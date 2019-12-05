package com.zh.xfz.business.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.application.MyApplication;
import com.zh.xfz.mvp.contract.activity.UserOperationContract;
import com.zh.xfz.mvp.presenter.UserOperationPresenter;
import com.zh.xfz.utils.LoginUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/10/29
 * description:
 */
@Route(path = AccountSecurityActivity.AROUTER_PATH)
//@ToolbarLeft(menuId = R.menu.menu_sure)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "账户安全")
public class AccountSecurityActivity extends BaseActivity implements UserOperationContract.AccountSecurityUI {
    public final static String AROUTER_PATH = "/main/AccountSecurityActivity/";
    public final static String AUTHOR_CODE = "AUTHOR_CODE";
    private IWXAPI api;

    @BindView(R.id.visible_tv)
    TextView tv;

    @Inject
    UserOperationPresenter presenter;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_account_security;
    }

    @Override
    public void init() {
        api = WXAPIFactory.createWXAPI(this, MyApplication.APP_ID, false);
        if (LoginUtils.isIsBindWX()) {
            tv.setVisibility(View.VISIBLE);
        }
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

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (intent.hasExtra(AUTHOR_CODE))
            presenter.wxCheckAndLogin(intent.getStringExtra(AUTHOR_CODE));
    }

    @OnClick(R.id.bind_wx_ly)
    public void bindWX() {
        if (!LoginUtils.isIsBindWX()) {
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
//        req.state = String.valueOf(new Date().getTime());
            req.state = "AccountSecurityActivity";
            api.sendReq(req);
        } else {
            new AlertDialog.Builder(this).setTitle("提示").setMessage("是否解除微信绑定").setPositiveButton("确定", (dialog, which) -> {
                presenter.relieveWXBind();
                dialog.dismiss();

            }).setNegativeButton("取消", (dialog, which) -> dialog.dismiss());
        }

    }

    @Override
    public void success() {
        showMsg("绑定成功");
        LoginUtils.setBindWX(true);
        tv.setVisibility(View.VISIBLE);
    }

    @Override
    public void relieve() {
        showMsg("已解除微信绑定");
        LoginUtils.setBindWX(false);
        tv.setVisibility(View.GONE);
    }

}
