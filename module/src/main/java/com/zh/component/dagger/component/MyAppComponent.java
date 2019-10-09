package com.zh.component.dagger.component;

import com.zh.component.application.MyApplication;
import com.zh.component.dagger.module.AllActivityModule;
import com.zh.component.dagger.module.AllFragmentModule;
import com.zh.component.dagger.module.AppModule;
import com.zh.component1.dagger.module.AllActivityModule_1;
import com.zh.component1.dagger.module.AllFragmentModule_1;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AppModule.class,
        AllActivityModule.class,
        AllActivityModule_1.class,
        AllFragmentModule_1.class,
        AllFragmentModule.class
        })
public interface MyAppComponent extends AndroidInjector<MyApplication> {
    void inject(MyApplication instance);
}