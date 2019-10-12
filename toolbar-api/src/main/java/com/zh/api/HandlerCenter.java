package com.zh.api;

import android.content.Context;
import android.util.Log;

import com.zh.bean.Constant;
import com.zh.fade.INavagitionClick;
import com.zh.fade.IOnMenuClick;
import com.zh.fade.IToolbarLeft;
import com.zh.fade.IToolbarNavigation;
import com.zh.fade.IToolbarTitle;

import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * author : dayezi
 * data :2019/5/30
 * description:
 */
public class HandlerCenter {
    private static Context mContext;
    static ThreadPoolExecutor executor;

    /**
     * LogisticsCenter init, load all metas in memory. Demand initialization
     */
    public synchronized static void init(Context context, ThreadPoolExecutor tpe) {
        try {
            mContext = context;
            executor = tpe;
            Set<String> routerMap = ClassUtils.getFileNameByPackageName(mContext, Constant.PACK_NAME);
            if (!routerMap.isEmpty()) {
                for (String className : routerMap) {
                    Object bean = (Class.forName(className).getConstructor().newInstance());
                    if (bean instanceof IToolbarNavigation) {
                        ((IToolbarNavigation) (Class.forName(className).getConstructor().newInstance())).loadInfo(ToolBarInject.atlas);
                        Warehouse.atlas.clear();
                        Warehouse.atlas.putAll(ToolBarInject.atlas);
                    } else if (bean instanceof IToolbarTitle) {
                        ((IToolbarTitle) (Class.forName(className).getConstructor().newInstance())).loadInfo(ToolBarInject.titleBeanMap);
                        Warehouse.titleBeanMap.clear();
                        Warehouse.titleBeanMap.putAll(ToolBarInject.titleBeanMap);
                    } else if (bean instanceof IToolbarLeft) {
                        ((IToolbarLeft) (Class.forName(className).getConstructor().newInstance())).loadInfo(ToolBarInject.leftImags);
                        Warehouse.leftImags.clear();
                        Warehouse.leftImags.putAll(ToolBarInject.leftImags);
                    } else if (bean instanceof INavagitionClick) {
                        ((INavagitionClick) (Class.forName(className).getConstructor().newInstance())).loadInfo(ToolBarInject.navigationOnclicks);
                        Warehouse.navigationOnclicks.clear();
                        Warehouse.navigationOnclicks.putAll(ToolBarInject.navigationOnclicks);
                    } else if (bean instanceof IOnMenuClick) {
                        ((IOnMenuClick) (Class.forName(className).getConstructor().newInstance())).loadInfo(ToolBarInject.navigationOnMenuClicks);
                        Warehouse.navigationOnMenuClicks.clear();
                        Warehouse.navigationOnMenuClicks.putAll(ToolBarInject.navigationOnMenuClicks);
                    }
                }
            } else {//navigationOnclicks
                Log.e("TAG", "IS NULL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * LogisticsCenter init, load all metas in memory. Demand initialization
     */
    public synchronized static void init(Context context) {
        try {
            mContext = context;
            Set<String> routerMap = ClassUtils.getFileNameByPackageName(mContext, "com.zh.toolbar");
            if (!routerMap.isEmpty()) {
                for (String className : routerMap) {
                    ((IToolbarNavigation) (Class.forName(className).getConstructor().newInstance())).loadInfo(ToolBarInject.atlas);
                }
            } else {
                Log.e("TAG", "IS NULL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
