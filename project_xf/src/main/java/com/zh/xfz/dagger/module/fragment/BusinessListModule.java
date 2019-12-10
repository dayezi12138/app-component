package com.zh.xfz.dagger.module.fragment;

import com.zh.xfz.business.fragment.BusinessListFragment;

import core.app.zh.com.core.annotation.FragmentScope;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.MyBaseModel;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/12/9
 * description:
 */
@Module
public class BusinessListModule {


    @FragmentScope
    @Provides
    public BaseActivity activity(BusinessListFragment fragment) {
        return fragment.getMyActivity();
    }

    @FragmentScope
    @Provides
    public MyBaseModel myBaseModel(BusinessListFragment fragment) {
        return new MyBaseModel(fragment.getActivity().getApplication()) {
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
