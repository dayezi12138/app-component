package com.zh.xfz.mvp.presenter;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.android.tu.loadingdialog.LoadingDailog;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.Account;
import com.zh.xfz.bean.activity.FileResponseData;
import com.zh.xfz.bean.activity.TargetUserInfo;
import com.zh.xfz.bean.other.Data;
import com.zh.xfz.business.activity.InputPasswordActivity;
import com.zh.xfz.business.activity.MainActivity;
import com.zh.xfz.business.activity.UpdatePersonNameActivity;
import com.zh.xfz.business.activity.ValidNoteActivity;
import com.zh.xfz.business.activity.WXLoginActivity;
import com.zh.xfz.mvp.contract.UserContract;
import com.zh.xfz.mvp.model.UserModel;
import com.zh.xfz.utils.IM.IMConnectCallBack;
import com.zh.xfz.utils.IM.IMUtils;
import com.zh.xfz.utils.LoginHandler;
import com.zh.xfz.utils.ResponseStatusUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.listener.observable.ObservableListener;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author : dayezi
 * data :2019/12/13
 * description:
 */
public class UserPresenter extends BasePresenter<BaseView> implements UserContract.Presenter {
    private UserModel model;
    @Inject
    LoginHandler loginHandler;

    @Inject
    public UserPresenter(UserModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void validAccount(String account) {
        model.validAccount(account, validAccount -> {
            if (ResponseStatusUtils.isSpecialSuccess(validAccount.getCode()))
                ARouter.getInstance().build(ValidNoteActivity.AROUTER_PATH)
                        .withString(ValidNoteActivity.ACCOUNT_KEY, account)
                        .withBoolean(ValidNoteActivity.EXIST_ACCOUNT_KEY, false)
                        .navigation();
            else if (ResponseStatusUtils.isNormalSuccess(validAccount.getCode()))
                ARouter.getInstance().build(InputPasswordActivity.AROUTER_PATH)
                        .withString(InputPasswordActivity.ACCOUNT_KEY, account)
                        .navigation();
            else view.get().showMsg(validAccount.getMsg());
        });
    }

    @Override
    public void wxLogin(String code) {
        Dialog dialog = dialog();
        if (dialog != null && !dialog.isShowing()) dialog.show();
        model.wxLogin(code, accountData -> {
            if (ResponseStatusUtils.isNormalSuccess(accountData.getCode())) {
                Account account = accountData.getRes();
                if (!StringUtils.isEmpty(accountData.getRes().getOpenid())) {
                    ARouter.getInstance().build(WXLoginActivity.AROUTER_PATH)
                            .withString(WXLoginActivity.WX_OPENID_KEY, account.getOpenid())
                            .withString(WXLoginActivity.WX_UNIONID_KEY, account.getUnionid())
                            .withString(WXLoginActivity.WX_ACCESS_TOKEN_KEY, account.getAccess_token())
                            .navigation();
                    closeDialog(dialog);
                } else
                    IMUtils.connect(account.getToken(), new IMConnectCallBackImpl(account, dialog));
            } else {
                view.get().showMsg(accountData.getMsg());
                closeDialog(dialog);
            }
        });
    }

    @Override
    public void login(String account, String password) {
        model.login(account, password, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode())) {
                Account user = data.getRes();
                IMUtils.connect(data.getRes().getToken(), new IMConnectCallBackImpl(user, true));
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void doSMS(String mobile, String code, boolean isLogin) {
        ObservableListener.SuccessListener<Data<Account>> successListener = data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode())) {
                Account user = data.getRes();
                IMUtils.connect(data.getRes().getToken(), new IMConnectCallBackImpl(user, true));
            } else view.get().showMsg(data.getMsg());
        };
        if (!isLogin) model.smsRegister(mobile, code, successListener);
        else model.smsLogin(mobile, code, successListener);
    }

    @Override
    public void wxRegister(Map<String, String> params) {
        model.wxRegister(params, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode())) {
                Account user = data.getRes();
//                loginHandler.saveLoginInfo(user);
                IMUtils.connect(user.getToken(), new IMConnectCallBackImpl(user, true));
            }
        });
    }

    @Override
    public void bindWxOpenID(String code) {
        model.bindWxOpenID(code, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode()) && view.get() instanceof UserContract.AccountSecurityUI) {
                UserContract.AccountSecurityUI ui = (UserContract.AccountSecurityUI) view.get();
                view.get().showMsg(getMyActivity().getResources().getString(R.string.act_wx_bind_success_toast_msg));
                com.zh.xfz.db.bean.UserInfo userInfo = loginHandler.getCurrentUserInfo();
                loginHandler.saveBindWX(true, userInfo.getUserId());
                ui.bindWXSuccess();
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void relieveWXBind() {
        model.relieveWXBind(data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode()) && view.get() instanceof UserContract.AccountSecurityUI) {
                UserContract.AccountSecurityUI ui = (UserContract.AccountSecurityUI) view.get();
                view.get().showMsg(getMyActivity().getResources().getString(R.string.act_wx_bind_relieve_toast_msg));
                com.zh.xfz.db.bean.UserInfo userInfo = loginHandler.getCurrentUserInfo();
                loginHandler.saveBindWX(false, userInfo.getUserId());
                ui.relieveWXSuccess();
            }
        });
    }

    @Override
    public void getTargetUserInfo(String userId) {
        model.getTargetUserInfo(userId, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode())) {
                TargetUserInfo targetUserInfo = data.getRes();
                String userName = StringUtils.isEmpty(targetUserInfo.getRemarkName()) ? targetUserInfo.getChineseName() : targetUserInfo.getRemarkName();
                userName = StringUtils.isEmpty(userName) ? targetUserInfo.getMobile() : userName;
                Uri uri = TextUtils.isEmpty(targetUserInfo.getUserIcon()) ? imageTranslateUri(R.drawable.rc_default_portrait) : Uri.parse(targetUserInfo.getUserIcon());
                RongIM.getInstance().refreshUserInfoCache(new UserInfo(userId, userName, uri));
            }
        });
    }

    @Override
    public void updatePassword(String newPassword1, String newPassword2) {
        model.updatePassWord(newPassword1, newPassword2, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode())) {
                view.get().showMsg(model.getMyBaseModel().getMyActivity().getResources().getString(R.string.act_update_success_toast_msg));
                model.getMyBaseModel().getMyActivity().finish();
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void updatePersonName(String name, String icon) {
        model.updatePersonName(name, icon, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode())) {
                com.zh.xfz.db.bean.UserInfo userInfo = loginHandler.getCurrentUserInfo();
                userInfo.setUserIcon(icon);
                userInfo.setChineseName(name);
                loginHandler.updateUserInfo(userInfo);
                String msg = model.getMyBaseModel().getMyActivity() instanceof UpdatePersonNameActivity ? model.getMyBaseModel().getMyActivity().getResources().getString(R.string.act_update_success_msg) : model.getMyBaseModel().getMyActivity().getResources().getString(R.string.act_update_header_image_success_msg);
                view.get().showMsg(msg);
                model.getMyBaseModel().getMyActivity().finish();
            }
        });
    }

    @Override
    public void updateMobile(String mobile, String code) {
        model.updateMobile(mobile, code, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode())) {
                view.get().showMsg(model.getMyBaseModel().getMyActivity().getResources().getString(R.string.act_update_success_msg));
                model.getMyBaseModel().getMyActivity().finish();
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void uploadImg(File file) {
        model.uploadImg(file, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    FileResponseData data = new Gson().fromJson(response.body().string(), FileResponseData.class);
                    if (data.getCode() == 0) {
                        com.zh.xfz.db.bean.UserInfo userInfo = loginHandler.getCurrentUserInfo();
                        updatePersonName(StringUtils.isEmpty(userInfo.getChineseName()) ? "" : userInfo.getChineseName(), data.getOriginalUrl());
                    } else
                        model.getMyBaseModel().getBaseView().showMsg(model.getMyBaseModel().getMyActivity().getResources().getString(R.string.act_update_header_fail_msg));
                } catch (IOException e) {
                    e.printStackTrace();
                    view.get().showMsg(e.getMessage());
                    LogUtils.e(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.get().showMsg(t.getMessage());
            }
        });
    }

    public Uri imageTranslateUri(int resId) {
        Resources r = model.getMyBaseModel().getMyActivity().getResources();
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(resId) + "/"
                + r.getResourceTypeName(resId) + "/"
                + r.getResourceEntryName(resId));

        return uri;
    }

    private void closeDialog(Dialog dialog) {
        if (dialog != null && !dialog.isShowing()) dialog.dismiss();
    }

    private class IMConnectCallBackImpl implements IMConnectCallBack {
        private Account account;
        private boolean isNormal;
        private Dialog dialog;

        public IMConnectCallBackImpl(Account account, Dialog dialog) {
            this.account = account;
            this.dialog = dialog;
        }

        public IMConnectCallBackImpl(Account account, boolean isNormal) {
            this.account = account;
            this.isNormal = isNormal;
        }

        @Override
        public void success(String userid) {
            closeDialog(dialog);
            loginHandler.saveLoginInfo(account);
            ARouter.getInstance().build(MainActivity.AROUTER_PATH).navigation();
//            if (!isNormal) {
//                ARouter.getInstance().build(MainActivity.AROUTER_PATH).navigation();
//            } else {
//                if (account.getTenant() == null || account.getTenant().isEmpty())
//                    ARouter.getInstance().build(BusinessListActivity.AROUTER_PATH)
//                            .withString(BusinessListActivity.FRAGMENT_CLASS_KEY, BusinessListFragment.class.getName())
//                            .navigation();
//                else ARouter.getInstance().build(MainActivity.AROUTER_PATH).navigation();
//            }
            model.getMyBaseModel().getMyActivity().finish();
        }

        @Override
        public void fail(String msg) {
            closeDialog(dialog);
            if (!isNormal) {
                view.get().showMsg(msg);
            } else view.get().showMsg(msg);
        }
    }

    private Dialog dialog() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(model.getMyBaseModel().getMyActivity())
                .setMessage(model.getMyBaseModel().getMyActivity().getResources().getString(R.string.act_login_ing_msg))
                .setCancelable(false)
                .setCancelOutside(false);
        return loadBuilder.create();
    }
}
