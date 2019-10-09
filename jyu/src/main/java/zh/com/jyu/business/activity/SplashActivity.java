package zh.com.jyu.business.activity;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ScreenUtils;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BasePresenter;
import zh.com.jyu.R;
import zh.com.jyu.constans.CommonConstants;
import zh.com.jyu.listener.LoginStateListener;

/**
 * author : dayezi
 * data :2019/6/4
 * description:
 */
public class SplashActivity extends BaseActivity {


    @Inject
    LoginStateListener loginStateListener;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_splash;
    }

    @Override
    public void init() {
        new Handler().postDelayed(() -> {
            loginStateListener.start();
            finish();
        }, CommonConstants.DELAY_TIME);
    }

    @Override
    public View beforeInit(LayoutInflater inflater, ViewGroup container) {
        ScreenUtils.setFullScreen(this);
        return super.beforeInit(inflater, container);
    }
}
