package com.zh.xfz.mvp.contract;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;

/**
 * author : dayezi
 * data :2019/12/6
 * description:短信模块
 */
public interface SmsCodeContract {
    interface SendSmsUI extends BaseView {
        void sendSuccess();
    }

    interface UpdatePhoneUI extends BaseView {
        void failSend();

        void sendSuccess();
    }

    interface Presenter extends IPresenter {
        void getCode(String phone, int status);
    }
}
