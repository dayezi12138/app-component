package com.zh.bean;

/**
 * author : dayezi
 * data :2019/12/25
 * description:
 */
public class ToolbarLeftBean {
    private int menuId;
    private int actionMenuTextAppearanceId;

    private ToolbarLeftBean(int menuId, int actionMenuTextAppearanceId) {
        this.menuId = menuId;
        this.actionMenuTextAppearanceId = actionMenuTextAppearanceId;
    }

    public static ToolbarLeftBean build(int menuId, int actionMenuTextAppearanceId) {
        return new ToolbarLeftBean(menuId, actionMenuTextAppearanceId);
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getActionMenuTextAppearanceId() {
        return actionMenuTextAppearanceId;
    }

    public void setActionMenuTextAppearanceId(int actionMenuTextAppearanceId) {
        this.actionMenuTextAppearanceId = actionMenuTextAppearanceId;
    }
}
