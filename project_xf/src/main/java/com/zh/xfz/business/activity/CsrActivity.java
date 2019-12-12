package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;

import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/10/30
 * description:
 */
@Route(path = CsrActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "在线客服")
public class CsrActivity extends BaseActivity {
    public final static String AROUTER_PATH = "/main/CsrActivity/";

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_csr;
    }

    @Override
    public void init() {

    }
}
