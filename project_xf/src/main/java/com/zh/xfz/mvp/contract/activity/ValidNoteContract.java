package com.zh.xfz.mvp.contract.activity;

import com.zh.xfz.bean.activity.Account;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;

/**
 * author : dayezi
 * data :2019/7/24
 * description:
 */
public interface ValidNoteContract {

    interface ValidNoteUI extends BaseView {
        void success();

        @Deprecated
        void loginOrRegisterSuccess(Account account, String userId);

        void registerSuccess(Account account, String userId);
    }

    @Deprecated
    interface Presenter extends IPresenter {
        void getCode(String phone, boolean exist);

        void loginOrRegister(String mobile, String code, boolean register);

    }
}
