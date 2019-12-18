package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.mvp.presenter.UserPresenter;
import com.zh.xfz.utils.NotEmptyUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/7/18
 * description:
 */
@Route(path = InputPasswordActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_ios)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color)
public class InputPasswordActivity extends BaseActivity {
    public final static String AROUTER_PATH = "/login/InputPasswordActivity/";
    public final static String ACCOUNT_KEY = "ACCOUNT_KEY";

    @BindView(R.id.password_img)
    ImageView imageView;

    @BindView(R.id.password_et)
    EditText passwordEt;

    @Autowired(name = ACCOUNT_KEY)
    String account;

    @Inject
    UserPresenter userPresenter;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_normal_login;
    }

    @Override
    public void init() {

    }

    private boolean changeImage = false;

    @OnClick(R.id.password_img)
    public void changeImage() {
        int imageId = !changeImage ? R.drawable.ic_show_password : R.drawable.ic_hide_password;
        TransformationMethod method = !changeImage ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance();
        passwordEt.setTransformationMethod(method);
        changeImage = !changeImage;
        imageView.setBackgroundResource(imageId);
    }

    @OnClick(R.id.submit_btn)
    public void submit() {
        if (NotEmptyUtil.isEmpty(passwordEt.getText().toString(),
                getResources().getString(R.string.act_password_not_empty_toast_msg)))
            return;
        userPresenter.login(account, passwordEt.getText().toString());
    }

    @OnClick(R.id.forget_tv)
    public void forgetPassword() {
        ARouter.getInstance().build(ValidNoteActivity.AROUTER_PATH).withString(ValidNoteActivity.ACCOUNT_KEY, account)
                .withBoolean(ValidNoteActivity.EXIST_ACCOUNT_KEY, true).withBoolean(ValidNoteActivity.REGISTER_KEY, true).navigation();
    }

    @OnClick(R.id.sms_text)
    public void clickSMS() {
        ARouter.getInstance().build(ValidNoteActivity.AROUTER_PATH).withString(ValidNoteActivity.ACCOUNT_KEY, account)
                .withBoolean(ValidNoteActivity.EXIST_ACCOUNT_KEY, true).navigation();
    }
}
