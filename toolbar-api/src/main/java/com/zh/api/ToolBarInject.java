package com.zh.api;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.zh.bean.ToolbarNavigationBean;
import com.zh.bean.ToolbarTitleBean;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * author : dayezi
 * data :2019/5/28
 * description:
 */
public class ToolBarInject {
    public final static int DEFAULT_INT_ = -1;

    private static DefaultOptionListener defaultOptionListener;

    private static boolean hasInit;

    public static void init(Application application) {
        hasInit = _ToolBarInject.init(application);
    }

    public static void init(Application application, DefaultOptionListener defaultOptionListener) {
        hasInit = _ToolBarInject.init(application);
        ToolBarInject.defaultOptionListener = defaultOptionListener;
    }

    public static Map<String, ToolbarNavigationBean> atlas = new HashMap<>();
    public static Map<String, ToolbarTitleBean> titleBeanMap = new HashMap<>();
    public static Map<String, Integer> leftImags = new HashMap<>();
    public static Map<String, String> navigationOnclicks = new HashMap<>();
    public static Map<String, String> navigationOnMenuClicks = new HashMap<>();

    public synchronized static void inject(Object obj, Toolbar toolbar) {
        if (obj == null || toolbar == null) return;
        if (obj instanceof AppCompatActivity || obj instanceof Fragment) {
            initToolbarNavigationBean(obj, toolbar);
            initToolbarTitleBean(obj, toolbar);
            initToolbarLeftBean(obj, toolbar);
            initNavigationClick(obj, toolbar);
            initOnMenuClick(obj, toolbar);
        }
    }

    public static boolean needAddToolbar(Object obj) {
        if (!hasInit) init(valid(obj).getApplication());
        return atlas.containsKey(obj.getClass().getName())
                || titleBeanMap.containsKey(obj.getClass().getName())
                || Warehouse.titleBeanMap.containsKey(obj.getClass().getName())
                || Warehouse.atlas.containsKey(obj.getClass().getName());
    }

    private static AppCompatActivity valid(Object obj) {
        AppCompatActivity activity = null;
        if (obj instanceof AppCompatActivity) activity = (AppCompatActivity) obj;
        else if (obj instanceof Fragment)
            activity = (AppCompatActivity) ((Fragment) obj).getActivity();
        return activity;
    }

