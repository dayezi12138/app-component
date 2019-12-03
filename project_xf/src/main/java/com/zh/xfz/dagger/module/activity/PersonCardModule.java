package com.zh.xfz.dagger.module.activity;

import android.view.LayoutInflater;
import android.view.View;

import com.zh.xfz.R;
import com.zh.xfz.business.activity.PersonCardActivity;

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
public class PersonCardModule {
    @ActivityScope
    @Provides
    public MyPopupWindow popupWindow(PersonCardActivity activity) {
        View view = LayoutInflater.from(activity).inflate(R.layout.person_card_pop, null);
        MyPopupWindow.Builder builder = new MyPopupWindow.Builder(view, activity);
        builder.animationStyle(R.style.pop_animation);
        view.findViewById(R.id.qcrode_tv).setOnClickListener(activity);
        view.findViewById(R.id.cancel_tv).setOnClickListener(activity);
        return builder.build();
    }

}
