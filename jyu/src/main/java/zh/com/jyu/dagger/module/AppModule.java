package zh.com.jyu.dagger.module;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.zh.api.DefaultBean;
import com.zh.api.DefaultOptionListener;

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
import zh.com.jyu.R;
import zh.com.jyu.api.MyService;
import zh.com.jyu.application.MyApplication;
import zh.com.jyu.business.activity.LoginActivity;
import zh.com.jyu.business.activity.MainActivity;
import zh.com.jyu.constans.SpKeyConstant;
import zh.com.jyu.db.DaoMaster;
import zh.com.jyu.db.DaoSession;
import zh.com.jyu.listener.LoginStateListener;

@Module
public class AppModule {
    private final static String TAG = AppModule.class.getSimpleName();
    private MyApplication application;
    private static final int READ_WRITE_CONNECT_TIME = 15;
    private HttpLoggingInterceptor loggingInterceptor;
    private final String BASE_URL = "http://120.26.41.167:7081/api/";
    public final static String DB_NAME = "JYu";

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
    public LoginStateListener loginStateListener() {
        return new LoginStateListener() {

            @Override
            public void login() {
                ARouter.getInstance().build(LoginActivity.AROUTE_PATH).navigation();
            }

            @Override
            public void directNext() {
                ARouter.getInstance().build(MainActivity.AROUTE_PATH).navigation();
            }

            @Override
            public void start() {
                if (SPUtils.getInstance().getInt(SpKeyConstant.LOGIN_UID_KEY) != -1) {
                    directNext();
                } else {
                    login();
                }
            }
        };
    }

    @Provides
    public MyService myService(Retrofit retrofit) {
        MyService myService = retrofit.create(MyService.class);
        RequestProxy proxy = new RequestProxy(myService);
        return (MyService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{MyService.class}, proxy);
    }

    @Provides
    public DefaultOptionListener defaultOptionListener() {
        return () -> {
            DefaultBean bean = new DefaultBean();
            bean.setToolbarBackgroundColorId(R.color.colorPrimary);
            bean.setIconId(R.drawable.ic_back_white);
            return bean;
        };
    }

    @Provides
    public DaoSession daoSession(MyApplication application) {
        return DaoMaster.newDevSession(application, DB_NAME);
    }
}