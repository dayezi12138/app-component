package com.zh.xfz.mvp.model;

import com.zh.xfz.api.MyService;
import com.zh.xfz.api.UpLoadService;
import com.zh.xfz.bean.activity.Account;
import com.zh.xfz.bean.activity.TargetUserInfo;
import com.zh.xfz.bean.activity.ValidAccount;
import com.zh.xfz.bean.other.Data;
import com.zh.xfz.utils.AndroidUtils;
import com.zh.xfz.utils.LoginHandler;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseModel;
import core.app.zh.com.core.bean.BaseObservable;
import core.app.zh.com.core.listener.GetMyBaseModelListener;
import core.app.zh.com.core.listener.observable.ObservableListener;
import core.app.zh.com.core.provider.ObservableProvider;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Retrofit;

import static com.zh.xfz.constans.RequestParamsConstant.CHINESE_NAME;
import static com.zh.xfz.constans.RequestParamsConstant.CODE;
import static com.zh.xfz.constans.RequestParamsConstant.ICON;
import static com.zh.xfz.constans.RequestParamsConstant.MOBILE;
import static com.zh.xfz.constans.RequestParamsConstant.NEW_PASSWORD;
import static com.zh.xfz.constans.RequestParamsConstant.NEW_PASSWORD_2;
import static com.zh.xfz.constans.RequestParamsConstant.TARGET_ID;
import static com.zh.xfz.constans.RequestParamsConstant.TIME_STAMP;
import static com.zh.xfz.constans.RequestParamsConstant.USER_ID;
import static com.zh.xfz.dagger.module.AppModule.BASE_URL;

/**
 * author : dayezi
 * data :2019/12/13
 * description:用户相关请求模块
 */
public class UserModel implements GetMyBaseModelListener {

    @Inject
    MyBaseModel myBaseModel;

    @Inject
    MyService myService;

    @Inject
    ObservableProvider observableProvider;

    @Inject
    OkHttpClient okHttpClient;

    @Inject
    LoginHandler loginHandler;

    @Inject
    public UserModel() {
    }

    /**
     * 验证用户是否存在
     *
     * @param account
     * @param successListener
     */
    public void validAccount(String account, ObservableListener.SuccessListener<ValidAccount> successListener) {
        myService.validAccount(account).subscribe(observableProvider.successListener(successListener).showDialog(true).build(BaseObservable.class));
    }

