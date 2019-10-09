package com.zh.xfz.business.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.AppUtils;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;

import java.text.MessageFormat;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/9/25
 * description:
 */
@Route(path = SettingActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_ios)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "设置")
public class SettingActivity extends BaseActivity {

    public final static String AROUTER_PATH = "/main/SettingActivity/";

    @BindView(R.id.version)
    TextView versionTv;

    @Inject
    MaterialDialog dialog;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_setting;
    }

    @Override
    public void init() {
        versionTv.setText(MessageFormat.format(getResources().getString(R.string.act_setting_version_str), AppUtils.getAppVersionName()));
    }

    @SuppressLint("WrongConstant")
    @OnClick(R.id.switch_account_tv)
    public void switchAccount() {
        ARouter.getInstance().build(AccountLoginActivity.AROUTER_PATH).withFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP).navigation();
    }

    @OnClick(R.id.setting_ly)
    public void setting() {
//        ARouter.getInstance().build(UpdatePasswordActivity.AROUTER_PATH).navigation();
//        RongIM.getInstance().logout();
    }

    @OnClick(R.id.logout)
    public void logout() {
        if (!dialog.isShowing()) dialog.show();
    }
}
