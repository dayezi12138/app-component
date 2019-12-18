package com.zh.xfz.dagger.module.activity;

import android.view.LayoutInflater;
import android.view.View;

import com.zh.xfz.R;
import com.zh.xfz.business.activity.MyCompanyActivity;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.view.MyPopupWindow;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/12/18
 * description:
 */
@Module
public class MyCompanyModule {

    @ActivityScope
    @Provides
    public MyPopupWindow popupWindow(MyCompanyActivity activity) {
        View view = LayoutInflater.from(activity).inflate(R.layout.view_my_company, null);
        MyPopupWindow.Builder builder = new MyPopupWindow.Builder(view, activity);
        builder.animationStyle(R.style.pop_animation);
        view.findViewById(R.id.create_btn).setOnClickListener(activity);
        view.findViewById(R.id.company_list_btn).setOnClickListener(activity);
        return builder.build();
    }
}
