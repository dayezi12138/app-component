package com.zh.bean;

/**
 * author : dayezi
 * data :2019/5/30
 * description:
 */
public class ToolbarTitleBean {
    private String title;
    private int textSize;
    private int textColorId;
    private int viewId;
    private int backGroundColorId;
    private int statusBarColorId;

    private ToolbarTitleBean(String title, int textSize, int textColorId, int viewId, int backGroundColorId, int statusBarColorId) {
        this.title = title;
        this.textSize = textSize;
        this.textColorId = textColorId;
        this.viewId = viewId;
        this.backGroundColorId = backGroundColorId;
        this.statusBarColorId = statusBarColorId;
    }

    public static ToolbarTitleBean build(String title, int textSize, int textColorId, int viewId, int backGroundColorId, int statusBarColorId) {
        return new ToolbarTitleBean(title, textSize, textColorId, viewId, backGroundColorId, statusBarColorId);
    }

    public String getTitle() {
        return title;
    }

    public int getTextSize() {
        return textSize;
    }

    public int getTextColorId() {
        return textColorId;
    }

    public int getViewId() {
        return viewId;
    }

    public int getBackGroundColorId() {
        return backGroundColorId;
    }

    public int getStatusBarColorId() {
        return statusBarColorId;
    }
}