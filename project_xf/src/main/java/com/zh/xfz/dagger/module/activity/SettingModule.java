package com.zh.xfz.dagger.module.activity;

import android.app.Activity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zh.xfz.R;
import com.zh.xfz.business.activity.SettingActivity;
import com.zh.xfz.utils.LoginHandler;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.listener.impl.ActivityLifecycleCallbackListener;
import dagger.Module;
import dagger.Provides;
import io.rong.imkit.RongIM;

/**
 * author : dayezi
 * data :2019/10/8
 * description:
 */
@Module
public class SettingModule {


    @ActivityScope
    @Provides
    public MaterialDialog dialog(SettingActivity activity, LoginHandler loginHandler) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(activity)
                .title("提示")
                .content("真的要退出应用?")
                .negativeColor(activity.getResources().getColor(R.color.background_splash_color))
                .positiveColor(activity.getResources().getColor(R.color.background_splash_color))
                .positiveText(activity.getResources().getString(R.string.act_create_busi_sure_str))
                .negativeText(activity.getResources().getString(R.string.rc_cancel))
                .onPositive((dialog, which) -> {
                    loginHandler.clearLogin();
                    RongIM.getInstance().logout();
                    for (Activity activity1 : ActivityLifecycleCallbackListener.sActivityList) {
                        activity1.finish();
                    }
                });
        return builder.build();
    }
}
