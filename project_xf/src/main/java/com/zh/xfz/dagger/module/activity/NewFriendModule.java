package com.zh.xfz.dagger.module.activity;

import com.zh.xfz.R;
import com.zh.xfz.business.adapter.ContactAdapter;

import core.app.zh.com.core.annotation.ActivityScope;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/9/12
 * description:
 */
@Module
public class NewFriendModule {
    @ActivityScope
    @Provides
    public ContactAdapter contactAdapter() {
        return new ContactAdapter(R.layout.item_new_friend);
    }
}
