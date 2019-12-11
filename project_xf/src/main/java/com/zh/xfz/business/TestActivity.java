package com.zh.xfz.business;

import android.support.annotation.NonNull;
import android.widget.LinearLayout;

import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/12/11
 * description:
 */
@ToolbarLeft(menuId = R.menu.menu_sure)
@ToolbarNavigation(visibleNavigation = true,iconId = R.drawable.ic_back_white,title = "222")
@ToolbarTitle(backGroundColorId = R.color.background_splash_color,title = "222")
public class TestActivity extends BaseActivity {

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    @BindView(R.id.ly)
    LinearLayout ly;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.toolbar_;
    }

    @Override
    public void init() {
    }
}
