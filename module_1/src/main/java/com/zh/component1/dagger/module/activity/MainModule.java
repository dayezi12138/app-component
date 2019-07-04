package com.zh.component1.dagger.module.activity;

import com.zh.component1.api.ModuleSevice;

import java.lang.reflect.Proxy;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.proxy.RequestProxy;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * author : dayezi
 * data :2019/7/1
 * description:
 */
@Module
public class MainModule {

    @ActivityScope
    @Provides
    public ModuleSevice moduleSevice(Retrofit retrofit) {
        ModuleSevice myService = retrofit.create(ModuleSevice.class);
        RequestProxy proxy = new RequestProxy(myService);
        return (ModuleSevice) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{ModuleSevice.class}, proxy);
    }

}
