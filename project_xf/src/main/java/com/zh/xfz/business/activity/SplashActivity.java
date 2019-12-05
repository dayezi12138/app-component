package com.zh.xfz.business.activity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zh.xfz.R;
import com.zh.xfz.application.MyApplication;
import com.zh.xfz.utils.IM.IMConnectCallBack;
import com.zh.xfz.utils.IM.IMUtils;
import com.zh.xfz.utils.LoginUtils;

import butterknife.ButterKnife;
import cn.zh.ydt.wxapi.AppRegister;

import static com.zh.xfz.constans.Constans.IM_TOKEN;
import static com.zh.xfz.constans.Constans.USER_INFO;

/**
 * author : dayezi
 * data :2019/7/17
 * description:
 */
public class SplashActivity extends AppCompatActivity {
    private IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.act_splash);
        regToWx();
        if (SPUtils.getInstance().contains(USER_INFO)) {
            IMUtils.connect(SPUtils.getInstance().getString(IM_TOKEN), new IMConnectCallBack() {
                @Override
                public void success(String userid) {
                    LoginUtils.ACCOUNT = LoginUtils.getUserInfo();
                    start(true);
                }

                @Override
                public void fail(String msg) {
                    start(false);
                    ToastUtils.showShort(msg);
                }
            });
        } else
            start(false);
    }

    private void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, MyApplication.APP_ID, true);
        // 将应用的appId注册到微信
        api.registerApp(MyApplication.APP_ID);

        //建议动态监听微信启动广播进行注册到微信
        registerReceiver(new AppRegister(), new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));

    }

    private void start(boolean toMain) {
        new Handler().postDelayed(() -> {
            if (toMain)
                ARouter.getInstance().build(MainActivity.AROUTER_PATH).navigation();
            else
                ARouter.getInstance().build(LoginActivity.AROUTER_PATH).navigation();
            finish();
        }, 2000);
    }

}
