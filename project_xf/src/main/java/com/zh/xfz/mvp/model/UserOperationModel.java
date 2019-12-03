package com.zh.xfz.mvp.model;

import com.blankj.utilcode.util.LogUtils;
import com.zh.xfz.api.MyService;
import com.zh.xfz.bean.activity.Account;
import com.zh.xfz.bean.activity.TargetUserInfo;
import com.zh.xfz.bean.activity.UserInfo;
import com.zh.xfz.bean.activity.ValidSmsCode;
import com.zh.xfz.bean.other.Data;
import com.zh.xfz.utils.AndroidUtils;
import com.zh.xfz.utils.LoginUtils;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.MyBaseModel;
import core.app.zh.com.core.bean.BaseObservable;
import core.app.zh.com.core.provider.ObservableProvider;
import core.app.zh.com.core.listener.GetMyBaseModelListener;
import core.app.zh.com.core.listener.observable.ObservableListener;

/**
 * author : dayezi
 * data :2019/9/25
 * description:
 */
public class UserOperationModel implements GetMyBaseModelListener {

    @Inject
    BaseActivity activity;
    @Inject
    MyService myService;

    @Inject
    MyBaseModel myBaseModel;

    @Inject
    public UserOperationModel() {
    }

    public void getTargetUserInfo(String targetid, ObservableListener.SuccessListener<Data<TargetUserInfo>> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("userid", LoginUtils.getUserId());
        params.put("timeStamp", AndroidUtils.getUUID());
        params.put("targetid", targetid);
        try {
            BaseObservable observable = new ObservableProvider(activity, listener).showDialog(false).build(BaseObservable.class);
            myService.getTargetUserInfo(params).subscribe(observable);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
            e.printStackTrace();
        }
    }

    public void getUserInfo(String account, ObservableListener.SuccessListener<Data<UserInfo>> listener) {
        try {
            BaseObservable observable = new ObservableProvider(activity, listener).build(BaseObservable.class);
            myService.getUserInfo(account, AndroidUtils.getUUID()).subscribe(observable);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
            e.printStackTrace();
        }
    }

    public void updatePassWord(String oldPassword, String newPassword1, String newPassword2, ObservableListener.SuccessListener<Data<Object>> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("userid", LoginUtils.getUserId());
        params.put("timeStamp", AndroidUtils.getUUID());
        params.put("newpass1", newPassword1);
        params.put("newpass2", newPassword2);
        params.put("pass", oldPassword == null ? "" : oldPassword);
        try {
            BaseObservable observable = new ObservableProvider(activity, listener).build(BaseObservable.class);
            myService.updatePassWord(params).subscribe(observable);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
            e.printStackTrace();
        }
    }

    public void getCode(String phone, int type, ObservableListener.SuccessListener<ValidSmsCode> successListener) {
        try {
            BaseObservable observable = new ObservableProvider(activity, successListener).showDialog(false).build(BaseObservable.class);
            myService.getCode(phone, type).subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void register(String account, String code, ObservableListener.SuccessListener<Data<Account>> successListener) {
        try {
            BaseObservable observable = new ObservableProvider(activity, successListener).build(BaseObservable.class);
            myService.register(account, code, "", "", AndroidUtils.getUUID()).subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void login(String account, String password, ObservableListener.SuccessListener<Data<Account>> successListener) {
        try {
            BaseObservable observable = new ObservableProvider(activity, successListener).build(BaseObservable.class);
            myService.login(account, password, AndroidUtils.getUUID()).subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void forgetPassWord(Map<String, String> params, ObservableListener.SuccessListener<Data<Object>> successListener) {
        try {
            BaseObservable observable = new ObservableProvider(activity, successListener).build(BaseObservable.class);
            myService.forgetPassWord(params).subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public MyBaseModel getMyBaseModel() {
        return myBaseModel;
    }
}
