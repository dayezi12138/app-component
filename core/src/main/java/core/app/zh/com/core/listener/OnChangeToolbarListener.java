package core.app.zh.com.core.listener;

import android.support.v7.widget.Toolbar;

import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.bean.ToolbarBean;

/**
 * @auther create by Administrator
 * DATE:2019/3/26 0026 11
 */
public interface OnChangeToolbarListener {
    void onchange(BaseActivity activity, Toolbar toolbar, ToolbarBean bean);
}
