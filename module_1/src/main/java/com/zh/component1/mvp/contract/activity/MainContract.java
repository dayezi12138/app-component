package com.zh.component1.mvp.contract.activity;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;

/**
 * author : dayezi
 * data :2019/7/1
 * description:
 */
public interface MainContract {

    interface MainUI extends BaseView {
        void success();
    }

    interface Presenter extends IPresenter {
        void test();
    }
}
