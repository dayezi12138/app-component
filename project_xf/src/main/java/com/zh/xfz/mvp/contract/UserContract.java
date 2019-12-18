package com.zh.xfz.mvp.contract;

import java.io.File;
import java.util.Map;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;

/**
 * author : dayezi
 * data :2019/12/13
 * description:用户模块
 */
public interface UserContract {

    interface AccountSecurityUI extends BaseView {
        void bindWXSuccess();

        void relieveWXSuccess();
    }

    interface Presenter extends IPresenter {
        void validAccount(String account);

        void wxLogin(String code);

        void login(String account, String password);

        void doSMS(String mobile, String code, boolean register);

        void wxRegister(Map<String, String> params);

        void bindWxOpenID(String code);

        void relieveWXBind();

        void getTargetUserInfo(String userId);

        void updatePassword(String newPassword1, String newPassword2);

        void updatePersonName(String name, String icon);

        void updateMobile(String mobile, String code);

        void uploadImg(File file);

    }
}
