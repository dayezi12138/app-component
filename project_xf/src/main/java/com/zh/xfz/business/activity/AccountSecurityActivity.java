package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;

import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/10/29
 * description:
 */
@Route(path = AccountSecurityActivity.AROUTER_PATH)
@ToolbarLeft(menuId = R.menu.menu_sure)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "账户安全")
public class AccountSecurityActivity extends BaseActivity {
    public final static String AROUTER_PATH = "/main/AccountSecurityActivity/";

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_account_security;
    }

    @Override
    public void init() {

    }

    @OnMenuOnclick
    public void menuClick() {
    }

    @OnClick(R.id.phone_ly)
    public void phoneClick() {
        ARouter.getInstance().build(UpdatePhoneActivity.AROUTER_PATH).navigation();
    }

    @OnClick(R.id.update_password_ly)
    public void passwordClick() {
        ARouter.getInstance().build(UpdatePasswordActivity.AROUTER_PATH).navigation();
    }
}
