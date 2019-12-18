package com.zh.xfz.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.StringUtils;
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
import com.zh.xfz.constans.Constants;

/**
 * author : dayezi
 * data :2019/11/29
 * description:
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, MyApplication.APP_ID, false);
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
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
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
            String code = authResp.code;
            if (StringUtils.isEmpty(authResp.code)) {
                finish();
                return;
            }
            if (!authResp.state.equals(AccountSecurityActivity.class.getSimpleName())) {
                Intent intent = new Intent(this, AccountLoginActivity.class);
                intent.putExtra(Constants.WX_CODE, code);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, AccountSecurityActivity.class);
                intent.putExtra(AccountSecurityActivity.AUTHOR_CODE, authResp.code);
                startActivity(intent);
                finish();
            }
        }
    }
}