package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.mvp.contract.activity.UserOperationContract;
import com.zh.xfz.mvp.presenter.UserOperationPresenter;
import com.zh.xfz.utils.LoginUtils;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/10/29
 * description:
 */
@Route(path = UpdatePersonNameActivity.AROUTER_PATH)
@ToolbarLeft(menuId = R.menu.menu_complete)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white, title = "个人信息")
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "姓名")
public class UpdatePersonNameActivity extends BaseActivity implements UserOperationContract.UpdatePersonNameUI {
    public final static String AROUTER_PATH = "/main/UpdatePersonNameActivity/";

    public static final String NAME_KEY = "NAME_KEY";
    @Inject
    UserOperationPresenter mPresenter;

    @Autowired(name = NAME_KEY, required = true)
    String name;

    @BindView(R.id.text_tv)
    EditText nameTv;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_update_person_name;
    }

    @Override
    public void init() {
        nameTv.setText(name);
    }

    @OnMenuOnclick
    public void menuClick() {
        if (nameTv.getText().toString() != null) {
            mPresenter.updatePersonName(nameTv.getText().toString());
        }
    }

    @Override
    public void successData() {
        LoginUtils.ACCOUNT.setChineseName(nameTv.getText().toString());
        LoginUtils.saveLoginInfo(LoginUtils.ACCOUNT);
        finish();
        showMsg("更新完成");
    }
}
