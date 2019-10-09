package com.zh.xfz.mvp.contract.activity;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;

/**
 * author : dayezi
 * data :2019/7/24
 * description:
 */
public interface AccountLoginContract {

    interface AccountLoginUI extends BaseView {

    }

    interface Presenter extends IPresenter {
        void validAccount(String account);
    }
}