    /**
     * @param obj
     * @param toolbar
     */
    private static void initToolbarNavigationBean(Object obj, Toolbar toolbar) {
        if (atlas.containsKey(obj.getClass().getName())) {
            AppCompatActivity activity = valid(obj);
            if (activity == null) return;
            int iconId = R.drawable.ic_back;
            if (defaultOptionListener != null) {
                DefaultBean bean = defaultOptionListener.defaultToolbarOption();
                if (bean.getTitleAppearanceId() != DEFAULT_INT_)
                    toolbar.setTitleTextAppearance(activity, bean.getTitleAppearanceId());
                if (bean.getTextColorId() != DEFAULT_INT_)
                    toolbar.setTitleTextColor(bean.getTextColorId() == DEFAULT_INT_ ? bean.getTextColorId() : activity.getResources().getColor(android.R.color.white));
                if (bean.getSubTitleColorId() != DEFAULT_INT_)
                    toolbar.setSubtitleTextColor(activity.getResources().getColor(bean.getSubTitleColorId()));
                if (bean.getSubTitleAppearanceId() != DEFAULT_INT_)
                    toolbar.setSubtitleTextAppearance(activity, bean.getSubTitleAppearanceId());
                iconId = bean.getIconId();
            }
            ToolbarNavigationBean bean = atlas.get(obj.getClass().getName());
            toolbar.setTitle(bean.getTitle());
            if (bean.getTitleAppearanceId() != DEFAULT_INT_)
                toolbar.setTitleTextAppearance(activity, bean.getTitleAppearanceId());
            toolbar.setSubtitle(bean.getSubTitle());
            if (bean.getSubTitleAppearanceId() != DEFAULT_INT_)
                toolbar.setSubtitleTextAppearance(activity, bean.getSubTitleAppearanceId());
            if (bean.getSubTitleColorId() != DEFAULT_INT_)
                toolbar.setSubtitleTextColor(activity.getResources().getColor(bean.getSubTitleColorId()));
            if (bean.isVisibleNavigation()) {
                toolbar.setNavigationIcon(bean.getIconId() != DEFAULT_INT_ ? bean.getIconId() : iconId);
                final AppCompatActivity finalActivity = activity;
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finalActivity.finish();
                    }
                });
            }
        }
    }

    private static void initToolbarTitleBean(Object obj, Toolbar toolbar) {
        if (titleBeanMap.containsKey(obj.getClass().getName())) {
            AppCompatActivity activity = valid(obj);
            if (activity == null) return;
            TextView textView = new TextView(activity);
            Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.CENTER;
            textView.setLayoutParams(layoutParams);
            textView.setGravity(Gravity.CENTER);
            boolean isAdd = false;
            int colorId = R.color.design_default_color_primary;
            if (defaultOptionListener != null) {
                DefaultBean bean = defaultOptionListener.defaultToolbarOption();
                if (bean.getTextColorId() != DEFAULT_INT_)
                    textView.setTextColor(activity.getResources().getColor(bean.getTextColorId()));
                if (bean.getTextSize() != DEFAULT_INT_)
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, bean.getTextSize());
                toolbar.addView(textView);
                isAdd = true;
                colorId = defaultOptionListener.defaultToolbarOption().getToolbarBackgroundColorId();
            }

            ToolbarTitleBean bean = titleBeanMap.get(obj.getClass().getName());
            if (bean.getViewId() == DEFAULT_INT_) {
                textView.setText(bean.getTitle());
                if (bean.getTextColorId() != DEFAULT_INT_)
                    textView.setTextColor(activity.getResources().getColor(bean.getTextColorId()));
                if (bean.getTextSize() != DEFAULT_INT_) {
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, bean.getTextSize());
                }
                if (!isAdd) toolbar.addView(textView);
            } else {
                toolbar.removeView(textView);
                View view = LayoutInflater.from(activity).inflate(bean.getViewId(), null);
                toolbar.addView(view);
            }
            if (bean.getBackGroundColorId() != -1) {
                colorId = bean.getBackGroundColorId();
            }
            toolbar.setBackgroundColor(activity.getResources().getColor(colorId));
            BarUtils.addMarginTopEqualStatusBarHeight(toolbar);
            BarUtils.setStatusBarColor(activity, activity.getResources().getColor(colorId), 1);
        } else {
            test(obj, toolbar);
        }
    }

    private static void test(Object obj, Toolbar toolbar) {
        if (Warehouse.titleBeanMap.containsKey(obj.getClass().getName())) {
            titleBeanMap.putAll(Warehouse.titleBeanMap);
            inject(obj, toolbar);
        }
    }

    private static void initToolbarLeftBean(Object obj, Toolbar toolbar) {
        if (leftImags.containsKey(obj.getClass().getName())) {
            int menuId = leftImags.get(obj.getClass().getName());
            if (menuId != DEFAULT_INT_) {
                toolbar.inflateMenu(menuId);
            }
        }
    }

    private static void initNavigationClick(final Object obj, Toolbar toolbar) {
        if (navigationOnclicks.containsKey(obj.getClass().getName())) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Method[] methods = obj.getClass().getDeclaredMethods();
                        for (Method method : methods) {
                            if (method.getName().equals(navigationOnclicks.get(obj.getClass().getName()))) {
                                Class<?>[] classes = method.getParameterTypes();
                                if (classes.length == 1 && classes[0].getName().equals(View.class.getName())) {
                                    method.invoke(obj, v);
                                } else {
                                    method.invoke(obj);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    private static void initOnMenuClick(final Object obj, final Toolbar toolbar) {
        if (navigationOnMenuClicks.containsKey(obj.getClass().getName())) {
            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    try {
                        Method[] methods = obj.getClass().getDeclaredMethods();
                        for (Method method : methods) {
                            if (method.getName().equals(navigationOnMenuClicks.get(obj.getClass().getName()))) {
                                Class<?>[] classes = method.getParameterTypes();
                                if (classes.length == 1 && classes[0].getName().equals(MenuItem.class.getName())) {
                                    method.invoke(obj, menuItem);
                                } else {
                                    method.invoke(obj);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                    return true;
                }
            });
        }
    }
}
