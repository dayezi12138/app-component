package com.zh.api.application;

import android.app.Application;
import android.text.TextUtils;

import com.zh.api.Warehouse;
import com.zh.applicationOption.OptionInApplicationService;

import java.util.ArrayList;
import java.util.List;


/**
 * author : dayezi
 * data :2019/8/2
 * description:
 */
public class ApplicationOptionInject {
    private static boolean hasInit;
    public static List<String> iApplicationOptionList = new ArrayList<>();
    private static OptionInApplicationService optionInApplicationService;
    private static Application application;

    public static void init(Application application) {
        hasInit = _ApplicationOptionInject.init(application);
        if (hasInit)
            initOptionInApplicationService();
    }

    public synchronized static Object getApplicationCommont() {
        if (hasInit && optionInApplicationService != null) {
            return optionInApplicationService.optionApplicationComponent();
        }
        return null;
    }

    private static void initOptionInApplicationService() {
        try {
            String className = exist();
            if (!TextUtils.isEmpty(className)) {
                Object object = Class.forName(className).newInstance();
                if (object instanceof OptionInApplicationService) {
                    optionInApplicationService = (OptionInApplicationService) object;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private static String exist() {
        if (!iApplicationOptionList.isEmpty()) return iApplicationOptionList.get(0);
        if (!Warehouse.iApplicatons.isEmpty()) return Warehouse.iApplicatons.get(0);
        return null;
    }
}
