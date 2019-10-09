package com.zh.api.loading;

import android.app.Application;

import com.blankj.utilcode.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * author : dayezi
 * data :2019/7/1
 * description:
 */
public class LoadingInJect {
    private static boolean hasInit;
    public static List<String> iLoadingList = new ArrayList<>();

    public static void init(Application application) {
        hasInit = _LoadingInJect.init(application);
    }

    public synchronized static boolean valided(Object object) {
        boolean exist = false;
        if (ObjectUtils.isEmpty(object) || !hasInit) return exist;
        for (String loading : iLoadingList) {
            if (object.getClass().getName().equals(loading)) {
                exist = true;
                break;
            }
        }
        return exist;
    }
}
