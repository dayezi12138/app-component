package com.zh.xfz.dagger.module.activity;

import com.zh.xfz.business.activity.AddGroupMembersActivity;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseActivity;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/9/24
 * description:
 */
@Module
public class AddGroupMembersModule {

    @ActivityScope
    @Provides
    public BaseActivity activity(AddGroupMembersActivity activity) {
        return activity;
    }
}
