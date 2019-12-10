package com.zh.xfz.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zh.xfz.application.MyApplication;
import com.zh.xfz.bean.activity.Account;
import com.zh.xfz.bean.other.Data;
import com.zh.xfz.business.activity.AccountLoginActivity;
import com.zh.xfz.business.activity.AccountSecurityActivity;
import com.zh.xfz.business.activity.MainActivity;
import com.zh.xfz.business.activity.WXLoginActivity;
import com.zh.xfz.dagger.module.AppModule;
import com.zh.xfz.utils.AndroidUtils;
import com.zh.xfz.utils.IM.IMConnectCallBack;
import com.zh.xfz.utils.IM.IMUtils;
import com.zh.xfz.utils.LoginUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * author : dayezi
 * data :2019/11/29
 * description:
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static String TAG = "MicroMsg.WXEntryActivity";

    private IWXAPI api;
    private MyHandler handler;

    private static final String APP_SECRETE = "2214b1b4360e30b8b581c5825e8e07c8";

    private static class MyHandler extends Handler {
        private final WeakReference<WXEntryActivity> wxEntryActivityWeakReference;

        public MyHandler(WXEntryActivity wxEntryActivity) {
            wxEntryActivityWeakReference = new WeakReference<>(wxEntryActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            int tag = msg.what;
            switch (tag) {
                case NetworkUtil.GET_TOKEN: {
                    Bundle data = msg.getData();
                    JSONObject json = null;
                    try {

                        json = new JSONObject(data.getString("result"));
                        String openId, accessToken, refreshToken, scope, unionid;
                        openId = json.getString("openid");
                        accessToken = json.getString("access_token");
                        refreshToken = json.getString("refresh_token");
                        unionid = json.getString("unionid");
                        scope = json.getString("scope");
                        if (accessToken != null && openId != null) {
                            NetworkUtil.sendWxAPI(this, String.format("https://api.weixin.qq.com/sns/auth?" +
                                    "access_token=%s&openid=%s", accessToken, openId), NetworkUtil.CHECK_TOKEN);
                        }

//                        Intent intent = new Intent(wxEntryActivityWeakReference.get(), AccountLoginActivity.class);
//                        intent.putExtra("openId", openId);
////                        RequestBody requestBody = new FormBody.Builder().add("")
////                        intent.putExtra("accessToken", accessToken);
////                        intent.putExtra("refreshToken", refreshToken);
////                        intent.putExtra("scope", scope);
//                        wxEntryActivityWeakReference.get().startActivity(intent);
                    } catch (JSONException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        api = WXAPIFactory.createWXAPI(this, MyApplication.APP_ID, false);
        handler = new MyHandler(this);
        try {
            Intent intent = getIntent();
            api.handleIntent(intent, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        switch (req.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
//                goToGetMsg();
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
//                goToShowMsg((ShowMessageFromWX.Req) req);
                break;
            default:
                break;
        }
        finish();
    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
            SendAuth.Resp authResp = (SendAuth.Resp) resp;
            final String code = authResp.code;
            if (!authResp.state.equals("AccountSecurityActivity")) {
                RequestBody body = new FormBody.Builder().add("code", code)
                        .add("timeStamp", AndroidUtils.getUUID()).build();
                okHttpClient(body).enqueue(new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Intent intent = new Intent(WXEntryActivity.this, AccountLoginActivity.class);
                        WXEntryActivity.this.startActivity(intent);
                        ToastUtils.showShort("微信登陆异常");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Type typeToken = new TypeToken<Data<Account>>(){}.getType();
                        Data<Account> result = new Gson().fromJson(response.body().string(), typeToken);
                        if (result.getCode() == 0) {
                            if (StringUtils.isEmpty(result.getRes().getAccess_token()))
                                connectIM(result.getRes());
                            else
                                ARouter.getInstance().build(WXLoginActivity.AROUTER_PATH)
                                        .withString("openId", result.getRes().getOpenid())
                                        .withString("unionid", result.getRes().getUnionid())
                                        .withString("access_token", result.getRes().getAccess_token())
                                        .navigation();
                        }
                    }
                });
            } else {
                Intent intent = new Intent(this, AccountSecurityActivity.class);
                intent.putExtra(AccountSecurityActivity.AUTHOR_CODE, authResp.code);
                startActivity(intent);
            }
        }
        finish();
    }

    private void connectIM(Account account) {
        IMUtils.connect(account.getToken(), new IMConnectCallBack() {
            @Override
            public void success(String userid) {
                LoginUtils.ACCOUNT = account;
                LoginUtils.saveLoginInfo(account);
                ARouter.getInstance().build(MainActivity.AROUTER_PATH).navigation();
                finish();
            }

            @Override
            public void fail(String msg) {
                ToastUtils.showShort(msg);
            }
        });
    }

    private Call okHttpClient(RequestBody body) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url(AppModule.BASE_URL + "User/WxCheckAndLogin")
                .post(body)
                .build();
        return okHttpClient.newCall(request);
    }

}