package com.zh.xfz.dagger.module.activity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zh.xfz.R;
import com.zh.xfz.business.activity.GroupMemberListActivity;
import com.zh.xfz.dagger.module.CommonActivityModule;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseActivity;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/9/25
 * description:
 */
@Module(includes = CommonActivityModule.class)
public class GroupMemberListModule {

    @ActivityScope
    @Provides
    public BaseActivity activity(GroupMemberListActivity activity) {
        return activity;
    }

    @ActivityScope
    @Provides
    public MaterialDialog.Builder dialog(GroupMemberListActivity activity) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(activity)
                .title("提示")
                .content("是否将该群转让给这个人?")
                .negativeColor(activity.getResources().getColor(R.color.background_splash_color))
                .positiveColor(activity.getResources().getColor(R.color.background_splash_color))
                .positiveText(activity.getResources().getString(R.string.act_create_busi_sure_str))
                .negativeText(activity.getResources().getString(R.string.rc_cancel));
        return builder;
    }
}
