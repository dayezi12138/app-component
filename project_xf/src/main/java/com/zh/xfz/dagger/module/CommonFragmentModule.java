package com.zh.xfz.dagger.module;

import core.app.zh.com.core.annotation.FragmentScope;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.MyBaseModel;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/12/13
 * description:
 */
@Module
public class CommonFragmentModule {
    @FragmentScope
    @Provides
    public MyBaseModel baseModel(BaseFragment fragment) {
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
