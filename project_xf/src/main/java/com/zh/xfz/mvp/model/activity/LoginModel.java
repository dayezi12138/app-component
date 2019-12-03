package com.zh.xfz.mvp.model.activity;

import android.app.Application;

import com.blankj.utilcode.util.LogUtils;
import com.zh.xfz.api.MyService;
import com.zh.xfz.bean.activity.ValidAccount;
import com.zh.xfz.utils.AndroidUtils;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.MyBaseModel;
import core.app.zh.com.core.bean.BaseObservable;
import core.app.zh.com.core.bean.MyObservable;
import core.app.zh.com.core.provider.ObservableProvider;
import core.app.zh.com.core.listener.GetMyBaseModelListener;
import core.app.zh.com.core.listener.observable.ObservableListener;

/**
 * author : dayezi
 * data :2019/7/18
 * description:
 */
public class LoginModel implements GetMyBaseModelListener {

    private BaseActivity activity;
    private MyService myService;

    @Inject
    public LoginModel(BaseActivity activity, MyService myService) {
        this.activity = activity;
        this.myService = myService;
    }

    public Application getMyApplication() {
        return activity.getApplication();
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

    public void login(String user, String password, MyObservable.OnSuccessListener listener) throws Exception {
        BaseObservable observable = new ObservableProvider(activity, new ObservableListener.SuccessListener() {
            @Override
            public void onSuccess(Object object) {
                LogUtils.e(object);
            }
        }).build(BaseObservable.class);
//        myService.login(user, password).subscribe(observable);
    }

    public void validAccount(String account, ObservableListener.SuccessListener<ValidAccount> successListener) {
        try {
            BaseObservable observable = new ObservableProvider(activity, successListener).showDialog(true).build(BaseObservable.class);
            myService.validAccount(account).subscribe(observable);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
            e.printStackTrace();
        }
    }

    public void getInfo(String account, ObservableListener.SuccessListener successListener) {
        try {
            BaseObservable observable = new ObservableProvider(activity, successListener).build(BaseObservable.class);
            myService.getUserInfo(account, AndroidUtils.getUUID()).subscribe(observable);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
            e.printStackTrace();
        }
    }
}
