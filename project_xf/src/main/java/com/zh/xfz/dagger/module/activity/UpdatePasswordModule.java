package com.zh.xfz.dagger.module.activity;

import com.zh.xfz.business.activity.UpdatePasswordActivity;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.MyBaseModel;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/10/17
 * description:
 */
@Module
public class UpdatePasswordModule {
    @ActivityScope
    @Provides
    public BaseActivity activity(UpdatePasswordActivity activity) {
        return activity;
    }

    @ActivityScope
    @Provides
    public MyBaseModel baseModel(BaseActivity activity) {
        return new MyBaseModel(activity.getApplication()) {
            @Override
            public BaseView getBaseView() {
                return activity;
            }

            @Override
            public BaseActivity getMyActivity() {
                return activity;
            }
        };
    }
}
