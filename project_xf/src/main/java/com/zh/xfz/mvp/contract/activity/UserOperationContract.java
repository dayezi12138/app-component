package com.zh.xfz.mvp.contract.activity;

import com.zh.xfz.bean.activity.TargetUserInfo;
import com.zh.xfz.bean.activity.UserInfo;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;
import q.rorbin.badgeview.Badge;

/**
 * author : dayezi
 * data :2019/9/25
 * description:
 */
public interface UserOperationContract {

    interface AddPasswordUI extends BaseView {
        void success();
    }

    interface TargetUserInfoUI extends BaseView {
        void targetUserInfoSuccess(TargetUserInfo userInfo);
    }

    interface UserOperationUI extends BaseView {
        default void userInfoSuccess(UserInfo userInfo) {

        }

    }
    interface UpdatePersonNameUI extends BaseView{
      void successData();
    }

    interface Presenter extends IPresenter {
        void getUserInfo(String account);

        void updatePassWord(String oldPassword, String newPassword1, String newPassword2);

        void refreshUserInfo();

        void initRongIM(Badge badge);

        void getTargetUserInfo(String targetId);

        void register(String mobile, String code);

        void getCode(String phone, boolean isRegister);

        void login(String account, String password);

        void forgetPassWord(String account, String newpass1, String newpass2, String code);

        void updatePersonName(String chineseName);
    }
}
