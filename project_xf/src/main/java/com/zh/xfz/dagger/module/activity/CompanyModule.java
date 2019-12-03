package com.zh.xfz.dagger.module.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zh.xfz.R;
import com.zh.xfz.business.activity.CompanyActivity;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.view.MyPopupWindow;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/10/29
 * description:
 */
@Module
public class CompanyModule {
    @ActivityScope
    @Provides
    public MyPopupWindow popupWindow(CompanyActivity activity) {
        View view = LayoutInflater.from(activity).inflate(R.layout.person_card_pop, null);
        MyPopupWindow.Builder builder = new MyPopupWindow.Builder(view, activity);
        builder.animationStyle(R.style.pop_animation);
        TextView tv = view.findViewById(R.id.qcrode_tv);
        tv.setText("退出企业");
        tv.setTextColor(activity.getResources().getColor(R.color.d));
        tv.setOnClickListener(activity);
        view.findViewById(R.id.cancel_tv).setOnClickListener(activity);
        return builder.build();
    }
}
