package com.zh.component1.application;

import com.zh.api.DefaultBean;
import com.zh.api.ToolBarInject;
import com.zh.component1.dagger.component.DaggerMyAppComponent_1;
import com.zh.component1.dagger.component.MyAppComponent_1;

import core.app.zh.com.core.application.BaseApplication;
import core.app.zh.com.core.listener.DaggerOptionListener;

/**
 * author : dayezi
 * data :2019/6/4
 * description:
 */
public class Module_1Application extends BaseApplication {
//    @Override
//    public AddOptionInApplicationListener option() {
//        return application -> ToolBarInject.init(Module_1Application.this, () -> {
//            DefaultBean bean = new DefaultBean();
//            return bean;
//        });
//    }


    @Override
    public void init(BaseApplication application) {
        ToolBarInject.init(this, () -> {
            DefaultBean bean = new DefaultBean();
            return bean;
        });
    }

    @Override
    public DaggerOptionListener daggerOptionListener() {
        return () -> {
            MyAppComponent_1 component = DaggerMyAppComponent_1.builder().build();
            component.inject(Module_1Application.this);
            return component;
        };
    }
}
