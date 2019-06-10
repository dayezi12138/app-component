package com.zh.component1.dagger.module;

import com.blankj.utilcode.util.LogUtils;
import com.zh.component1.application.Module_1Application;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule_1 {
    private final static String TAG = AppModule_1.class.getSimpleName();
    private Module_1Application application;
    private static final int READ_WRITE_CONNECT_TIME = 15;
    private HttpLoggingInterceptor loggingInterceptor;
    private final String BASE_URL = "http://120.26.41.167:8091/api/";
    private final static String DB_NAME = "YTB";

    @Inject
    public AppModule_1(Module_1Application application) {
        this.application = application;
        loggingInterceptor = new HttpLoggingInterceptor(message -> LogUtils.eTag(TAG, message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    public Module_1Application getApplication() {
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

//    @Provides
//    public MyService myService(Retrofit retrofit) {
//        MyService myService = retrofit.create(MyService.class);
//        RequestProxy proxy = new RequestProxy(myService);
//        return (MyService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{MyService.class}, proxy);
//    }

//    @Provides
//    public DaoSession daoSession(Module_1Application application) {
//        return DaoMaster.newDevSession(application, DB_NAME);
//    }
}