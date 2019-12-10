package com.zh.xfz.business.activity;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.StringUtils;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.mvp.contract.SmsCodeContract;
import com.zh.xfz.mvp.presenter.SmsCodePresenter;
import com.zh.xfz.mvp.presenter.UserOperationPresenter;
import com.zh.xfz.utils.AndroidUtils;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/12/5
 * description:
 */
@Route(path = WXLoginActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_ios)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color)
public class WXLoginActivity extends BaseActivity implements SmsCodeContract.SmsCodeUI {
    public final static String AROUTER_PATH = "/login/WXLoginActivity/";

    @Autowired(name = "openId", required = true)
    String openId;

    @Autowired(name = "unionid", required = true)
    String unionid;

    @Autowired(name = "access_token", required = true)
    String accessToken;

    @BindView(R.id.send_tv)
    TextView sendTv;

    @BindView(R.id.note_et)
    EditText noteEt;

    @BindView(R.id.account_et)
    EditText accountEt;

    @Inject
    SmsCodePresenter presenter;

    @Inject
    UserOperationPresenter userOperationPresenter;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_wx_login;
    }

    @Override
    public void init() {
    }

    private CountDownTimer countDownTimer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            int second = (int) ((millisUntilFinished / 1000) % 60);
            sendTv.setText(second + "S");
            if (sendTv.isClickable()) {
                sendTv.setBackgroundColor(Color.TRANSPARENT);
                sendTv.setClickable(false);
            }
        }

        @Override
        public void onFinish() {
            refresh();
        }
    };

    private void refresh() {
        sendTv.setText(getResources().getString(R.string.act_valid_note_refresh_note_str));
        sendTv.setBackgroundResource(R.drawable.underline_layer_list);
        sendTv.setClickable(true);
    }

    @OnClick(R.id.send_tv)
    public void clickSend() {
        if (StringUtils.isEmpty(accountEt.getText().toString())) {
            showMsg("手机号不能为空");
            return;
        }
        presenter.getCode(accountEt.getText().toString(), 1);
    }

    @Override
    public void showMsg(String msg) {
        super.showMsg(msg);
        countDownTimer.cancel();
    }

    @OnClick(R.id.submit_btn)
    public void submit() {
        if (StringUtils.isEmpty(accountEt.getText().toString()) || StringUtils.isEmpty(noteEt.getText().toString())) {
            showMsg("手机号或者验证码不能为空");
            return;
        }
        Map<String, String> params = new HashMap<>(6);
        params.put("openid", openId);
        params.put("unionid", unionid);
        params.put("code", noteEt.getText().toString());
        params.put("timeStamp", AndroidUtils.getUUID());
        params.put("access_token", accessToken);
        params.put("mobile", accountEt.getText().toString());
        userOperationPresenter.wxRegister(params);
    }

    @Override
    public void sendSuccess() {
        countDownTimer.start();
    }

}
