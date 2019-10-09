package com.zh.api.loading;

import android.content.Context;
import android.util.Log;

import com.zh.api.ClassUtils;
import com.zh.bean.Constant;
import com.zh.fade.ILoading;

import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * author : dayezi
 * data :2019/7/1
 * description:
 */
public class HandlerCenterByLoading {
    private static Context mContext;
    static ThreadPoolExecutor executor;

    /**
     * LogisticsCenter init, load all metas in memory. Demand initialization
     */
    public synchronized static void init(Context context, ThreadPoolExecutor tpe) {
        try {
            mContext = context;
            executor = tpe;
            Set<String> routerMap = ClassUtils.getFileNameByPackageName(mContext, Constant.PACK_NAME_1);
//            LogUtils.e(routerMap);
            if (!routerMap.isEmpty()) {
                for (String className : routerMap) {
                    Object bean = (Class.forName(className).getConstructor().newInstance());
                    if (bean instanceof ILoading)
                        ((ILoading) (Class.forName(className).getConstructor().newInstance())).loadInfo(LoadingInJect.iLoadingList);
                }
            } else {
                Log.e("TAG", "IS NULL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
