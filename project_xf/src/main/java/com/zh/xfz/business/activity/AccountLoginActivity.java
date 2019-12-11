package com.zh.xfz.business.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.application.MyApplication;
import com.zh.xfz.mvp.contract.activity.AccountLoginContract;
import com.zh.xfz.mvp.presenter.UserOperationPresenter;
import com.zh.xfz.mvp.presenter.activity.AccountLoginPresenter;

import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.view.ClearEditTextView;

/**
 * author : dayezi
 * data :2019/7/18
 * description:
 */
@Route(path = AccountLoginActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_ios)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color)
public class AccountLoginActivity extends BaseActivity implements AccountLoginContract.AccountLoginUI {
    public final static String AROUTER_PATH = "/login/AccountLoginActivity/";

    @BindView(R.id.account_et)
    ClearEditTextView accountEt;

    @BindView(R.id.submit_btn)
    Button submitBtn;

    @Inject
    AccountLoginPresenter presenter;
    @Inject
    UserOperationPresenter userOperationPresenter;

    private IWXAPI api;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_phone_login;
    }

    @Override
    public void init() {
        switchBtn(false);
        api = WXAPIFactory.createWXAPI(this, MyApplication.APP_ID, false);
        accountEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean isTrue = TextUtils.isEmpty(s);
                int colorId = isTrue ? R.color.input_account_color : R.color.white;
                submitBtn.setTextColor(getResources().getColor(colorId));
                switchBtn(!isTrue);
            }
        });
    }

    private void switchBtn(boolean s) {
        submitBtn.setClickable(s);
        submitBtn.setFocusable(s);
    }

    @OnClick(R.id.submit_btn)
    public void submit() {
        presenter.validAccount(accountEt.getText().toString());
    }

    @OnClick(R.id.wx_login)
    public void wxLogin() {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = String.valueOf(new Date().getTime());
        api.sendReq(req);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (intent.hasExtra("code"))
            userOperationPresenter.wxLogin(intent.getStringExtra("code"));
    }

}
