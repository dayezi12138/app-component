package com.zh.xfz.dagger.module.activity;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zh.xfz.R;
import com.zh.xfz.business.activity.FriendDetailActivity;
import com.zh.xfz.dagger.module.CommonActivityModule;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.view.MyPopupWindow;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/9/23
 * description:
 */
@Module(includes = CommonActivityModule.class)
public class FriendDetailModule {
    @ActivityScope
    @Provides
    public BaseActivity activity(FriendDetailActivity activity) {
        return activity;
    }

    @ActivityScope
    @Provides
    public MyPopupWindow popupWindow(FriendDetailActivity activity) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(activity).inflate(R.layout.friend_operation, null);
        for (int i = 0, j = view.getChildCount(); i < j; i++) {
            view.getChildAt(i).setOnClickListener(activity);
        }
        MyPopupWindow.Builder builder = new MyPopupWindow.Builder(view, activity);
        builder.animationStyle(R.style.pop_animation);
        return builder.build();
    }

    @ActivityScope
    @Provides
    public Dialog dialog(FriendDetailActivity activity) {
        MaterialDialog materialDialog = new MaterialDialog.Builder(activity)
                .customView(R.layout.dialog_input, false)
                .positiveText(R.string.rc_confirm)
                .title("备注修改")
                .negativeText(R.string.rc_cancel)
                .positiveColor(activity.getResources().getColor(R.color.background_splash_color))
                .negativeColor(activity.getResources().getColor(R.color.background_splash_color))
                .onPositive(activity)
                .build();
        return materialDialog;
    }
}
