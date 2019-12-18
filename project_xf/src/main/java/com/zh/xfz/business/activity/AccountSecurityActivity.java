package com.zh.xfz.business.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.mvp.contract.UserContract;
import com.zh.xfz.mvp.presenter.UserPresenter;
import com.zh.xfz.utils.LoginHandler;
import com.zh.xfz.utils.WxHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.view.MyPopupWindow;

/**
 * author : dayezi
 * data :2019/10/29
 * description:
 */
@Route(path = AccountSecurityActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "账户安全")
public class AccountSecurityActivity extends BaseActivity implements View.OnClickListener, UserContract.AccountSecurityUI {
    public final static String AROUTER_PATH = "/main/AccountSecurityActivity/";
    public final static String AUTHOR_CODE = "AUTHOR_CODE";
    private IWXAPI api;

    @BindView(R.id.visible_tv)
    TextView tv;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    MyPopupWindow popupWindow;

    @Inject
    UserPresenter userPresenter;

    @Inject
    LoginHandler loginHandler;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_account_security;
    }

    @Override
    public void init() {
        api = WxHelper.register(this, false);
        if (loginHandler.isBindWX()) {
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
        if (intent.hasExtra(AUTHOR_CODE) && !loginHandler.isBindWX()) {
            userPresenter.bindWxOpenID(intent.getStringExtra(AUTHOR_CODE));
        }
    }

    @OnClick(R.id.bind_wx_ly)
    public void bindWX() {
        if (!loginHandler.isBindWX()) {
            SendAuth.Req req = WxHelper.wxAuth();
            req.state = AccountSecurityActivity.class.getSimpleName();
            api.sendReq(req);
        } else {
            if (popupWindow.isShowing()) return;
            popupWindow.setBackgroundAlpha();
            popupWindow.showAtLocation(toolbar, Gravity.BOTTOM, 0, 0);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sure_btn:
                if (popupWindow.isShowing()) popupWindow.dismiss();
                userPresenter.relieveWXBind();
                break;
            case R.id.cancel_btn:
                if (popupWindow.isShowing()) popupWindow.dismiss();
                break;
        }
    }

    @Override
    public void bindWXSuccess() {
        tv.setVisibility(View.VISIBLE);
    }

    @Override
    public void relieveWXSuccess() {
        tv.setVisibility(View.GONE);
    }
}
