package com.zh.xfz.dagger.module.activity;

import com.zh.xfz.business.activity.GroupActivity;

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
public class GroupModule {

    @ActivityScope
    @Provides
    public BaseActivity activity(GroupActivity activity) {
        return activity;
    }
}
