package com.zh.xfz.dagger.module.activity;

import com.zh.xfz.business.activity.UpdatePhoneSmsActivity;
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
@Module(includes = CommonActivityModule.class)
public class UpdatePhoneSmsModule {

    @ActivityScope
    @Provides
    public BaseActivity activity(UpdatePhoneSmsActivity activity) {
        return activity;
    }

    @ActivityScope
    @Provides
    public MyCountDownTimer myCountDownTimer(UpdatePhoneSmsActivity activity) {
        MyCountDownTimer myCountDownTimer = new MyCountDownTimer();
        myCountDownTimer.setCountDownListener(activity);
        return myCountDownTimer;
    }

}
