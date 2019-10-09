package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.text.TextUtils;
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
import com.zh.xfz.mvp.contract.activity.LoginContract;
import com.zh.xfz.mvp.presenter.UserOperationPresenter;

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
public class InputPasswordActivity extends BaseActivity implements LoginContract.LoginUI {
    public final static String AROUTER_PATH = "/login/InputPasswordActivity/";
    public final static String ACCOUNT_KEY = "ACCOUNT_KEY";
    public final static String SMS_CODE = "SMS_CODE";

    @BindView(R.id.password_img)
    ImageView imageView;

    @BindView(R.id.password_et)
    EditText passwordEt;

    @Autowired(name = ACCOUNT_KEY)
    String account;

    @Autowired(name = SMS_CODE)
    String smsCode;

    @Inject
    UserOperationPresenter userOperationPresenter;


//    @Inject
//    LoginPresenter presenter;

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
        if (TextUtils.isEmpty(passwordEt.getText().toString())) {
            showMsg("密码不能为空");
            return;
        }
        userOperationPresenter.login(account, passwordEt.getText().toString());
//        presenter.loginNormal(account, passwordEt.getText().toString(), smsCode);
    }

    @OnClick(R.id.forget_tv)
    public void forgetPassword() {
        ARouter.getInstance().build(ValidNoteActivity.AROUTER_PATH).withString(ValidNoteActivity.ACCOUNT_KEY, account)
                .withBoolean(ValidNoteActivity.EXIST_ACCOUNT_KEY, true).withBoolean(ValidNoteActivity.REGISTER_KEY, true).navigation();
    }
}
