package com.zh.component.application;

import com.zh.component.dagger.component.DaggerMyAppComponent;
import com.zh.component.dagger.component.MyAppComponent;
import com.zh.component.dagger.module.AppModule;

import core.app.zh.com.core.application.BaseApplication;
import core.app.zh.com.core.listener.DaggerOptionListener;

/**
 * author : dayezi
 * data :2019/6/4
 * description:main module
 */
public class MyApplication extends BaseApplication {

    @Override
    public void init(BaseApplication application) {
    }

    @Override
    public DaggerOptionListener daggerOptionListener() {
        MyAppComponent component = DaggerMyAppComponent.builder().appModule(new AppModule(this)).build();
        component.inject(this);
        return () -> component;
    }
}
