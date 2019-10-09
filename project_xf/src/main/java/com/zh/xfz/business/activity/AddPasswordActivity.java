package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.mvp.contract.activity.UserOperationContract;
import com.zh.xfz.mvp.presenter.UserOperationPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/9/27
 * description:
 */
@Route(path = AddPasswordActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_ios)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color)
public class AddPasswordActivity extends BaseActivity implements UserOperationContract.AddPasswordUI {
    public final static String AROUTER_PATH = "/login/AddPasswordActivity/";
    public final static String OLD_PASSWORD_KEY = "OLD_PASSWORD_KEY";
    public final static String CODE_KEY = "OLD_PASSWORD_KEY";

    //    public final static String IS_ADD_KEY = "IS_ADD_KEY";
    @BindView(R.id.password_et)
    EditText passwordEt;

    @BindView(R.id.password_again_et)
    EditText againPasswordEt;

    @Autowired(name = OLD_PASSWORD_KEY)
    String oldPassword = "";

    @Autowired(name = InputPasswordActivity.ACCOUNT_KEY)
    String account;

    @Autowired(name = CODE_KEY)
    String code;

    @Inject
    UserOperationPresenter presenter;


    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_add_password;
    }

    @Override
    public void init() {

    }

    @OnClick(R.id.submit_btn)
    public void submit() {
        if (TextUtils.isEmpty(passwordEt.getText().toString()) || TextUtils.isEmpty(againPasswordEt.getText().toString())) {
            showMsg("密码不能为空");
            return;
        }
        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(code))
            presenter.forgetPassWord(account, passwordEt.getText().toString(), againPasswordEt.getText().toString(), code);
        else
            presenter.updatePassWord(oldPassword, passwordEt.getText().toString(), againPasswordEt.getText().toString());
    }

    @Override
    public void success() {
        if (TextUtils.isEmpty(oldPassword))
            ARouter.getInstance().build(CreateBusinessActivity.AROUTER_PATH).navigation();
//        else {
////            presenter.login();
//        }
    }
}
