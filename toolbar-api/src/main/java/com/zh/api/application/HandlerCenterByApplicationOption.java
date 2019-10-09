package com.zh.api.application;

import android.content.Context;
import android.util.Log;

import com.zh.annatation.application.ApplicationOption;
import com.zh.api.ClassUtils;
import com.zh.api.Warehouse;
import com.zh.bean.Constant;
import com.zh.fade.IApplicationOption;

import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * author : dayezi
 * data :2019/7/1
 * description:
 */
public class HandlerCenterByApplicationOption {
    private static Context mContext;
    static ThreadPoolExecutor executor;

    /**
     * LogisticsCenter init, load all metas in memory. Demand initialization
     */
    public synchronized static void init(Context context, ThreadPoolExecutor tpe) {
        try {
            mContext = context;
            executor = tpe;
            Set<String> routerMap = ClassUtils.getFileNameByPackageName(mContext, Constant.PACK_NAME_PREFIX + ApplicationOption.class.getSimpleName().toLowerCase());
//            LogUtils.e(routerMap);
            if (!routerMap.isEmpty()) {
                for (String className : routerMap) {
                    Object bean = (Class.forName(className).getConstructor().newInstance());
                    if (bean instanceof IApplicationOption) {
                        ((IApplicationOption) (Class.forName(className).getConstructor().newInstance())).loadInfo(ApplicationOptionInject.iApplicationOptionList);
                        Warehouse.iApplicatons.clear();
                        Warehouse.iApplicatons.addAll(ApplicationOptionInject.iApplicationOptionList);
                    }
                }
            } else {
                Log.e("TAG", "IS NULL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
