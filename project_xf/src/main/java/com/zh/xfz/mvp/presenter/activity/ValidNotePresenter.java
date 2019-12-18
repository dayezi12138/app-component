package com.zh.xfz.mvp.presenter.activity;

import com.blankj.utilcode.util.ToastUtils;
import com.zh.xfz.bean.activity.Account;
import com.zh.xfz.bean.other.Data;
import com.zh.xfz.mvp.contract.activity.ValidNoteContract;
import com.zh.xfz.mvp.model.activity.ValidCodeModel;
import com.zh.xfz.utils.IM.IMConnectCallBack;
import com.zh.xfz.utils.IM.IMUtils;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.listener.observable.ObservableListener;

/**
 * author : dayezi
 * data :2019/7/24
 * description:
 */
@Deprecated
public class ValidNotePresenter extends BasePresenter<ValidNoteContract.ValidNoteUI> implements ValidNoteContract.Presenter {
    private ValidCodeModel model;

    @Inject
    public ValidNotePresenter(ValidCodeModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void getCode(String phone, boolean exist) {
        model.getCode(phone, !exist ? 1 : 2, validSmsCode -> {
            if (validSmsCode.getCode() == 0) {
                ToastUtils.showShort(validSmsCode.getMsg());
                view.get().success();
            } else view.get().showMsg(validSmsCode.getMsg());

        });
    }

    @Override
    public void loginOrRegister(String mobile, String code, boolean register) {
        model.loginOrRegister(mobile, code, register, (ObservableListener.SuccessListener<Data<Account>>) accountData -> {
            if (accountData.getCode() == 0)
                IMUtils.connect(accountData.getRes().getToken(), new IMConnectCallBack() {
                    @Override
                    public void success(String userId) {

                        view.get().loginOrRegisterSuccess(accountData.getRes(),userId);
                    }

                    @Override
                    public void fail(String msg) {
                        view.get().showMsg(msg);
                    }
                });
            else view.get().error(accountData.getMsg());
        });
    }
}