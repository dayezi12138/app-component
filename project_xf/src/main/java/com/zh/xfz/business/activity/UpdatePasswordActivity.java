package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.mvp.presenter.UserPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/9/25
 * description:
 */
@Route(path = UpdatePasswordActivity.AROUTER_PATH)
@ToolbarLeft(menuId = R.menu.menu_update)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "修改密码")
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
public class UpdatePasswordActivity extends BaseActivity {
    public final static String AROUTER_PATH = "/main/UpdatePasswordActivity/";

    @BindView(R.id.old_password_et)
    EditText oldPassowrd;

    @BindView(R.id.new_password1_et)
    EditText newPassword1;
    @BindView(R.id.new_password2_et)
    EditText newPassword2;

    @Inject
    UserPresenter userPresenter;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_update_password;
    }

    @Override
    public void init() {

    }

    @OnMenuOnclick
    public void menuClick() {
        if (TextUtils.isEmpty(newPassword1.getText().toString()) || TextUtils.isEmpty(newPassword2.getText().toString())) {
            showMsg(getResources().getString(R.string.act_new_password_update_success_msg));
            return;
        }
        userPresenter.updatePassword(newPassword1.getText().toString(), newPassword2.getText().toString());
    }
}
