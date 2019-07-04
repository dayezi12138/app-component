package com.zh.component1.api;

import java.util.Map;

import core.app.zh.com.core.annotation.ApiAnnotation;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * author : dayezi
 * data :2019/7/1
 * description:
 */
public interface ModuleSevice {

    @ApiAnnotation("计划列表")
    @GET("ProduceReceipt/GetPlanPager")
    Observable<Object> getPlanPager(@QueryMap Map<String, Object> map);
}
