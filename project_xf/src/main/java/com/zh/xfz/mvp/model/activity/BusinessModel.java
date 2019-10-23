package com.zh.xfz.mvp.model.activity;

import com.zh.xfz.api.MyService;
import com.zh.xfz.utils.AndroidUtils;
import com.zh.xfz.utils.LoginUtils;

import java.util.HashMap;
import java.util.Map;

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
 * data :2019/7/29
 * description:
 */
public class BusinessModel implements GetMyBaseModelListener {
    private BaseActivity activity;
    private MyService myService;

    @Inject
    public BusinessModel(BaseActivity activity, MyService myService) {
        this.activity = activity;
        this.myService = myService;
    }


    public void getIndustry(ObservableListener.SuccessListener successListener) {
        try {
            BaseObservable observable = new ObservableProvider(activity, successListener).build(BaseObservable.class);
            myService.getIndustry().subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTenant(String tenantName, String industryIDs, ObservableListener.SuccessListener successListener) {
        try {
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("userid", LoginUtils.getUserId());
            paramMap.put("tenantName", tenantName);
            paramMap.put("industryIDs", industryIDs);
            paramMap.put("timeStamp", AndroidUtils.getUUID());
            BaseObservable observable = new ObservableProvider(activity, successListener).build(BaseObservable.class);
            myService.createTenant(paramMap).subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void existTenantName(String tenantName, ObservableListener.SuccessListener successListener) {
        try {

            BaseObservable observable = new ObservableProvider(activity, successListener).build(BaseObservable.class);
            myService.existTenantName(tenantName, LoginUtils.getUserId(), AndroidUtils.getUUID()).subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public MyBaseModel getMyBaseModel() {
        return new MyBaseModel(activity.getApplication()) {
            @Override
            public BaseView getBaseView() {
                return (activity != null && activity instanceof BaseView) ? activity : null;
            }

            @Override
            public BaseActivity getMyActivity() {
                return activity;
            }
        };
    }
}
