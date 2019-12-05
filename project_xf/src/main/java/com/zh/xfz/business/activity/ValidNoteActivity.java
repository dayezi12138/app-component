package com.zh.xfz.business.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.Account;
import com.zh.xfz.mvp.contract.activity.ValidNoteContract;
import com.zh.xfz.mvp.presenter.activity.ValidNotePresenter;
import com.zh.xfz.utils.LoginUtils;

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
public class ValidNoteActivity extends BaseActivity implements ValidNoteContract.ValidNoteUI {
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
    ValidNotePresenter presenter;

//    @Inject
//    UserOperationPresenter userOperationPresenter;

    private CountDownTimer countDownTimer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            int second = (int) ((millisUntilFinished / 1000) % 60);
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
    };


    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_valid_note;
    }

    @Override
    public void init() {
        phoneTv.setText(account);
//        userOperationPresenter.getCode(account, isRegister);
        presenter.getCode(account, existAccount);
        verifyCodeView.setOnAllFilledListener(text -> {
                    presenter.loginOrRegister(account, text, existAccount);
//                    if (!isRegister) userOperationPresenter.register(account, text);
//                    else
//                        ARouter.getInstance().build(AddPasswordActivity.AROUTER_PATH).withString(ACCOUNT_KEY, account).withString(CODE_KEY, text).navigation();
                }
//                presenter.loginOrRegister(account, text, existAccount)
        );
    }

    @Override
    public void success() {
        countDownTimer.start();
    }

    @SuppressLint("WrongConstant")
    @Override
    public void loginOrRegisterSuccess(Account account, String userId) {
        LoginUtils.ACCOUNT = account;
        LoginUtils.saveLoginInfo(account);
        if (account.getTenant() != null && !account.getTenant().isEmpty())
            ARouter.getInstance().build(MainActivity.AROUTER_PATH).navigation();
        else
            ARouter.getInstance().build(CreateBusinessActivity.AROUTER_PATH).navigation();
        finish();
    }

    @Override
    public void registerSuccess(Account account, String userId) {
        LoginUtils.ACCOUNT = account;
        LoginUtils.saveLoginInfo(account);
        ARouter.getInstance().build(AddPasswordActivity.AROUTER_PATH).navigation();
        finish();
    }

    @Override
    public void error(String msg) {
        showMsg(msg);
        countDownTimer.cancel();
        refresh();
    }

    private void refresh() {
        refreshTv.setText(getResources().getString(R.string.act_valid_note_refresh_note_str));
        refreshTv.setBackgroundResource(R.drawable.underline_layer_list);
        refreshTv.setClickable(true);
    }

    @OnClick(R.id.refresh_tv)
    public void refreshSms() {
        presenter.getCode(account, existAccount);
        countDownTimer.start();
    }

    @Override
    public void showMsg(String msg) {
        refresh();
        super.showMsg(msg);
    }
}
