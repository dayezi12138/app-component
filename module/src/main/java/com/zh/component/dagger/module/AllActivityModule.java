package com.zh.component.dagger.module;

import com.zh.component.business.activity.SplashActivity;
import com.zh.component1.business.activity.MainActivity;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.dagger.component.ActivityComponent;
import core.app.zh.com.core.dagger.module.EmptyModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @auther create by Administrator
 * DATE:2019/3/26 0026 11
 */
@Module(subcomponents = ActivityComponent.class)
public abstract class AllActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = EmptyModule.class)
    abstract SplashActivity contributeSplashActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = EmptyModule.class)
    abstract MainActivity contributeMainActivityInjector();
}