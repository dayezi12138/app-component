package com.zh.xfz.mvp.presenter;

import com.zh.xfz.mvp.contract.SmsCodeContract;
import com.zh.xfz.mvp.model.SmsCodeModel;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.base.BaseView;

/**
 * author : dayezi
 * data :2019/12/6
 * description:
 */
public class SmsCodePresenter extends BasePresenter<BaseView> implements SmsCodeContract.Presenter {
    private SmsCodeModel model;

    @Inject
    public SmsCodePresenter(SmsCodeModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void getCode(String phone, int status) {
        model.getCode(phone, status, validSmsCode -> {
            if (validSmsCode.getCode() == 40004) getCode(phone, 3);
            else if (validSmsCode.getCode() == 0 && view.get() instanceof SmsCodeContract.SmsCodeUI) {
                ((SmsCodeContract.SmsCodeUI) view.get()).loginOrRegister(status != 3 ? true : false);
            } else view.get().showMsg(validSmsCode.getMsg());
        });
    }
}
