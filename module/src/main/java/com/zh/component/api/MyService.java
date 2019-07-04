package com.zh.component.api;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * author : dayezi
 * data :2019/6/28
 * description:
 */
public interface MyService {

    @GET("v7/score")
    Observable<Object> ov();
}
