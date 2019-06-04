package com.zh.component.application;

import com.zh.component.dagger.component.DaggerMyAppComponent;
import com.zh.component.dagger.component.MyAppComponent;
import com.zh.component.dagger.module.AppModule;

import core.app.zh.com.core.application.BaseApplication;
import core.app.zh.com.core.listener.AddOptionInApplicationListener;
import core.app.zh.com.core.listener.DaggerOptionListener;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * author : dayezi
 * data :2019/6/4
 * description:main module
 */
public class MyApplication extends BaseApplication {
    @Override
    public AddOptionInApplicationListener option() {
        return null;
    }

    @Override
    public DaggerOptionListener daggerOptionListener() {
        MyAppComponent component = DaggerMyAppComponent.builder().appModule(new AppModule(this)).build();
        component.inject(this);
        return () -> component;
    }
}
