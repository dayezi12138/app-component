package com.zh.xfz.dagger.module.fragment;

import com.zh.xfz.business.fragment.CompanyMemberListFragment;

import core.app.zh.com.core.annotation.FragmentScope;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.MyBaseModel;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/12/24
 * description:
 */
@Module
public class CompanyMemberListModule {

    @FragmentScope
    @Provides
    public BaseActivity activity(CompanyMemberListFragment fragment) {
        return fragment.getMyActivity();
    }

    @FragmentScope
    @Provides
    public MyBaseModel baseModel(CompanyMemberListFragment fragment) {
        return new MyBaseModel(fragment.getMyActivity().getApplication()) {
            @Override
            public BaseView getBaseView() {
                return fragment;
            }

            @Override
            public BaseActivity getMyActivity() {
                return fragment.getMyActivity();
            }
        };
    }
}
