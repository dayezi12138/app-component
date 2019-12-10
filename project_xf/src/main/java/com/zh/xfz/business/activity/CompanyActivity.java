package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.Account;
import com.zh.xfz.utils.LoginUtils;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.view.MyPopupWindow;

/**
 * author : dayezi
 * data :2019/10/29
 * description:
 */
@Route(path = CompanyActivity.AROUTER_PATH)
@ToolbarLeft(menuId = R.menu.more_info_white_menu)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white, title = "我")
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "所属公司")
public class CompanyActivity extends BaseActivity implements View.OnClickListener {
    public final static String AROUTER_PATH = "/main/CompanyActivity/";
    @Inject
    MyPopupWindow popupWindow;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.companyfull_tv)
    TextView companyfullTv;

    @BindView(R.id.companyeasy_tv)
    TextView companyeasyTv;

    @BindView(R.id.founder_tv)
    TextView founderTv;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_company;
    }

    @Override
    public void init() {
        Account userInfo = LoginUtils.getUserInfo();
        if (userInfo != null) {

            if (userInfo.getTenant()!= null&& userInfo.getTenant().size()>0){
                companyfullTv.setText(userInfo.getTenant().get(0).getTenantName());
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sure_btn:
                popupWindow.dismiss();
                showMsg("该功能正在开发中....");
                break;
            case R.id.cancel_btn:
                popupWindow.dismiss();
                break;
        }
    }

    @OnMenuOnclick
    public void menuClick() {
        if (popupWindow.isShowing()) return;
        popupWindow.setBackgroundAlpha();
        popupWindow.showAtLocation(toolbar, Gravity.BOTTOM, 0, 0);
    }
}
