package com.zh.xfz.dagger.module.activity;

import com.zh.xfz.business.activity.AccountLoginActivity;
import com.zh.xfz.dagger.module.CommonActivityModule;

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
public class AccountLoginModule {

    @ActivityScope
    @Provides
    public BaseActivity baseActivity(AccountLoginActivity activity) {
        return activity;
    }


}
