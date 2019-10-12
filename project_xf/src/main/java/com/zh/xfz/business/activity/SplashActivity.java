package com.zh.xfz.business.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.xfz.R;

import butterknife.ButterKnife;

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
        start();
    }

    private void start() {
        new Handler().postDelayed(() -> {
            ARouter.getInstance().build(LoginActivity.AROUTER_PATH).navigation();
            finish();
        }, 3000);
    }
}
