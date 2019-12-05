package cn.zh.ydt.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zh.xfz.application.MyApplication;
import com.zh.xfz.business.activity.AccountLoginActivity;
import com.zh.xfz.business.activity.AccountSecurityActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

/**
 * author : dayezi
 * data :2019/11/29
 * description:
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static String TAG = "MicroMsg.WXEntryActivity";

    private IWXAPI api;
    private MyHandler handler;
    private static final String APP_SECRETE = "b372e17aabaffdd328edc7709b1cad25";

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
                        String openId, accessToken, refreshToken, scope;
                        openId = json.getString("openid");
                        accessToken = json.getString("access_token");
                        refreshToken = json.getString("refresh_token");
                        scope = json.getString("scope");
                        if (accessToken != null && openId != null) {
                            NetworkUtil.sendWxAPI(this, String.format("https://api.weixin.qq.com/sns/auth?" +
                                    "access_token=%s&openid=%s", accessToken, openId), NetworkUtil.CHECK_TOKEN);
                        }
                        Intent intent = new Intent(wxEntryActivityWeakReference.get(), AccountLoginActivity.class);
                        intent.putExtra("openId", openId);
                        intent.putExtra("accessToken", accessToken);
                        intent.putExtra("refreshToken", refreshToken);
                        intent.putExtra("scope", scope);
                        wxEntryActivityWeakReference.get().startActivity(intent);
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
            if (!authResp.state.equals("AccountSecurityActivity"))
                NetworkUtil.sendWxAPI(handler, String.format("https://api.weixin.qq.com/sns/oauth2/access_token?" +
                                "appid=%s&secret=%s&code=%s&grant_type=authorization_code", MyApplication.APP_ID,
                        APP_SECRETE, code), NetworkUtil.GET_TOKEN);
            else {
                Intent intent = new Intent(this, AccountSecurityActivity.class);
                intent.putExtra(AccountSecurityActivity.AUTHOR_CODE, authResp.code);
                startActivity(intent);
            }
        }
        finish();
    }


}