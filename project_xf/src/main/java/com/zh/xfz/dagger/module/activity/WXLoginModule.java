package com.zh.xfz.dagger.module.activity;

import com.zh.xfz.business.activity.WXLoginActivity;
import com.zh.xfz.dagger.module.CommonActivityModule;
import com.zh.xfz.utils.MyCountDownTimer;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseActivity;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/12/6
 * description:
 */
@Module(includes = {CommonActivityModule.class})
public class WXLoginModule {
    @ActivityScope
    @Provides
    public BaseActivity activity(WXLoginActivity activity) {
        return activity;
    }

    @ActivityScope
    @Provides
    public MyCountDownTimer myCountDownTimer() {
        return new MyCountDownTimer();
    }
}
