package com.zh.xfz.api;

import com.zh.xfz.bean.activity.HelpListBean;
import com.zh.xfz.bean.activity.LocalApplication;
import com.zh.xfz.bean.fragment.HelpArticle;
import com.zh.xfz.bean.other.Data;
import com.zh.xfz.bean.other.HelpData;

import java.util.List;
import java.util.Map;

import core.app.zh.com.core.annotation.ApiAnnotation;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * author : dayezi
 * data :2019/12/25
 * description:
 */
public interface HelpService {

    @ApiAnnotation("获取文章列表")
    @GET("help/getArticles")
    Observable<HelpData<HelpListBean>> getArticles(@QueryMap Map<String, Object> params);

    @ApiAnnotation("根据应用ID分页获取文章数据")
    @GET("help/GetArticlesByAppID")
    Observable<Data<List<HelpArticle>>> getArticlesByAppID(@QueryMap Map<String, Object> params);

    @ApiAnnotation("获取所有应用")
    @GET("help/getApps")
    Observable<Data<List<LocalApplication>>> getApps();
}
