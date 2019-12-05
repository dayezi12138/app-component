package com.zh.xfz.dagger.module.activity;

import com.zh.xfz.business.activity.UpdatePersonNameActivity;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.MyBaseModel;
import dagger.Module;
import dagger.Provides;

/**
 * Created by YST on 2019/12/4 0004.
 */

@Module
public class UpdatePersonNameModule {

    @ActivityScope
    @Provides

    public BaseActivity activity (UpdatePersonNameActivity activity) {return activity.getMyActivity();}

    @ActivityScope
    @Provides
    public MyBaseModel myBaseModel (UpdatePersonNameActivity activity){
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
