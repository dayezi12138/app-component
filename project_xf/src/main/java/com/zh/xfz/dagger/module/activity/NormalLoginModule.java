package com.zh.xfz.dagger.module.activity;

import com.zh.xfz.business.activity.InputPasswordActivity;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseActivity;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/7/18
 * description:
 */
@Module
public class NormalLoginModule {

    @ActivityScope
    @Provides
    public BaseActivity baseActivity(InputPasswordActivity inputPasswordActivity) {
        return inputPasswordActivity;
    }
}
