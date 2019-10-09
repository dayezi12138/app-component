package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/9/25
 * description:
 */
@Route(path = UpdatePasswordActivity.AROUTER_PATH)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "修改密码")
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_ios)
public class UpdatePasswordActivity extends BaseActivity {
    public final static String AROUTER_PATH = "/main/UpdatePasswordActivity/";

    @BindView(R.id.old_password_et)
    EditText oldPassowrd;

    @BindView(R.id.new_password1_et)
    EditText newPassword1;
    @BindView(R.id.new_password2_et)
    EditText newPassword2;

//    @Inject
//    UserOperationPresenter presenter;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_update_password;
    }

    @Override
    public void init() {

    }

    @OnClick(R.id.submit)
    public void submit() {
//        if (TextUtils.isEmpty(newPassword1.getText().toString()) || TextUtils.isEmpty(newPassword2.getText().toString()))
//            showMsg("新密码不能为空");
//        else
//            presenter.updatePassWord(oldPassowrd.getText().toString(), newPassword1.getText().toString(), newPassword2.getText().toString());
    }
}
