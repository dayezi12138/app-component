package com.zh.xfz.dagger.module.activity;

import com.zh.xfz.business.activity.UpdatePersonNameActivity;
import com.zh.xfz.dagger.module.CommonActivityModule;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by YST on 2019/12/4 0004.
 */

@Module(includes = CommonActivityModule.class)
public class UpdatePersonNameModule {

    @ActivityScope
    @Provides

    public BaseActivity activity (UpdatePersonNameActivity activity) {return activity.getMyActivity();}


}
