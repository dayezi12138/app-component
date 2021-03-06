package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.StringUtils;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.db.bean.UserInfo;
import com.zh.xfz.mvp.presenter.UserPresenter;
import com.zh.xfz.utils.LoginHandler;

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
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "姓名")
public class UpdatePersonNameActivity extends BaseActivity {
    public final static String AROUTER_PATH = "/main/UpdatePersonNameActivity/";

    public static final String NAME_KEY = "NAME_KEY";

    @Inject
    UserPresenter userPresenter;

    @Autowired(name = NAME_KEY, required = true)
    String name;

    @BindView(R.id.text_tv)
    EditText nameTv;
    @Inject
    LoginHandler loginHandler;

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
            try {
                UserInfo userInfo = loginHandler.getCurrentUserInfo();
                userPresenter.updatePersonName(nameTv.getText().toString(), StringUtils.isEmpty(userInfo.getUserIcon()) ? "" : userInfo.getUserIcon());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
