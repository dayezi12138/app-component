package com.zh.xfz.business.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.constans.Constants;
import com.zh.xfz.listener.MyTextWatcher;
import com.zh.xfz.mvp.presenter.UserPresenter;
import com.zh.xfz.utils.NotEmptyUtil;
import com.zh.xfz.utils.WxHelper;

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
public class AccountLoginActivity extends BaseActivity {
    public final static String AROUTER_PATH = "/login/AccountLoginActivity/";
    @BindView(R.id.account_et)
    ClearEditTextView accountEt;

    @BindView(R.id.submit_btn)
    Button submitBtn;

    @Inject
    UserPresenter userPresenter;

    private IWXAPI api;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_phone_login;
    }

    @Override
    public void init() {
        switchBtn(false);
        api = WxHelper.register(this, false);
        accountEt.addTextChangedListener((MyTextWatcher) s -> {
            boolean isTrue = TextUtils.isEmpty(s);
            int colorId = isTrue ? R.color.input_account_color : R.color.white;
            submitBtn.setTextColor(getResources().getColor(colorId));
            switchBtn(!isTrue);
        });
    }


    private void switchBtn(boolean s) {
        submitBtn.setClickable(s);
        submitBtn.setFocusable(s);
    }

    @OnClick(R.id.submit_btn)
    public void submit() {
        String account = accountEt.getText().toString();
        if (NotEmptyUtil.isEmpty(account, getResources().getString(R.string.act_account_not_empty_toast_msg)))
            return;
        userPresenter.validAccount(accountEt.getText().toString());
    }

    @OnClick(R.id.wx_login)
    public void wxLogin() {
        api.sendReq(WxHelper.wxAuth());
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (intent.hasExtra(Constants.WX_CODE))
            userPresenter.wxLogin(intent.getStringExtra(Constants.WX_CODE));
    }

}
