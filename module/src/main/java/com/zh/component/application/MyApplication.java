package com.zh.component.application;

import core.app.zh.com.core.application.BaseApplication;
import core.app.zh.com.core.listener.AddOptionInApplicationListener;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * author : dayezi
 * data :2019/6/4
 * description:
 */
public class MyApplication extends BaseApplication {
    @Override
    public AddOptionInApplicationListener option() {
        return null;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return null;
    }
}
