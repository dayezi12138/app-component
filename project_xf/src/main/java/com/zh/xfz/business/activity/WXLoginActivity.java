package com.zh.xfz.business.activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.widget.EditText;
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
import com.zh.xfz.utils.AndroidUtils;
import com.zh.xfz.utils.MyCountDownTimer;
import com.zh.xfz.utils.NotEmptyUtil;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;

import static com.zh.xfz.constans.RequestParamsConstant.CODE;
import static com.zh.xfz.constans.RequestParamsConstant.MOBILE;
import static com.zh.xfz.constans.RequestParamsConstant.TIME_STAMP;
import static com.zh.xfz.constans.RequestParamsConstant.WX_ACCESS_TOKEN_ID;

/**
 * author : dayezi
 * data :2019/12/5
 * description:
 */
@Route(path = WXLoginActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_ios)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color)
public class WXLoginActivity extends BaseActivity implements SmsCodeContract.SendSmsUI, MyCountDownTimer.CountDownListener {
    public final static String AROUTER_PATH = "/login/WXLoginActivity/";
    public final static String WX_OPENID_KEY = "openId";
    public final static String WX_UNIONID_KEY = "unionid";
    public final static String WX_ACCESS_TOKEN_KEY = "access_token";
    @Autowired(name = WX_OPENID_KEY)
    String openId;

    @Autowired(name = WX_UNIONID_KEY)
    String unionid;

    @Autowired(name = WX_ACCESS_TOKEN_KEY)
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
    MyCountDownTimer myCountDownTimer;

    @Inject
    UserPresenter userPresenter;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_wx_login;
    }

    @Override
    public void init() {
        myCountDownTimer.setCountDownListener(this);
    }

    private void refresh() {
        sendTv.setText(getResources().getString(R.string.act_valid_note_refresh_note_str));
        sendTv.setBackgroundResource(R.drawable.underline_layer_list);
        sendTv.setClickable(true);
    }

    @OnClick(R.id.send_tv)
    public void clickSend() {
        if (NotEmptyUtil.isEmpty(accountEt.getText().toString(), getResources().getString(R.string.act_mobile_not_empty_toast_msg)))
            return;
        presenter.getCode(accountEt.getText().toString(), Constants.SMS_STATUS_REGISTER_CODE);
    }

    @Override
    public void showMsg(String msg) {
        super.showMsg(msg);
        myCountDownTimer.cancel();
    }

    @OnClick(R.id.submit_btn)
    public void submit() {
        if (NotEmptyUtil.isEmpty(accountEt.getText().toString(), getResources().getString(R.string.act_mobile_not_empty_toast_msg)))
            return;
        if (NotEmptyUtil.isEmpty(noteEt.getText().toString(), getResources().getString(R.string.act_sms_code_not_empty_toast_msg)))
            return;
        Map<String, String> params = new HashMap<>(6);
        params.put(WX_OPENID_KEY, openId);
        params.put(WX_UNIONID_KEY, unionid);
        params.put(CODE, noteEt.getText().toString());
        params.put(TIME_STAMP, AndroidUtils.getUUID());
        params.put(WX_ACCESS_TOKEN_ID, accessToken);
        params.put(MOBILE, accountEt.getText().toString());
        userPresenter.wxRegister(params);
    }

    @Override
    public void sendSuccess() {
        myCountDownTimer.start();
    }


    @Override
    public void onTick(int second) {
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
}
