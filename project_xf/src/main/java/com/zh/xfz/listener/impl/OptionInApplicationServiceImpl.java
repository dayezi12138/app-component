package com.zh.xfz.listener.impl;

import com.blankj.utilcode.util.Utils;
import com.zh.annatation.application.ApplicationOption;
import com.zh.applicationOption.InitService;
import com.zh.applicationOption.OptionInApplicationService;
import com.zh.xfz.application.MyApplication;
import com.zh.xfz.dagger.component.DaggerMyAppComponent;
import com.zh.xfz.dagger.component.MyAppComponent;
import com.zh.xfz.dagger.module.AppModule;

import java.util.List;

/**
 * author : dayezi
 * data :2019/8/2
 * description:
 */
@ApplicationOption(option = OptionInApplicationServiceImpl.class)
public class OptionInApplicationServiceImpl implements OptionInApplicationService {
    @Override
    public List<InitService> addInitService() {
        return null;
    }

    @Override
    public Object optionApplicationComponent() {
        MyAppComponent component = DaggerMyAppComponent.builder().appModule(new AppModule((MyApplication) Utils.getApp())).build();
        component.inject((MyApplication) Utils.getApp());
        return component;
    }
}
