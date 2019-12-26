package com.zh.xfz.business.activity;

import android.Manifest;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.BarUtils;
import com.zh.xfz.R;

import java.util.List;

import butterknife.BindViews;
import butterknife.OnClick;
import core.app.zh.com.core.annotation.NeedPermission;
import core.app.zh.com.core.base.BaseActivity;

import static android.Manifest.permission.READ_PHONE_STATE;

/**
 * author : dayezi
 * data :2019/7/17
 * description:
 */
@Route(path = LoginActivity.AROUTER_PATH)
public class LoginActivity extends BaseActivity {
    public final static String AROUTER_PATH = "/login/LoginActivity/";

    @BindViews({R.id.normal_login_btn, R.id.phone_login_tbn})
    List<Button> buttonList;


    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_login;
    }

    @NeedPermission(next = false, value = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, READ_PHONE_STATE})
    @Override
    public void init() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.act_login_btn_anim);
        BarUtils.addMarginTopEqualStatusBarHeight(findViewById(R.id.ly));
        BarUtils.setStatusBarColor(this, getResources().getColor(R.color.background_splash_color), 1);
        for (View view : buttonList) {
            view.startAnimation(animation);
        }

    }

    @OnClick({R.id.normal_login_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.normal_login_btn:
                ARouter.getInstance().build(AccountLoginActivity.AROUTER_PATH).navigation();
                break;
        }
    }

}
