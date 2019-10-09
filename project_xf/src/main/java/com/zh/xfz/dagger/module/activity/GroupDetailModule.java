package com.zh.xfz.dagger.module.activity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zh.xfz.R;
import com.zh.xfz.business.activity.GroupDetailActivity;

import javax.inject.Named;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseActivity;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/9/26
 * description:
 */
@Module
public class GroupDetailModule {
    @ActivityScope
    @Provides
    public BaseActivity activity(GroupDetailActivity activity) {
        return activity;
    }

    @ActivityScope
    @Provides
    public MaterialDialog.Builder dialog(GroupDetailActivity activity) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(activity)
                .title("提示")
                .content("确定清空消息吗?")
                .negativeColor(activity.getResources().getColor(R.color.background_splash_color))
                .positiveColor(activity.getResources().getColor(R.color.background_splash_color))
                .positiveText(activity.getResources().getString(R.string.act_create_busi_sure_str))
                .negativeText(activity.getResources().getString(R.string.rc_cancel));
        return builder;
    }

    @ActivityScope
    @Provides
    @Named("dialog")
    public MaterialDialog.Builder dialog1(GroupDetailActivity activity) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(activity)
                .title("提示")
                .negativeColor(activity.getResources().getColor(R.color.background_splash_color))
                .positiveColor(activity.getResources().getColor(R.color.background_splash_color))
                .positiveText(activity.getResources().getString(R.string.act_create_busi_sure_str))
                .negativeText(activity.getResources().getString(R.string.rc_cancel));
        return builder;
    }
}
