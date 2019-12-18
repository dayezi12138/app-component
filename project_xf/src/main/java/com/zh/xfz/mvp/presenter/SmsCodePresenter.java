package com.zh.xfz.mvp.presenter;

import com.zh.xfz.business.activity.WXLoginActivity;
import com.zh.xfz.constans.Constants;
import com.zh.xfz.mvp.contract.SmsCodeContract;
import com.zh.xfz.mvp.model.SmsCodeModel;
import com.zh.xfz.utils.ResponseStatusUtils;

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
            if (ResponseStatusUtils.notMsgFail(validSmsCode.getCode()) && model.getMyBaseModel().getMyActivity() instanceof WXLoginActivity) {
                getCode(phone, Constants.SMS_STATUS_WX_BIND_CODE);
                return;
            }
            if (view.get() instanceof SmsCodeContract.SendSmsUI) {
                SmsCodeContract.SendSmsUI wxLoginUI = (SmsCodeContract.SendSmsUI) view.get();
                if (ResponseStatusUtils.isNormalSuccess(validSmsCode.getCode()))
                    wxLoginUI.sendSuccess();
                else view.get().showMsg(validSmsCode.getMsg());
                return;
            }
            if (view.get() instanceof SmsCodeContract.UpdatePhoneUI) {
                SmsCodeContract.UpdatePhoneUI updatePhoneUI = (SmsCodeContract.UpdatePhoneUI) view.get();
                if (ResponseStatusUtils.isNormalSuccess(validSmsCode.getCode()))
                    updatePhoneUI.sendSuccess();
                else view.get().showMsg(validSmsCode.getMsg());
                return;
            }
        });
    }
}
