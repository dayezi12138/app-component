package com.zh.api;

import android.support.annotation.ColorRes;

/**
 * author : dayezi
 * data :2019/6/5
 * description:
 */
public class DefaultBean {

    private int titleAppearanceId = ToolBarInject.DEFAULT_INT_;
    private int titleColorId = ToolBarInject.DEFAULT_INT_;

    private int subTitleAppearanceId = ToolBarInject.DEFAULT_INT_;
    private int subTitleColorId = ToolBarInject.DEFAULT_INT_;

    private int iconId = R.drawable.ic_back;

    private int textSize = 18;
    private int textColorId = android.R.color.white;

    @ColorRes
    private int toolbarBackgroundColorId = R.color.design_default_color_primary;

    public DefaultBean() {
    }

    public DefaultBean(int titleAppearanceId, int titleColorId, int subTitleAppearanceId, int subTitleColorId, int iconId, int textSize, int textColorId, int toolbarBackgroundColorId) {
        this.titleAppearanceId = titleAppearanceId;
        this.titleColorId = titleColorId;
        this.subTitleAppearanceId = subTitleAppearanceId;
        this.subTitleColorId = subTitleColorId;
        this.iconId = iconId;
        this.textSize = textSize;
        this.textColorId = textColorId;
//        this.viewId = viewId;
        this.toolbarBackgroundColorId = toolbarBackgroundColorId;
    }

    public int getTitleAppearanceId() {
        return titleAppearanceId;
    }

    public void setTitleAppearanceId(int titleAppearanceId) {
        this.titleAppearanceId = titleAppearanceId;
    }

    public int getTitleColorId() {
        return titleColorId;
    }

    public void setTitleColorId(int titleColorId) {
        this.titleColorId = titleColorId;
    }

    public int getSubTitleAppearanceId() {
        return subTitleAppearanceId;
    }

    public void setSubTitleAppearanceId(int subTitleAppearanceId) {
        this.subTitleAppearanceId = subTitleAppearanceId;
    }

    public int getSubTitleColorId() {
        return subTitleColorId;
    }

    public void setSubTitleColorId(int subTitleColorId) {
        this.subTitleColorId = subTitleColorId;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getTextColorId() {
        return textColorId;
    }

    public void setTextColorId(int textColorId) {
        this.textColorId = textColorId;
    }

//    public int getViewId() {
//        return viewId;
//    }
//
//    public void setViewId(int viewId) {
//        this.viewId = viewId;
//    }

    public int getToolbarBackgroundColorId() {
        return toolbarBackgroundColorId;
    }

    public void setToolbarBackgroundColorId(int toolbarBackgroundColorId) {
        this.toolbarBackgroundColorId = toolbarBackgroundColorId;
    }
}
