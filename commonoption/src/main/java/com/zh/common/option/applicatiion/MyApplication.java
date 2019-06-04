package com.zh.common.option.applicatiion;

import core.app.zh.com.core.application.BaseApplication;
import core.app.zh.com.core.listener.AddOptionInApplicationListener;
import core.app.zh.com.core.listener.DaggerOptionListener;

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
    public DaggerOptionListener daggerOptionListener() {
        return null;
    }
}
