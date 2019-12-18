package com.zh.xfz.dagger.module.activity;

import com.zh.xfz.R;
import com.zh.xfz.business.activity.NewFriendActivity;
import com.zh.xfz.business.adapter.ContactAdapter;
import com.zh.xfz.dagger.module.CommonActivityModule;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseActivity;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/9/12
 * description:
 */
@Module(includes = CommonActivityModule.class)
public class NewFriendModule {

    @ActivityScope
    @Provides
    public ContactAdapter contactAdapter() {
        return new ContactAdapter(R.layout.item_new_friend);
    }

    @ActivityScope
    @Provides
    public BaseActivity activity(NewFriendActivity activity) {
        return activity;
    }
}
