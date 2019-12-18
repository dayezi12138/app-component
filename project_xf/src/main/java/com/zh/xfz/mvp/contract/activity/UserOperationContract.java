package com.zh.xfz.mvp.contract.activity;

import com.zh.xfz.bean.activity.TargetUserInfo;
import com.zh.xfz.bean.activity.UserInfo;

import java.io.File;
import java.util.Map;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;
import q.rorbin.badgeview.Badge;

/**
 * author : dayezi
 * data :2019/9/25
 * description:
 */
@Deprecated
public interface UserOperationContract {

    interface AddPasswordUI extends BaseView {
        void success();
    }

    interface TargetUserInfoUI extends BaseView {
        void targetUserInfoSuccess(TargetUserInfo userInfo);
    }

    interface AccountSecurityUI extends BaseView {
        void success();

        //        void bindWx(String code);
        void relieve();
    }


    interface UserOperationUI extends BaseView {
        default void userInfoSuccess(UserInfo userInfo) {
        }
    }

    interface UpdatePersonNameUI extends BaseView {
        void successData();
    }

    interface UpdateMobileUI extends BaseView {
        void updateMobileSuccess();
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

        void updatePersonName(String chineseName, String icon);

        void bindWX(String code);

        void wxCheckAndLogin(String code);

        void relieveWXBind();

        void updateMobile(String mobile, String code);

        void wxRegister(Map<String, String> params);

        void uploadImg(File file);

        void wxLogin(String code);
    }
}
