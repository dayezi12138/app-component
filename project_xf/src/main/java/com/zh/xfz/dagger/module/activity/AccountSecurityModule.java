package com.zh.xfz.dagger.module.activity;

import com.zh.xfz.business.activity.AccountSecurityActivity;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.MyBaseModel;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/12/5
 * description:
 */
@Module
public class AccountSecurityModule {


    @ActivityScope
    @Provides
    public BaseActivity activity(AccountSecurityActivity activity) {
        return activity;
    }

    @ActivityScope
    @Provides
    public MyBaseModel myBaseModel(BaseActivity activity) {
        return new MyBaseModel(activity.getApplication()) {
            @Override
            public BaseView getBaseView() {
                return activity;
            }

            @Override
            public BaseActivity getMyActivity() {
                return activity.getMyActivity();
            }
        };
    }
}
