package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.zh.xfz.R;
import com.zh.xfz.db.bean.UserInfo;
import com.zh.xfz.utils.IM.IMConnectCallBack;
import com.zh.xfz.utils.IM.IMUtils;
import com.zh.xfz.utils.LoginHandler;
import com.zh.xfz.utils.WxHelper;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/7/17
 * description:
 */
public class SplashActivity extends BaseActivity implements IMConnectCallBack {

    @Inject
    LoginHandler loginHandler;

    private void start(boolean toMain) {
        if (toMain)
            ARouter.getInstance().build(MainActivity.AROUTER_PATH).navigation();
        else
            ARouter.getInstance().build(LoginActivity.AROUTER_PATH).navigation();
        finish();
    }

    @Override
    public void success(String userId) {
        start(true);
    }

    @Override
    public void fail(String msg) {
        start(false);
        ToastUtils.showShort(msg);

    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_splash;
    }

    @Override
    public void init() {
        WxHelper.register(this, true);
        try {
            UserInfo userInfo = loginHandler.getCurrentUserInfo();
            IMUtils.connect(userInfo.getToken(), this);
        } catch (RuntimeException e) {
            e.printStackTrace();
            start(false);
        }
    }

}
