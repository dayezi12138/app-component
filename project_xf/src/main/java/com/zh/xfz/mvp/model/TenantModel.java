package com.zh.xfz.mvp.model;

import com.blankj.utilcode.util.StringUtils;
import com.zh.xfz.api.MyService;
import com.zh.xfz.bean.activity.Account;
import com.zh.xfz.bean.activity.Industry;
import com.zh.xfz.bean.fragment.ApplyTenant;
import com.zh.xfz.bean.fragment.BusinessBean;
import com.zh.xfz.bean.fragment.TenantMember;
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

import static com.zh.xfz.constans.Constants.PAGEINDEX;
import static com.zh.xfz.constans.Constants.PAGESIZE;
import static com.zh.xfz.constans.RequestParamsConstant.ID;
import static com.zh.xfz.constans.RequestParamsConstant.INDUSTRY_IDS;
import static com.zh.xfz.constans.RequestParamsConstant.PAGE_INDEX;
import static com.zh.xfz.constans.RequestParamsConstant.PAGE_SIZE;
import static com.zh.xfz.constans.RequestParamsConstant.SEARCH;
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

    private int pageSize = PAGESIZE;
    private int pageIndex = PAGEINDEX;

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
     * 所有商户列表
     *
     * @param search
     * @param successListener
     */
    public void getTenantPageList(String search, ObservableListener.SuccessListener<Data<List<BusinessBean>>> successListener) {
        Map<String, Object> params = new HashMap<>();
        params.put(SEARCH, StringUtils.isEmpty(search) ? "" : search);
        params.put(USER_ID, loginHandler.getCurrentUserId());
        params.put(TIME_STAMP, AndroidUtils.getUUID());
        params.put(PAGE_SIZE, pageSize);
        params.put(PAGE_INDEX, pageIndex);
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(false).build(BaseObservable.class);
        myService.getTenantPageList(params).subscribe(observable);
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
        params.put(USER_ID, loginHandler.getCurrentUserId());
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
    public void createTenant(String tenantName, String industryIDs, ObservableListener.SuccessListener<Data<Account.TenantBean>> successListener) {
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
     *
     * @param tenantName
     * @param successListener
     */
    public void existTenantName(String tenantName, ObservableListener.SuccessListener successListener) {
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(false).build(BaseObservable.class);
        myService.existTenantName(tenantName, String.valueOf(loginHandler.getCurrentUserId()), AndroidUtils.getUUID()).subscribe(observable);
    }

    /**
     * 行业列表
     *
     * @param successListener
     */
    public void getIndustry(ObservableListener.SuccessListener<Data<List<Industry>>> successListener) {
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(true).build(BaseObservable.class);
        myService.getIndustry().subscribe(observable);
    }

    /**
     * 获取申请/邀请列表
     *
     * @param id
     * @param type
     * @param successListener
     */
    public void getApplyList(String id, String type, ObservableListener.SuccessListener<Data<List<ApplyTenant>>> successListener) {
        Map<String, Object> params = new HashMap<>();
        params.put(TYPE, type);
        params.put(ID, id);
        params.put(PAGE_INDEX, pageIndex);
        params.put(PAGE_SIZE, pageSize);
        params.put(USER_ID, loginHandler.getCurrentUserId());
        params.put(TIME_STAMP, AndroidUtils.getUUID());
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(false).build(BaseObservable.class);
        myService.getApplyList(params).subscribe(observable);
    }

    /**
     * 获取租户成员
     *
     * @param tenantid
     * @param successListener
     */
    public void getTenantUser(String tenantid, ObservableListener.SuccessListener<Data<List<TenantMember>>> successListener) {
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(true).build(BaseObservable.class);
        myService.getTenantUser(loginHandler.getCurrentUserId(), AndroidUtils.getUUID(), tenantid).subscribe(observable);
    }


    public void applyOperate(String type, String id, ObservableListener.SuccessListener<Data<Object>> successListener) {
        Map<String, Object> params = new HashMap<>();
        params.put(USER_ID, loginHandler.getCurrentUserId());
        params.put(TIME_STAMP, AndroidUtils.getUUID());
        params.put(TYPE, type);
        params.put(ID, id);
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(true).build(BaseObservable.class);
        myService.applyOperate(params).subscribe(observable);
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
