package com.zh.xfz.dagger.module.fragment;

import android.annotation.SuppressLint;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zh.xfz.R;
import com.zh.xfz.business.fragment.ApplyTenantFragment;

import core.app.zh.com.core.annotation.FragmentScope;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseFragment;
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
public class ApplyTenantModule {

    @FragmentScope
    @Provides
    public BaseActivity activity(ApplyTenantFragment fragment) {
        return fragment.getMyActivity();
    }

    @FragmentScope
    @Provides
    public BaseFragment baseFragment(ApplyTenantFragment fragment) {
        return fragment;
    }

    @SuppressLint("ResourceAsColor")
    @FragmentScope
    @Provides
    public MaterialDialog dialog(ApplyTenantFragment fragment) {
        return new MaterialDialog.Builder(fragment.getMyActivity())
                .title(fragment.getActivity().getResources().getString(R.string.act_normal_title_dialog_msg))
                .contentColor(fragment.getResources().getColor(R.color.textColorPrimary))
                .content(fragment.getActivity().getResources().getString(R.string.frag_apply_tenant_content_dialog_msg))
                .positiveText(fragment.getActivity().getResources().getString(R.string.frag_apply_tenant_sure_dialog_msg))
                .negativeText(fragment.getActivity().getResources().getString(R.string.frag_apply_tenant_cancel_dialog_msg))
                .neutralText(fragment.getActivity().getResources().getString(R.string.alert_cancel_str))
                .cancelable(true)
                .neutralColor(fragment.getResources().getColor(R.color.textSecondary))
                .positiveColor(fragment.getResources().getColor(R.color.light_blue_color))
                .negativeColor(fragment.getResources().getColor(R.color.d))
                .onNegative(fragment)
                .onNeutral(fragment)
                .onPositive(fragment)
                .build();
    }

    @FragmentScope
    @Provides
    public MyBaseModel baseModel(ApplyTenantFragment fragment, BaseActivity activity) {
        return new MyBaseModel(activity.getApplication()) {
            @Override
            public BaseView getBaseView() {
                return fragment;
            }

            @Override
            public BaseActivity getMyActivity() {
                return activity;
            }
        };
    }
}
