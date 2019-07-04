package com.zh.component1.dagger.module;

import com.zh.component1.business.fragment.MainFragment;

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
public abstract class AllFragmentModule_1 {

    @FragmentScope
    @ContributesAndroidInjector(modules = EmptyModule.class)
    abstract MainFragment contributeMainFragmentInjector();
}