package com.zh.component;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : dayezi
 * data :2019/6/4
 * description:
 */
public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.above_1)
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        new Handler().postDelayed(() -> ARouter.getInstance().build(MainActivity.AROUTER_PATH).navigation(), 2000);
    }

    @OnClick(R.id.above_1)
    public void onclick() {
        Toast.makeText(this, "111", Toast.LENGTH_SHORT).show();
    }
}
