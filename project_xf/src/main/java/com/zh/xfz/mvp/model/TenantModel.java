package com.zh.xfz.mvp.model;

import com.zh.xfz.api.MyService;
import com.zh.xfz.bean.activity.Industry;
import com.zh.xfz.bean.fragment.BusinessBean;
import com.zh.xfz.bean.other.Data;
import com.zh.xfz.utils.AndroidUtils;
import com.zh.xfz.utils.LoginHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseModel;
import core.app.zh.com.core.bean.BaseObservable;
import core.app.zh.com.core.listener.GetMyBaseModelListener;
import core.app.zh.com.core.listener.observable.ObservableListener;
import core.app.zh.com.core.provider.ObservableProvider;

import static com.zh.xfz.constans.RequestParamsConstant.INDUSTRY_IDS;
import static com.zh.xfz.constans.RequestParamsConstant.PAGE_INDEX;
import static com.zh.xfz.constans.RequestParamsConstant.PAGE_SIZE;
import static com.zh.xfz.constans.RequestParamsConstant.TENANT_ID;
import static com.zh.xfz.constans.RequestParamsConstant.TENANT_NAME;
import static com.zh.xfz.constans.RequestParamsConstant.TIME_STAMP;
import static com.zh.xfz.constans.RequestParamsConstant.TYPE;
import static com.zh.xfz.constans.RequestParamsConstant.USER_ID;

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

    @Inject
    ObservableProvider observableProvider;

    private int pageSize = 25;
    private int pageIndex = 1;

    @Inject
    LoginHandler loginHandler;


    @Inject
    public TenantModel() {
    }

    @Override
    public MyBaseModel getMyBaseModel() {
        return myBaseModel;
    }


    /**
     * 获取公司列表
     *
     * @param params
     * @param successListener
     */
    public void getMyTenantListPage(Map<String, Object> params, ObservableListener.SuccessListener<Data<List<BusinessBean>>> successListener) {
        params.put(USER_ID, loginHandler.getCurrentUserId());
        params.put(TIME_STAMP, AndroidUtils.getUUID());
        params.put(PAGE_SIZE, pageSize);
        params.put(PAGE_INDEX, pageIndex);
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(false).build(BaseObservable.class);
        myService.getMyTenantListPage(params).subscribe(observable);
    }

    /**
     * 商户邀请用户/用户申请加入商户
     *
     * @param tenantid
     * @param type
     * @param successListener
     */
    public void applyTenant(String tenantid, int type, ObservableListener.SuccessListener<Data<Object>> successListener) {
        Map<String, Object> params = new HashMap<>();
        params.put(USER_ID,loginHandler.getCurrentUserId());
        params.put(TIME_STAMP, AndroidUtils.getUUID());
        params.put(TENANT_ID, tenantid);
        params.put(TYPE, type);
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(false).build(BaseObservable.class);
        myService.applyTenant(params).subscribe(observable);
    }

    /**
     * 创建公司
     *
     * @param tenantName
     * @param industryIDs
     * @param successListener
     */
    public void createTenant(String tenantName, String industryIDs, ObservableListener.SuccessListener<Data<Object>> successListener) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(USER_ID, String.valueOf(loginHandler.getCurrentUserInfo().getUserId()));
        paramMap.put(TENANT_NAME, tenantName);
        paramMap.put(INDUSTRY_IDS, industryIDs);
        paramMap.put(TIME_STAMP, AndroidUtils.getUUID());
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(true).build(BaseObservable.class);
        myService.createTenant(paramMap).subscribe(observable);
    }

    /**
     * 验证公司名是否存在
     * @param tenantName
     * @param successListener
     */
    public void existTenantName(String tenantName, ObservableListener.SuccessListener successListener) {
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(false).build(BaseObservable.class);
        myService.existTenantName(tenantName, String.valueOf(loginHandler.getCurrentUserId()), AndroidUtils.getUUID()).subscribe(observable);
    }

    /**
     * 行业列表
     * @param successListener
     */
    public void getIndustry(ObservableListener.SuccessListener<Data<List<Industry>>> successListener) {
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(true).build(BaseObservable.class);
        myService.getIndustry().subscribe(observable);
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
