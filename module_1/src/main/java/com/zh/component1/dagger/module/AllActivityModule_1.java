package com.zh.component1.dagger.module;


import com.zh.component1.business.activity.MainActivity;
import com.zh.component1.dagger.module.activity.MainModule;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.dagger.component.ActivityComponent;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @auther create by Administrator
 * DATE:2019/3/26 0026 11
 */
@Module(subcomponents = ActivityComponent.class)
public abstract class AllActivityModule_1 {
    @ActivityScope
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity contributeMainActivityInjector();
}