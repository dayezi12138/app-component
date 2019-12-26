package com.zh.xfz.dagger.module.fragment;

import com.zh.xfz.business.fragment.HelpListFragment;
import com.zh.xfz.dagger.module.CommonFragmentModule;

import core.app.zh.com.core.annotation.FragmentScope;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseFragment;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/12/25
 * description:
 */
@Module(includes = {CommonFragmentModule.class})
public class HelpListModule {
    @FragmentScope
    @Provides
    public BaseActivity activity(HelpListFragment fragment) {
        return fragment.getMyActivity();
    }

    @FragmentScope
    @Provides
    public BaseFragment fragment(HelpListFragment fragment) {
        return fragment;
    }
}
