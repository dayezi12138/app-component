package com.zh.component1.dagger.component;


import com.zh.component1.application.Module_1Application;
import com.zh.component1.dagger.module.AllActivityModule_1;
import com.zh.component1.dagger.module.AllFragmentModule_1;
import com.zh.component1.dagger.module.AppModule_1;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AppModule_1.class,
        AllActivityModule_1.class,
        AllFragmentModule_1.class
})
public interface MyAppComponent_1 extends AndroidInjector<Module_1Application> {
    void inject(Module_1Application instance);
}