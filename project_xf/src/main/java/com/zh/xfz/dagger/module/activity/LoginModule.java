package com.zh.xfz.dagger.module.activity;

import com.zh.xfz.business.activity.LoginActivity;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseActivity;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/10/17
 * description:
 */
@Module
public class LoginModule {

    @ActivityScope
    @Provides
    public BaseActivity activity(LoginActivity activity) {
        return activity;
    }

//    @ActivityScope
//    @Provides
//    public IMConnectCallBack connectCallBack() {
//        return new IMConnectCallBack() {
//            @Override
//            public void success(String userid) {
//                ARouter.getInstance().build(CreateBusinessActivity.AROUTER_PATH).navigation();
//            }
//
//            @Override
//            public void fail(String msg) {
//            TPAS
//            }
//        };
//    }
}
