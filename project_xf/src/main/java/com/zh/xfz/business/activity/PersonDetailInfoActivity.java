package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;

import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/10/29
 * description:
 */
@Route(path = PersonDetailInfoActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white, title = "我")
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "个人信息")
public class PersonDetailInfoActivity extends BaseActivity {
    public final static String AROUTER_PATH = "/main/PersonDetailInfoActivity/";

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_person_detail_info;
    }

    @Override
    public void init() {

    }

    @OnClick(R.id.portrait_iv)
    public void portraitClick() {
        ARouter.getInstance().build(UpLoadPortraitActivity.AROUTER_PATH).navigation();
    }

    @OnClick(R.id.name_ly)
    public void nameClick() {
        ARouter.getInstance().build(UpdatePersonNameActivity.AROUTER_PATH).navigation();
    }

    @OnClick(R.id.company_ly)
    public void companyClick() {
        ARouter.getInstance().build(CompanyActivity.AROUTER_PATH).navigation();
    }
}
