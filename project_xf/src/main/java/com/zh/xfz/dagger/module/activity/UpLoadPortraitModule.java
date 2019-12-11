package com.zh.xfz.dagger.module.activity;

import android.view.LayoutInflater;
import android.view.View;

import com.zh.xfz.R;
import com.zh.xfz.business.activity.UpLoadPortraitActivity;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.MyBaseModel;
import core.app.zh.com.core.view.MyPopupWindow;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/10/29
 * description:
 */
@Module
public class UpLoadPortraitModule {
    @ActivityScope
    @Provides
    public MyPopupWindow popupWindow(UpLoadPortraitActivity activity) {
        View view = LayoutInflater.from(activity).inflate(R.layout.upload_portrait_pop, null);
        MyPopupWindow.Builder builder = new MyPopupWindow.Builder(view, activity);
        builder.animationStyle(R.style.pop_animation);
        view.findViewById(R.id.qcrode_tv).setOnClickListener(activity);
        view.findViewById(R.id.cancel_tv).setOnClickListener(activity);
        view.findViewById(R.id.pic_camera_tv).setOnClickListener(activity);
        return builder.build();
    }

    @ActivityScope
    @Provides
    public BaseActivity activity(UpLoadPortraitActivity activity) {
        return activity;
    }

    @ActivityScope
    @Provides
    public MyBaseModel myBaseModel(UpLoadPortraitActivity activity) {
        return new MyBaseModel(activity.getApplication()) {
            @Override
            public BaseView getBaseView() {
                return activity;
            }

            @Override
            public BaseActivity getMyActivity() {
                return activity;
            }
        };
    }
}
