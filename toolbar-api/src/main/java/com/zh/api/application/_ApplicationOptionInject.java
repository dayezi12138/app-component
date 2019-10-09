package com.zh.api.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.zh.api.DefaultPoolExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * author : dayezi
 * data :2019/8/2
 * description:
 */
final class _ApplicationOptionInject {
    private static Context mContext;
    private static ThreadPoolExecutor executor = DefaultPoolExecutor.getInstance();
    private static Handler mHandler;

    private _ApplicationOptionInject() {
    }

    protected static boolean init(Application application) {
        mContext = application;
        HandlerCenterByApplicationOption.init(mContext, executor);
        mHandler = new Handler(Looper.getMainLooper());
        return true;
    }
}
