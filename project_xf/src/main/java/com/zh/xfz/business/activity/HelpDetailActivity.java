package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;

import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/10/30
 * description:
 */
@Route(path = HelpDetailActivity.AROUTER_PATH)
@ToolbarLeft(menuId = R.menu.menu_contact_me)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "帮助中心")
public class HelpDetailActivity extends BaseActivity {
    public final static String AROUTER_PATH = "/main/HelpDetailActivity/";

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_help_detail;
    }

    @Override
    public void init() {

    }
}
