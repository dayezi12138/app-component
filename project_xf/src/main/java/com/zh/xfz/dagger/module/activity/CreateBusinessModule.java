package com.zh.xfz.dagger.module.activity;

import com.zh.xfz.business.activity.CreateBusinessActivity;
import com.zh.xfz.dagger.module.CommonActivityModule;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseActivity;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/7/29
 * description:
 */
@Module(includes = {CommonActivityModule.class})
public class CreateBusinessModule {

    @ActivityScope
    @Provides
    public BaseActivity activity(CreateBusinessActivity activity) {
        return activity;
    }
}
