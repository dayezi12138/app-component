package com.zh.component1.mvp.model.activity;

import com.zh.component1.api.ModuleSevice;
import com.zh.component1.business.activity.MainActivity;

import java.lang.reflect.Proxy;
import java.util.HashMap;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseModel;
import core.app.zh.com.core.bean.MyObservable;
import core.app.zh.com.core.proxy.RequestProxy;
import retrofit2.Retrofit;

/**
 * author : dayezi
 * data :2019/7/1
 * description:
 */
public class MainModel implements BaseModel<MainActivity> {
    private MainActivity activity;
    private ModuleSevice moduleSevice;
    private Retrofit retrofit;

    @Inject
    public MainModel(MainActivity activity, Retrofit retrofit) {
        this.activity = activity;
        this.retrofit = retrofit;
//        this.moduleSevice = moduleSevice;
    }

    public void test(MyObservable.OnSuccessListener<Object> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity).build();
//        moduleSevice.getPlanPager(new HashMap<>()).subscribe(observable);
        moduleSevice(retrofit).getPlanPager(new HashMap<>()).subscribe(observable);
    }

    public ModuleSevice moduleSevice(Retrofit retrofit) {
        ModuleSevice myService = retrofit.create(ModuleSevice.class);
        RequestProxy proxy = new RequestProxy(myService);
        return (ModuleSevice) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{ModuleSevice.class}, proxy);
    }

    @Override
    public MainActivity getBean() {
        return activity;
    }
}
