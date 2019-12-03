package com.zh.xfz.dagger.module.activity;

import java.util.Arrays;
import java.util.List;

import core.app.zh.com.core.annotation.ActivityScope;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/10/30
 * description:
 */
@Module
public class HelpModule {

    @ActivityScope
    @Provides
    public List<String> stringList() {
        return Arrays.asList("新手指南","晋升职等职阶","英雄擂","百团大战");
    }
}
