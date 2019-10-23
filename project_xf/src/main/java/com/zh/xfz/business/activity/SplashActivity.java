package com.zh.xfz.business.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zh.xfz.R;
import com.zh.xfz.utils.IM.IMConnectCallBack;
import com.zh.xfz.utils.IM.IMUtils;

import butterknife.ButterKnife;

import static com.zh.xfz.constans.Constans.IM_TOKEN;
import static com.zh.xfz.constans.Constans.USER_INFO;

/**
 * author : dayezi
 * data :2019/7/17
 * description:
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.act_splash);
        if (SPUtils.getInstance().contains(USER_INFO)) {
            IMUtils.connect(SPUtils.getInstance().getString(IM_TOKEN), new IMConnectCallBack() {
                @Override
                public void success(String userid) {
                    start(true);
//                    ARouter.getInstance().build(MainActivity.AROUTER_PATH).navigation();
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
