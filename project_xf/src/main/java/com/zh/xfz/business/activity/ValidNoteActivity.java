package com.zh.xfz.business.activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.constans.Constants;
import com.zh.xfz.mvp.contract.SmsCodeContract;
import com.zh.xfz.mvp.presenter.SmsCodePresenter;
import com.zh.xfz.mvp.presenter.UserPresenter;
import com.zh.xfz.utils.MyCountDownTimer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.view.VerifyCodeView;

/**
 * author : dayezi
 * data :2019/7/18
 * description:
 */
@Route(path = ValidNoteActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_ios)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color)
public class ValidNoteActivity extends BaseActivity implements MyCountDownTimer.CountDownListener, SmsCodeContract.SendSmsUI {
    public final static String AROUTER_PATH = "/login/ValidNoteActivity/";
    public final static String ACCOUNT_KEY = "ACCOUNT_KEY";
    public final static String EXIST_ACCOUNT_KEY = "EXIST_ACCOUNT_KEY";
    public final static String REGISTER_KEY = "REGISTER_KEY";

    @BindView(R.id.valid_note_view)
    VerifyCodeView verifyCodeView;

    @BindView(R.id.refresh_tv)
    TextView refreshTv;

    @BindView(R.id.phone_tv)
    TextView phoneTv;

    @Autowired(name = ACCOUNT_KEY)
    String account;

    @Autowired(name = EXIST_ACCOUNT_KEY)
    boolean existAccount;

    @Autowired(name = REGISTER_KEY)
    boolean isRegister;

    @Inject
    SmsCodePresenter smsCodePresenter;

    @Inject
    UserPresenter userPresenter;
    @Inject
    MyCountDownTimer myCountDownTimer;


    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_valid_note;
    }

    @Override
    public void init() {
        phoneTv.setText(account);
        myCountDownTimer.setCountDownListener(this);
        smsCodePresenter.getCode(account, !existAccount ? Constants.SMS_STATUS_REGISTER_CODE : Constants.SMS_STATUS_RETRIEVE_CODE);
        verifyCodeView.setOnAllFilledListener(text -> userPresenter.doSMS(account, text, existAccount)
        );
    }

    private void refresh() {
        refreshTv.setText(getResources().getString(R.string.act_valid_note_refresh_note_str));
        refreshTv.setBackgroundResource(R.drawable.underline_layer_list);
        refreshTv.setClickable(true);
    }

    @OnClick(R.id.refresh_tv)
    public void refreshSms() {
        smsCodePresenter.getCode(account, !existAccount ? Constants.SMS_STATUS_REGISTER_CODE : Constants.SMS_STATUS_RETRIEVE_CODE);
        myCountDownTimer.start();
    }

    @Override
    public void showMsg(String msg) {
        refresh();
        super.showMsg(msg);
        myCountDownTimer.cancel();
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

    @Override
    public void sendSuccess() {
        myCountDownTimer.start();
    }
}
