package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/10/29
 * description:
 */
@Route(path = UpdatePhoneSmsActivity.AROUTER_PATH)
@ToolbarLeft(menuId = R.menu.menu_complete)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "绑定手机号")
public class UpdatePhoneSmsActivity extends BaseActivity {
    public final static String AROUTER_PATH = "/main/UpdatePhoneSmsActivity/";
    public final static String KEY_PHONE = "KEY_PHONE";

    @BindView(R.id.phone_tv)
    TextView phoneTv;

    @Autowired(name = KEY_PHONE)
    String phone;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_update_phone_sms;
    }

    @Override
    public void init() {
        phoneTv.setText(phone);
    }
}
