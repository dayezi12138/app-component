package com.zh.xfz.mvp.model;

import com.zh.xfz.api.MyService;
import com.zh.xfz.bean.activity.ValidSmsCode;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.MyBaseModel;
import core.app.zh.com.core.bean.BaseObservable;
import core.app.zh.com.core.listener.GetMyBaseModelListener;
import core.app.zh.com.core.listener.observable.ObservableListener;
import core.app.zh.com.core.provider.ObservableProvider;

/**
 * author : dayezi
 * data :2019/12/6
 * description:
 */
public class SmsCodeModel implements GetMyBaseModelListener {
    @Inject
    BaseActivity activity;
    @Inject
    MyService myService;

    @Inject
    MyBaseModel myBaseModel;
    @Inject
    ObservableProvider observableProvider;

    @Inject
    public SmsCodeModel() {
    }

    /**
     * 发送验证码
     * @param phone
     * @param status
     * @param successListener
     */
    public void getCode(String phone, int status, ObservableListener.SuccessListener<ValidSmsCode> successListener) {
        try {
            BaseObservable observable = observableProvider.successListener(successListener).showDialog(false).build(BaseObservable.class);
            myService.getCode(phone, status).subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public MyBaseModel getMyBaseModel() {
        return myBaseModel;
    }
}
