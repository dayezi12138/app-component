package core.app.zh.com.core.application;


import android.graphics.Color;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;

import core.app.zh.com.core.listener.AddOptionInApplicationListener;
import core.app.zh.com.core.listener.impl.ActivityLifecycleCallbackListener;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public abstract class BaseApplication extends DaggerApplication implements AddOptionInApplicationListener {


    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
        ToastUtils.setBgColor(Color.parseColor("#de000000"));
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbackListener());
        init(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        if (daggerOptionListener() != null) {
            return daggerOptionListener().optionComponent();
        }
        return null;
    }
//
//    public abstract AddOptionInApplicationListener option();
//
//    public abstract DaggerOptionListener daggerOptionListener();
//
//    public abstract List<AddOptionInPageListener> optionActivityList();

}
