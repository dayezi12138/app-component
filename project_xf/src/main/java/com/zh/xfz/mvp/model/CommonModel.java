package com.zh.xfz.mvp.model;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.MyBaseModel;

/**
 * author : dayezi
 * data :2019/9/24
 * description:
 */
public class CommonModel extends MyBaseModel {
    private BaseActivity activity;

    @Inject
    public CommonModel(BaseActivity activity) {
        super(activity.getApplication());
        this.activity = activity;
    }

    @Override
    public BaseView getBaseView() {
        return activity;
    }

    @Override
    public BaseActivity getMyActivity() {
        return activity;
    }
}
