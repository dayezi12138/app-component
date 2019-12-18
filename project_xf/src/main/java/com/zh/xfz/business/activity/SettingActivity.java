package com.zh.xfz.business.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
import com.zh.xfz.utils.LoginHandler;

import java.text.MessageFormat;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.utils.DataCleanManager;

/**
 * author : dayezi
 * data :2019/9/25
 * description:
 */
@Route(path = SettingActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "设置")
public class SettingActivity extends BaseActivity {

    public final static String AROUTER_PATH = "/main/SettingActivity/";

    @BindView(R.id.version)
    TextView versionTv;

    @BindView(R.id.clear_tv)
    TextView clearTv;

    @Inject
    MaterialDialog dialog;

//    @Named("clearMemoryDialog")
//    MaterialDialog clearMemoryDialog;

    @Inject
    LoginHandler loginHandler;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_setting;
    }

    @Override
    public void init() {
        versionTv.setText(MessageFormat.format(getResources().getString(R.string.act_setting_version_str), AppUtils.getAppVersionName()));
        try {
            clearTv.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("WrongConstant")
    @OnClick(R.id.switch_account_tv)
    public void switchAccount() {
        loginHandler.clearLogin();
        ARouter.getInstance().build(AccountLoginActivity.AROUTER_PATH).withFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK).navigation();
    }

    @OnClick(R.id.setting_ly)
    public void setting() {
        ARouter.getInstance().build(AccountSecurityActivity.AROUTER_PATH).navigation();
    }

    @OnClick(R.id.logout)
    public void logout() {
        if (!dialog.isShowing()) dialog.show();
    }

    @OnClick(R.id.clear_ly)
    public void clear() {
        new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.act_normal_title_dialog_msg)).setMessage(getResources().getString(R.string.act_clear_cache_content_x_dialog_msg))
                .setPositiveButton(getResources().getString(R.string.alert_sure_str), (dialog, which) -> {
                    DataCleanManager.clearAllCache(SettingActivity.this);
                    try {
                        clearTv.setText(DataCleanManager.getTotalCacheSize(SettingActivity.this));
                        showMsg(getResources().getString(R.string.act_clear_cache_success_toast_msg));
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        dialog.dismiss();
                    }
                }).setNegativeButton(getResources().getString(R.string.alert_cancel_str), (dialog, which) -> dialog.dismiss()).show();
    }

    @OnClick(R.id.my_company_ly)
    public void myCompany() {
        ARouter.getInstance().build(MyCompanyActivity.AROUTER_PATH).navigation();
    }

    @OnClick(R.id.help_ly)
    public void helpClick() {
        ARouter.getInstance().build(HelpActivity.AROUTER_PATH).navigation();
    }
}
