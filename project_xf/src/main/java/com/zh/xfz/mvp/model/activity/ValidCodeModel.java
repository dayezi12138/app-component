package com.zh.xfz.mvp.model.activity;

import com.zh.xfz.api.MyService;
import com.zh.xfz.bean.activity.ValidSmsCode;
import com.zh.xfz.utils.AndroidUtils;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.MyBaseModel;
import core.app.zh.com.core.bean.BaseObservable;
import core.app.zh.com.core.bean.ObservableProvider;
import core.app.zh.com.core.listener.GetMyBaseModelListener;
import core.app.zh.com.core.listener.observable.ObservableListener;

/**
 * author : dayezi
 * data :2019/7/24
 * description:
 */
public class ValidCodeModel implements GetMyBaseModelListener {
    private BaseActivity activity;
    private MyService myService;

    @Inject
    public ValidCodeModel(BaseActivity activity, MyService myService) {
        this.activity = activity;
        this.myService = myService;
    }

    public void getCode(String phone, int type, ObservableListener.SuccessListener<ValidSmsCode> successListener) {
        try {
            BaseObservable observable = new ObservableProvider(activity, successListener).showDialog(false).build(BaseObservable.class);
            myService.getCode(phone, type).subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginOrRegister(String account, String code, boolean register, ObservableListener.SuccessListener successListener) {
        try {
            BaseObservable observable = new ObservableProvider(activity, successListener).showDialog(false).build(BaseObservable.class);
            if (!register)
                myService.register(account, code, "", "", AndroidUtils.getUUID()).subscribe(observable);
            else
                myService.codeLogin(account, code, AndroidUtils.getUUID()).subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public MyBaseModel getMyBaseModel() {
        return new MyBaseModel(activity.getApplication()) {
            @Override
            public BaseActivity getMyActivity() {
                return activity;
            }

            @Override
            public BaseView getBaseView() {
                return (activity != null && activity instanceof BaseView) ? activity : null;
            }
        };
    }
}
