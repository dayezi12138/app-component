package com.zh.xfz.business.activity;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.zh.xfz.R;
import com.zh.xfz.db.bean.UserInfo;
import com.zh.xfz.utils.IM.IMConnectCallBack;
import com.zh.xfz.utils.IM.IMUtils;
import com.zh.xfz.utils.LoginHandler;
import com.zh.xfz.utils.WxHelper;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;

import static com.zh.xfz.constans.Constants.COUNT_DOWN_INTERVAL;

/**
 * author : dayezi
 * data :2019/7/17
 * description:
 */
public class SplashActivity extends BaseActivity implements IMConnectCallBack {

    @Inject
    LoginHandler loginHandler;

    @BindView(R.id.img_iv)
    ImageView ly;

    private void start(boolean toMain) {
        new Handler().postDelayed(() -> {
            if (toMain)
                ARouter.getInstance().build(MainActivity.AROUTER_PATH).navigation();
            else
                ARouter.getInstance().build(LoginActivity.AROUTER_PATH).navigation();
            finish();
        }, 3 * COUNT_DOWN_INTERVAL);
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
        ly.startAnimation(AnimationUtils.loadAnimation(this, R.anim.splash_anim));
        try {
            UserInfo userInfo = loginHandler.getCurrentUserInfo();
            IMUtils.connect(userInfo.getToken(), this);
        } catch (RuntimeException e) {
            e.printStackTrace();
            start(false);
        }
    }

}
