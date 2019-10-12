package com.zh.api.factory;

import android.app.Activity;
import android.support.v7.widget.Toolbar;

import java.util.HashMap;
import java.util.Map;

/**
 * author : dayezi
 * data :2019/5/30
 * description:
 */
public class ToolbarStrategyFactory {
    private static Map<String, IPunish> punishMap = new HashMap<>();

    //获取
    public static IPunish getPunish(String key) {
        if (!punishMap.containsKey(key)) return EMPTY.getInstance();
        IPunish result = punishMap.get(key);
        return result == null ? EMPTY.getInstance() : result;
    }

    public static void put(String key, IPunish punish) {
        punishMap.put(key, punish);
    }

    public static class EMPTY implements IPunish {
        private volatile static EMPTY instance;

        private EMPTY() {
        }

        public static EMPTY getInstance() {
            if (instance == null) {
                synchronized (EMPTY.class) {
                    if (instance == null) {
                        instance = new EMPTY();
                    }
                }
            }
            return instance;
        }

        @Override
        public void punish(Activity activity, Toolbar toolbar, IPunish bean) {

        }
    }
}
