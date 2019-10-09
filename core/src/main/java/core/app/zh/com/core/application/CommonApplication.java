package core.app.zh.com.core.application;

import android.content.Context;
import android.support.multidex.MultiDex;

import dagger.android.support.DaggerApplication;

/**
 * author : dayezi
 * data :2019/8/2
 * description:
 */
public abstract class CommonApplication extends DaggerApplication {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
