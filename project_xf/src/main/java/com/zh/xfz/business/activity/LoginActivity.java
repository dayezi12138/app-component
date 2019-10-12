package com.zh.xfz.business.activity;

import android.Manifest;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.xfz.R;

import java.util.List;

import butterknife.BindViews;
import butterknife.OnClick;
import core.app.zh.com.core.annotation.NeedPermission;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BasePresenter;

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

    Animation animation;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_login;
    }

//    @LoadingShow
    @NeedPermission({Manifest.permission.READ_PHONE_STATE})
    @Override
    public void init() {
        animation = AnimationUtils.loadAnimation(this, R.anim.act_login_btn_anim);
        fullScreen(this);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                test();
//            }
//        }, 2000);
        for (View view : buttonList) {
            view.startAnimation(animation);
        }
    }

//    @LoadingHide
    private void test() {
    }


    /**
     * 通过设置全屏，设置状态栏透明
     *
     * @param activity
     */

    private void fullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_VISIBLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                attributes.flags |= flagTranslucentStatus;
                window.setAttributes(attributes);
            }
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
