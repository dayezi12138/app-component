package com.zh.xfz.dagger.module.activity;

import com.zh.xfz.business.activity.SearchFriendActivity;
import com.zh.xfz.dagger.module.CommonActivityModule;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseActivity;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/10/23
 * description:
 */
@Module(includes = CommonActivityModule.class)
public class SearchFriendModule {
    @ActivityScope
    @Provides
    public BaseActivity activity(SearchFriendActivity activity) {
        return activity;
    }
}
