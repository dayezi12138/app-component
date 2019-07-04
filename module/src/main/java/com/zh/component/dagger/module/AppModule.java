package com.zh.component.dagger.module;

import com.blankj.utilcode.util.LogUtils;
import com.zh.component.api.MyService;
import com.zh.component.application.MyApplication;

import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import core.app.zh.com.core.proxy.RequestProxy;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {
    private final static String TAG = AppModule.class.getSimpleName();
    private MyApplication application;
    private static final int READ_WRITE_CONNECT_TIME = 15;
    private HttpLoggingInterceptor loggingInterceptor;
    private final String BASE_URL = "http://120.26.41.167:7083/api/";
    private final static String DB_NAME = "YTB";

    @Inject
    public AppModule(MyApplication application) {
        this.application = application;
        loggingInterceptor = new HttpLoggingInterceptor(message -> LogUtils.eTag(TAG, message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    public MyApplication getApplication() {
        return application;
    }

    @Provides
    public OkHttpClient httpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(READ_WRITE_CONNECT_TIME, TimeUnit.SECONDS).writeTimeout(READ_WRITE_CONNECT_TIME, TimeUnit.SECONDS).addInterceptor(loggingInterceptor);
        return builder.build();
    }

    @Provides
    public Retrofit retrofit(OkHttpClient httpClient) {
        return new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).client(httpClient).build();
    }

    @Provides
    public String name() {
        return "aaa";
    }

    @Provides
    public MyService myService(Retrofit retrofit) {
        MyService myService = retrofit.create(MyService.class);
        RequestProxy proxy = new RequestProxy(myService);
        return (MyService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{MyService.class}, proxy);
    }

//    @Provides
//    public DaoSession daoSession(MyApplication application) {
//        return DaoMaster.newDevSession(application, DB_NAME);
//    }
}