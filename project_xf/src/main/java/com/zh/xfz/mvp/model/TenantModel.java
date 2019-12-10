package com.zh.xfz.mvp.model;

import com.zh.xfz.api.MyService;
import com.zh.xfz.bean.fragment.BusinessBean;
import com.zh.xfz.bean.other.Data;
import com.zh.xfz.utils.AndroidUtils;
import com.zh.xfz.utils.LoginUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseModel;
import core.app.zh.com.core.bean.BaseObservable;
import core.app.zh.com.core.listener.GetMyBaseModelListener;
import core.app.zh.com.core.listener.observable.ObservableListener;
import core.app.zh.com.core.provider.ObservableProvider;

/**
 * author : dayezi
 * data :2019/12/9
 * description:
 */
public class TenantModel implements GetMyBaseModelListener {

    @Inject
    MyService myService;

    @Inject
    MyBaseModel myBaseModel;

    private int pageSize = 25;
    private int pageIndex = 1;


    @Inject
    public TenantModel() {
    }

    @Override
    public MyBaseModel getMyBaseModel() {
        return myBaseModel;
    }


    public void getMyTenantListPage(Map<String, Object> params, ObservableListener.SuccessListener<Data<List<BusinessBean>>> successListener) {
        try {
            params.put("userid", LoginUtils.getUserId());
            params.put("timeStamp", AndroidUtils.getUUID());
            params.put("pageSize", pageSize);
            params.put("pageIndex", pageIndex);
            BaseObservable observable = new ObservableProvider(myBaseModel.getMyActivity(), successListener).showDialog(false).build(BaseObservable.class);
            myService.getMyTenantListPage(params).subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void applyTenant(String tenantid, int type, ObservableListener.SuccessListener<Data<Object>> successListener) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("userid", LoginUtils.getUserId());
            params.put("timeStamp", AndroidUtils.getUUID());
            params.put("tenantid", tenantid);
            params.put("type", type);
            BaseObservable observable = new ObservableProvider(myBaseModel.getMyActivity(), successListener).showDialog(false).build(BaseObservable.class);
            myService.applyTenant(params).subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
