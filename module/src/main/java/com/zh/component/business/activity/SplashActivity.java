package com.zh.component.business.activity;

import android.Manifest;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.component.R;
import com.zh.component1.business.activity.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.annotation.NeedPermission;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BasePresenter;

/**
 * author : dayezi
 * data :2019/6/4
 * description:
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.above_1)
    TextView textView;

    @Inject
    String aaa;


    @OnClick(R.id.above_1)
    public void onclick() {
        Toast.makeText(this, "111", Toast.LENGTH_SHORT).show();
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_splash;
    }

    @NeedPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    @Override
    public void init() {
        new Handler().postDelayed(() -> ARouter.getInstance().build(MainActivity.AROUTER_PATH).navigation(), 2000);

    }
}
