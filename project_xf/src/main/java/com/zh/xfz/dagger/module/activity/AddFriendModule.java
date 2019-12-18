package com.zh.xfz.dagger.module.activity;

import com.zh.xfz.business.activity.AddFriendActivity;
import com.zh.xfz.dagger.module.CommonActivityModule;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseActivity;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/12/16
 * description:
 */
@Module(includes = CommonActivityModule.class)
public class AddFriendModule {
    @ActivityScope
    @Provides
    public BaseActivity activity(AddFriendActivity activity) {
        return activity;
    }
}