    /**
     * 绑定微信
     *
     * @param code
     * @param successListener
     */
    public void bindWxOpenID(String code, ObservableListener.SuccessListener<Data<Object>> successListener) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(USER_ID, String.valueOf(loginHandler.getCurrentUserInfo().getUserId()));
        paramMap.put(TIME_STAMP, AndroidUtils.getUUID());
        paramMap.put(CODE, code);
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(true).build(BaseObservable.class);
        myService.bindWxOpenID(paramMap).subscribe(observable);
    }

    /**
     * 解除绑定
     *
     * @param successListener
     */
    public void relieveWXBind(ObservableListener.SuccessListener<Data<Object>> successListener) {
        try {
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put(USER_ID, String.valueOf(loginHandler.getCurrentUserInfo().getUserId()));
            paramMap.put(TIME_STAMP, AndroidUtils.getUUID());
            BaseObservable observable = observableProvider.successListener(successListener).showDialog(true).build(BaseObservable.class);
            myService.relieveWXBind(paramMap).subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 微信登陆
     *
     * @param code
     * @param successListener
     */
    public void wxLogin(String code, ObservableListener.SuccessListener<Data<Account>> successListener) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(TIME_STAMP, AndroidUtils.getUUID());
        paramMap.put(CODE, code);
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(false).build(BaseObservable.class);
        myService.wxCheckAndLogin(paramMap).subscribe(observable);
    }

    /**
     * 正常登录
     *
     * @param account
     * @param password
     * @param successListener
     */
    public void login(String account, String password, ObservableListener.SuccessListener<Data<Account>> successListener) {
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(true).build(BaseObservable.class);
        myService.login(account, password, AndroidUtils.getUUID()).subscribe(observable);
    }

    /**
     * 微信登陆与注册
     *
     * @param params
     * @param successListener
     */
    public void wxRegister(Map<String, String> params, ObservableListener.SuccessListener<Data<Account>> successListener) {
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(true).build(BaseObservable.class);
        myService.wxRegister(params).subscribe(observable);
    }

    /**
     * 短信登录
     *
     * @param account
     * @param code
     * @param successListener
     */
    public void smsLogin(String account, String code, ObservableListener.SuccessListener<Data<Account>> successListener) {
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(true).build(BaseObservable.class);
        myService.codeLogin(account, code, AndroidUtils.getUUID()).subscribe(observable);
    }

    /**
     * 短信注册用户
     *
     * @param account
     * @param code
     * @param successListener
     */
    public void smsRegister(String account, String code, ObservableListener.SuccessListener<Data<Account>> successListener) {
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(true).build(BaseObservable.class);
        myService.register(account, code, "", "", AndroidUtils.getUUID()).subscribe(observable);
    }

    /**
     * 根据会话ID获取用户信息
     *
     * @param targetid
     * @param listener
     */
    public void getTargetUserInfo(String targetid, ObservableListener.SuccessListener<Data<TargetUserInfo>> listener) {
        Map<String, String> params = new HashMap<>();
        params.put(USER_ID, String.valueOf(loginHandler.getCurrentUserInfo().getUserId()));
        params.put(TIME_STAMP, AndroidUtils.getUUID());
        params.put(TARGET_ID, targetid);
        BaseObservable observable = observableProvider.successListener(listener).showDialog(false).build(BaseObservable.class);
        myService.getTargetUserInfo(params).subscribe(observable);
    }

    /***
     * 更新密码
     * @param newPassword1
     * @param newPassword2
     * @param listener
     */
    public void updatePassWord(String newPassword1, String newPassword2, ObservableListener.SuccessListener<Data<Object>> listener) {
        Map<String, String> params = new HashMap<>();
        params.put(USER_ID, String.valueOf(loginHandler.getCurrentUserInfo().getUserId()));
        params.put(TIME_STAMP, AndroidUtils.getUUID());
        params.put(NEW_PASSWORD, newPassword1);
        params.put(NEW_PASSWORD_2, newPassword2);
        BaseObservable observable = observableProvider.successListener(listener).showDialog(true).build(BaseObservable.class);
        myService.updatePassWord(params).subscribe(observable);
    }

    /***
     * 更新用户名
     * @param name
     * @param icon
     * @param successListener
     */
    public void updatePersonName(String name, String icon, ObservableListener.SuccessListener<Data<Object>> successListener) {
        Map<String, String> params = new HashMap<>();
        params.put(USER_ID, String.valueOf(loginHandler.getCurrentUserInfo().getUserId()));
        params.put(TIME_STAMP, AndroidUtils.getUUID());
        params.put(CHINESE_NAME, name);
        params.put(ICON, icon);
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(true).build(BaseObservable.class);
        myService.updatePersonName(params).subscribe(observable);

    }

    /**
     * 更新手机号码
     *
     * @param mobile
     * @param code
     * @param successListener
     */
    public void updateMobile(String mobile, String code, ObservableListener.SuccessListener<Data<Object>> successListener) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(USER_ID, String.valueOf(loginHandler.getCurrentUserInfo().getUserId()));
        paramMap.put(TIME_STAMP, AndroidUtils.getUUID());
        paramMap.put(MOBILE, mobile);
        paramMap.put(CODE, code);
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(true).build(BaseObservable.class);
        myService.updateMobile(paramMap).subscribe(observable);
    }

    /**
     * 上传头像
     *
     * @param file
     * @param callback
     */
    public void uploadImg(File file, Callback<ResponseBody> callback) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).build();
        UpLoadService upLoadService = retrofit.create(UpLoadService.class);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("stlWidthStr", "200")
                .createFormData("isThumb", "0").createFormData("file", file.getName(), requestBody);
        upLoadService.uploadImg(filePart).enqueue(callback);
    }

    @Override
    public MyBaseModel getMyBaseModel() {
        return myBaseModel;
    }
}
