package com.zh.xfz.dagger.module.activity;

import com.zh.xfz.business.activity.ValidNoteActivity;
import com.zh.xfz.dagger.module.CommonActivityModule;
import com.zh.xfz.utils.MyCountDownTimer;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseActivity;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/7/24
 * description:
 */
@Module(includes = {CommonActivityModule.class})
public class ValidNoteModule {
    @ActivityScope
    @Provides
    public BaseActivity baseActivity(ValidNoteActivity activity) {
        return activity;
    }

    @ActivityScope
    @Provides
    public MyCountDownTimer myCountDownTimer() {
        return new MyCountDownTimer();
    }
}
