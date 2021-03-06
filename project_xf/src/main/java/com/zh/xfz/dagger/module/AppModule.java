package com.zh.xfz.dagger.module;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.blankj.utilcode.util.LogUtils;
import com.zh.xfz.api.HelpService;
import com.zh.xfz.api.MyService;
import com.zh.xfz.application.MyApplication;
import com.zh.xfz.db.DaoMaster;
import com.zh.xfz.db.DaoSession;

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
    public static final int READ_WRITE_CONNECT_TIME = 15;
    private HttpLoggingInterceptor loggingInterceptor;
    public final static String BASE_URL = "http://47.103.75.23:8031/api/";
    private final String HEPL_BASE_URL = "http://csmapi.fus.cn/api/";
    private final String PGYER_APPID = "PGYER_APPID";
    private final static String DB_NAME = "XFZ";

    @Inject
    public AppModule(MyApplication application) {
        this.application = application;
        loggingInterceptor = new HttpLoggingInterceptor(message -> LogUtils.eTag(TAG, message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    public HttpLoggingInterceptor loggingInterceptor() {
        return loggingInterceptor;
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
    public MyService myService(Retrofit retrofit) {
        MyService myService = retrofit.create(MyService.class);
        RequestProxy proxy = new RequestProxy(myService);
        return (MyService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{MyService.class}, proxy);
    }

    @Provides
    public HelpService helpService(Retrofit retrofit) {
        HelpService helpService = retrofit.newBuilder().baseUrl(HEPL_BASE_URL).build().create(HelpService.class);
        RequestProxy proxy = new RequestProxy(helpService);
        return (HelpService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{HelpService.class}, proxy);
    }

    @Provides
    public MyApplication getApplication() {
        return application;
    }

    @Provides
    public String getPGYID() {
        try {
            ApplicationInfo appInfo = application.getPackageManager().getApplicationInfo(application.getPackageName(),
                    PackageManager.GET_META_DATA);
            String msg = appInfo.metaData.getString(PGYER_APPID);
            return msg;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Provides
    public DaoSession daoSession(MyApplication application) {
        DaoSession daoSession = DaoMaster.newDevSession(application, DB_NAME);
        return daoSession;
    }

}