package com.zh.api;

import com.zh.bean.ToolbarNavigationBean;
import com.zh.bean.ToolbarTitleBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author : dayezi
 * data :2019/5/28
 * description:
 */
public class Warehouse {

    public static Map<String, ToolbarNavigationBean> atlas = new HashMap<>();
    public static Map<String, ToolbarTitleBean> titleBeanMap = new HashMap<>();
    public static Map<String, Integer> leftImags = new HashMap<>();
    public static Map<String, String> navigationOnclicks = new HashMap<>();
    public static Map<String, String> navigationOnMenuClicks = new HashMap<>();
    public static List<String> iApplicatons = new ArrayList<>();
}
