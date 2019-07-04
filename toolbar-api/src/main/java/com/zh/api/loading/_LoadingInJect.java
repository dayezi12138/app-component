package com.zh.api.loading;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.zh.api.DefaultPoolExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * author : dayezi
 * data :2019/7/1
 * description:
 */
public class _LoadingInJect {
    private static Context mContext;
    private static ThreadPoolExecutor executor = DefaultPoolExecutor.getInstance();
    private static Handler mHandler;

    private _LoadingInJect() {
    }

    protected static boolean init(Application application) {
        mContext = application;
        HandlerCenterByLoading.init(mContext, executor);
        mHandler = new Handler(Looper.getMainLooper());
        return true;
    }
}
