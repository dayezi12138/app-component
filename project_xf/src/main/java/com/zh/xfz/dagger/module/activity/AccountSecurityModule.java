package com.zh.xfz.dagger.module.activity;

import android.view.LayoutInflater;
import android.view.View;

import com.zh.xfz.R;
import com.zh.xfz.business.activity.AccountSecurityActivity;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.MyBaseModel;
import core.app.zh.com.core.view.MyPopupWindow;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/12/5
 * description:
 */
@Module
public class AccountSecurityModule {

    @ActivityScope
    @Provides
    public MyPopupWindow popupWindow(AccountSecurityActivity activity) {
        View view = LayoutInflater.from(activity).inflate(R.layout.view_company, null);
        view.findViewById(R.id.sure_btn).setOnClickListener(activity);
        view.findViewById(R.id.cancel_btn).setOnClickListener(activity);
        MyPopupWindow.Builder builder = new MyPopupWindow.Builder(view, activity);
        builder.animationStyle(R.style.pop_animation);
        return builder.build();
    }
    @ActivityScope
    @Provides
    public BaseActivity activity(AccountSecurityActivity activity) {
        return activity;
    }

    @ActivityScope
    @Provides
    public MyBaseModel myBaseModel(BaseActivity activity) {
        return new MyBaseModel(activity.getApplication()) {
            @Override
            public BaseView getBaseView() {
                return activity;
            }

            @Override
            public BaseActivity getMyActivity() {
                return activity.getMyActivity();
            }
        };
    }
}
