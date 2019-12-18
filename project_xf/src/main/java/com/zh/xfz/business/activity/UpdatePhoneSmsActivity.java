package com.zh.xfz.business.activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.StringUtils;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.mvp.contract.SmsCodeContract;
import com.zh.xfz.mvp.presenter.SmsCodePresenter;
import com.zh.xfz.mvp.presenter.UserPresenter;
import com.zh.xfz.utils.MyCountDownTimer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.view.VerifyCodeView;

import static com.zh.xfz.constans.Constants.SMS_STATUS_REPLACE_PHONE_CODE;

/**
 * author : dayezi
 * data :2019/10/29
 * description:
 */
@Route(path = UpdatePhoneSmsActivity.AROUTER_PATH)
@ToolbarLeft(menuId = R.menu.menu_complete)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "绑定手机号")
public class UpdatePhoneSmsActivity extends BaseActivity implements SmsCodeContract.UpdatePhoneUI, MyCountDownTimer.CountDownListener {
    public final static String AROUTER_PATH = "/main/UpdatePhoneSmsActivity/";
    public final static String KEY_PHONE = "KEY_PHONE";

    @BindView(R.id.phone_tv)
    TextView phoneTv;

    @BindView(R.id.valid_note_view)
    VerifyCodeView verifyCodeView;

    @Autowired(name = KEY_PHONE, required = true)
    String phone;

    @BindView(R.id.refresh_tv)
    TextView refreshTv;

    @Inject
    SmsCodePresenter presenter;

    @Inject
    UserPresenter userPresenter;

    @Inject
    MyCountDownTimer myCountDownTimer;

    private String verifyCode;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_update_phone_sms;
    }

    @Override
    public void init() {
        phoneTv.setText(phone);
        presenter.getCode(phone, SMS_STATUS_REPLACE_PHONE_CODE);
        verifyCodeView.setOnAllFilledListener(text -> verifyCode = text);
    }

    @OnMenuOnclick
    public void menuClick() {
        if (StringUtils.isEmpty(verifyCode)) {
            showMsg(getResources().getString(R.string.act_sms_code_not_empty_toast_msg));
            return;
        }
        if (!isCanSendSMS) {
            showMsg(getResources().getString(R.string.act_update_phone_fail_msg));
            return;
        }
        userPresenter.updateMobile(phoneTv.getText().toString(), verifyCode);
    }

    private void refresh() {
        if (!refreshTv.isClickable()) {
            refreshTv.setText(getResources().getString(R.string.act_valid_note_refresh_note_str));
            refreshTv.setBackgroundResource(R.drawable.underline_layer_list);
            refreshTv.setClickable(true);
        }
    }

    @Override
    public void sendSuccess() {
        myCountDownTimer.start();
    }

    @Override
    public void failSend() {
        isCanSendSMS = false;
    }

    private boolean isCanSendSMS = true;

    @OnClick(R.id.refresh_tv)
    public void sendSMS() {
        if (!isCanSendSMS) {
            showMsg(getResources().getString(R.string.act_update_phone_fail_msg));
            return;
        }
        presenter.getCode(phone, SMS_STATUS_REPLACE_PHONE_CODE);
    }

    @Override
    public void onTick(int second) {
        refreshTv.setText(second + "S");
        if (refreshTv.isClickable()) {
            refreshTv.setBackgroundColor(Color.TRANSPARENT);
            refreshTv.setClickable(false);
        }
    }

    @Override
    public void onFinish() {
        refresh();
    }
}
