package core.app.zh.com.core.bean;

import android.graphics.Color;

/**
 * toolbar封装类
 *
 * @auther create by Administrator
 * DATE:2019/3/26 0026 11
 */
@Deprecated
public class ToolbarBean {
    private ToolbarNavigation navigation;
    private ToolbarTitle toolbarTitle;
    private ToolbarMenu menu;
    private int backgroundColor = Color.WHITE;

    public ToolbarBean() {
        navigation = new ToolbarNavigation();
        toolbarTitle = new ToolbarTitle();
        menu = new ToolbarMenu();
    }

    public ToolbarNavigation getNavigation() {
        return navigation;
    }

    public void setNavigation(ToolbarNavigation navigation) {
        this.navigation = navigation;
    }

    public ToolbarTitle getToolbarTitle() {
        return toolbarTitle;
    }

    public void setToolbarTitle(ToolbarTitle toolbarTitle) {
        this.toolbarTitle = toolbarTitle;
    }

    public ToolbarMenu getMenu() {
        return menu;
    }

    public void setMenu(ToolbarMenu menu) {
        this.menu = menu;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
