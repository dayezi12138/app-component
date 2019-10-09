package com.zh.xfz.mvp.presenter.activity;

import com.zh.xfz.mvp.contract.activity.LoginContract;
import com.zh.xfz.mvp.model.activity.LoginModel;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;

/**
 * author : dayezi
 * data :2019/7/18
 * description:
 */
public class LoginPresenter extends BasePresenter<LoginContract.LoginUI> implements LoginContract.Presenter {
    private LoginModel model;

    @Inject
    public LoginPresenter(LoginModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void register(String account, String password, String code) {
//        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password)) {
//            view.get().showMsg(model.getMyBaseModel().getMyActivity().getResources().getString(R.string.act_normal_login_error_str));
//            return;
//        }
//        try {
//            model.login(account, password, null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

//    @Override
//    public void validAccount(String account) {
//        model.validAccount(account, new ObservableListener.SuccessListener<ValidAccount>() {
//            @Override
//            public void onSuccess(ValidAccount validAccount) {
//
//            }
//        });
//    }
}
