package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;

import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/10/29
 * description:
 */
@Route(path = UpdatePersonNameActivity.AROUTER_PATH)
@ToolbarLeft(menuId = R.menu.menu_sure)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white, title = "个人信息")
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "姓名")
public class UpdatePersonNameActivity extends BaseActivity {
    public final static String AROUTER_PATH = "/main/UpdatePersonNameActivity/";

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_update_person_name;
    }

    @Override
    public void init() {

    }

    @OnMenuOnclick
    public void menuClick() {

    }
}
