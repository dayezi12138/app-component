package core.app.zh.com.core.application;


import android.graphics.Color;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.zh.api.application.ApplicationOptionInject;

import java.util.ArrayList;
import java.util.List;

import core.app.zh.com.core.listener.AddOptionInApplicationListener;
import core.app.zh.com.core.listener.ApplicationInitListener;
import core.app.zh.com.core.listener.impl.ActivityLifecycleCallbackListener;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public abstract class BaseApplication extends CommonApplication implements AddOptionInApplicationListener {
    private ActivityLifecycleCallbackListener lifecycleCallbackListener;

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
        ToastUtils.setBgColor(Color.parseColor("#de000000"));
        lifecycleCallbackListener = new ActivityLifecycleCallbackListener();
        registerActivityLifecycleCallbacks(lifecycleCallbackListener);
        init(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        Utils.init(this);
        ApplicationOptionInject.init(this);
        Object object = ApplicationOptionInject.getApplicationCommont();
        if (daggerOptionListener() != null) {
            return daggerOptionListener().optionComponent();
        }
        return null;
    }

    public ActivityLifecycleCallbackListener getLifecycleCallbackListener() {
        return lifecycleCallbackListener;
    }

    @Override
    public List<ApplicationInitListener> addListener() {
        List<ApplicationInitListener> listeners = new ArrayList<>();
        return listeners;
    }
}
