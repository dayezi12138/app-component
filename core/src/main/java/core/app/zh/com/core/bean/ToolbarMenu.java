package core.app.zh.com.core.bean;

import android.support.v7.widget.Toolbar;
import android.view.Menu;

import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.bean.i.IPunish;

/**
 * @auther create by Administrator
 * DATE:2019/3/26 0026 11
 */
public class ToolbarMenu implements IPunish<ToolbarMenu> {
    private int menuId;
    private Toolbar.OnMenuItemClickListener listener;

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public Toolbar.OnMenuItemClickListener getListener() {
        return listener;
    }

    public void setListener(Toolbar.OnMenuItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void punish(BaseActivity activity, Toolbar toolbar, ToolbarMenu bean) {
        if (bean.getMenuId() != 0) {
            Menu menu = toolbar.getMenu();
            menu.clear();
            toolbar.inflateMenu(bean.getMenuId());
        }
        if (listener != null)
            toolbar.setOnMenuItemClickListener(listener);
    }
}
