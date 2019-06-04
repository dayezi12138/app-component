package core.app.zh.com.core.bean.i;

import android.support.v7.widget.Toolbar;

import core.app.zh.com.core.base.BaseActivity;

/**
 * @auther create by Administrator
 * DATE:2019/3/26 0026 13
 */
public interface IPunish<T extends IPunish> {

    void punish(BaseActivity activity, Toolbar toolbar, T bean);
}
