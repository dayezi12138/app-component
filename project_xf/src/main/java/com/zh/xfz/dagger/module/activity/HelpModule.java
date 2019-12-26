package com.zh.xfz.dagger.module.activity;

import com.zh.xfz.business.activity.HelpActivity;
import com.zh.xfz.dagger.module.CommonActivityModule;

import java.util.Arrays;
import java.util.List;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseActivity;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/10/30
 * description:
 */
@Module(includes = {CommonActivityModule.class})
public class HelpModule {

    @ActivityScope
    @Provides
    public List<String> stringList() {
        return Arrays.asList("新手指南", "晋升职等职阶", "英雄擂", "百团大战");
    }

    @ActivityScope
    @Provides
    public BaseActivity activity(HelpActivity activity) {
        return activity;
    }
}
