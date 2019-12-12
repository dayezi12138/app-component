package com.zh.xfz.mvp.contract;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;

/**
 * author : dayezi
 * data :2019/12/6
 * description:
 */
public interface SmsCodeContract {

    interface SmsCodeUI extends BaseView {
        void loginOrRegister(boolean isRegister);
    }

    interface Presenter extends IPresenter {
        void getCode(String phone, int status);
    }
}
