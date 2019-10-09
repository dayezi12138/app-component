package com.zh.xfz.dagger.module.fragment;

import com.zh.xfz.business.fragment.MineFragment;

import core.app.zh.com.core.annotation.FragmentScope;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.MyBaseModel;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/9/25
 * description:
 */
@Module
public class MineModule {

    @FragmentScope
    @Provides
    public BaseActivity activity(MineFragment fragment) {
        return fragment.getMyActivity();
    }

    @FragmentScope
    @Provides
    public MyBaseModel myBaseModel(MineFragment fragment) {
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
