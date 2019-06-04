package core.app.zh.com.core.application;


import android.graphics.Color;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;

import core.app.zh.com.core.listener.AddOptionInApplicationListener;
import core.app.zh.com.core.listener.impl.ActivityLifecycleCallbackListener;
import dagger.android.support.DaggerApplication;

public abstract class BaseApplication extends DaggerApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
        ToastUtils.setBgColor(Color.parseColor("#de000000"));
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbackListener());
        if (option() != null) {
            AddOptionInApplicationListener applicationListener = option();
            applicationListener.init(this);
        }


    }

    public abstract AddOptionInApplicationListener option();
}
