package com.zh.xfz.dagger.module.activity;

import com.zh.xfz.business.activity.UpdatePhoneSmsActivity;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.MyBaseModel;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/12/6
 * description:
 */
@Module
public class UpdatePhoneSmsModule {

    @ActivityScope
    @Provides
    public BaseActivity activity(UpdatePhoneSmsActivity activity) {
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
                return activity;
            }
        };
    }
}
