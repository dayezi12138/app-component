package com.zh.xfz.mvp.model;

import com.zh.xfz.api.HelpService;
import com.zh.xfz.bean.activity.LocalApplication;
import com.zh.xfz.bean.fragment.HelpArticle;
import com.zh.xfz.bean.other.Data;

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
import static com.zh.xfz.constans.RequestParamsConstant.PAGE_INDEX;
import static com.zh.xfz.constans.RequestParamsConstant.PAGE_SIZE;

/**
 * author : dayezi
 * data :2019/12/25
 * description:
 */
public class HelpModel implements GetMyBaseModelListener {
    @Inject
    MyBaseModel myBaseModel;
    @Inject
    HelpService helpService;
    @Inject
    ObservableProvider observableProvider;

    private int pageSize = PAGESIZE;
    private int pageIndex = PAGEINDEX;

    @Inject
    public HelpModel() {
    }

    @Override
    public MyBaseModel getMyBaseModel() {
        return myBaseModel;
    }

    /**
     * 分页获取文章数据
     */
    @Deprecated
    public void getArticles(Map<String, Object> reqMap, ObservableListener.SuccessListener<Object> successListener) {
        Map<String, Object> params = new HashMap<>();
        params.put(PAGE_SIZE, pageSize);
        params.put(PAGE_INDEX, pageIndex);
        params.putAll(reqMap);
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(false).build(BaseObservable.class);
        helpService.getArticles(params).subscribe(observable);
    }

    /**
     * 根据应用ID获取文章数据
     *
     * @param reqMap
     * @param successListener
     */
    public void getArticlesByAppID(Map<String, Object> reqMap, ObservableListener.SuccessListener<Data<List<HelpArticle>>> successListener) {
        Map<String, Object> params = new HashMap<>();
        params.put(PAGE_SIZE, pageSize);
        params.put(PAGE_INDEX, pageIndex);
        params.putAll(reqMap);
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(false).build(BaseObservable.class);
        helpService.getArticlesByAppID(params).subscribe(observable);
    }

    /**
     * 获取应用列表
     *
     * @param successListener
     */
    public void getApps(ObservableListener.SuccessListener<Data<List<LocalApplication>>> successListener) {
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(false).build(BaseObservable.class);
        helpService.getApps().subscribe(observable);
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
