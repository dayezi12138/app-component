package com.zh.xfz.utils;

import android.content.Context;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zh.xfz.application.MyApplication;

import java.util.Date;

/**
 * author : dayezi
 * data :2019/12/13
 * description:
 */
public class WxHelper {

    public static final String SCOPE = "snsapi_userinfo";

    /**
     * 微信注册相关
     *
     * @param context
     * @param b
     * @return
     */
    public static IWXAPI register(Context context, boolean b) {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        IWXAPI api = WXAPIFactory.createWXAPI(context, MyApplication.APP_ID, b);
        // 将应用的appId注册到微信
        api.registerApp(MyApplication.APP_ID);
        return api;
    }

    public static SendAuth.Req wxAuth() {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = WxHelper.SCOPE;
        req.state = String.valueOf(new Date().getTime());
        return req;
    }
}
