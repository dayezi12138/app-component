package com.zh.xfz.mvp.contract.activity;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;

/**
 * author : dayezi
 * data :2019/7/18
 * description:
 */
public interface LoginContract {

    interface LoginUI extends BaseView {

    }

    interface Presenter extends IPresenter {
        void register(String account, String password, String code);

//        void validAccount(String account);
    }
}
