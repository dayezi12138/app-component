package com.zh.xfz.dagger.module;

import com.zh.xfz.business.fragment.ContactFragment;
import com.zh.xfz.business.fragment.MainFragment;
import com.zh.xfz.business.fragment.MineFragment;
import com.zh.xfz.business.fragment.WorkControlCenterFragment;
import com.zh.xfz.dagger.module.fragment.ContactModule;
import com.zh.xfz.dagger.module.fragment.MineModule;

import core.app.zh.com.core.annotation.FragmentScope;
import core.app.zh.com.core.dagger.component.FragmentComponent;
import core.app.zh.com.core.dagger.module.EmptyModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @auther create by Administrator
 * DATE:2019/3/26 0026 17
 */
@Module(subcomponents = FragmentComponent.class)
public abstract class AllFragmentModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract WorkControlCenterFragment contributeWorkControlCenterFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = {MineModule.class})
    abstract MineFragment contributeLoginMineFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = {ContactModule.class})
    abstract ContactFragment contributeContactFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract MainFragment contributeMainFragmentInjector();
}