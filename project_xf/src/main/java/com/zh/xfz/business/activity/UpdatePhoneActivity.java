package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.StringUtils;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.utils.LoginUtils;

import java.text.MessageFormat;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;

import static com.zh.xfz.business.activity.UpdatePhoneSmsActivity.KEY_PHONE;

/**
 * author : dayezi
 * data :2019/10/29
 * description:
 */
@Route(path = UpdatePhoneActivity.AROUTER_PATH)
@ToolbarLeft(menuId = R.menu.menu_next_step)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "绑定手机")
public class UpdatePhoneActivity extends BaseActivity {
    public final static String AROUTER_PATH = "/main/UpdatePhoneActivity/";

    @BindView(R.id.text_tv)
    EditText textTv;

    @BindView(R.id.mobile_tv)
    TextView mobileTv;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_update_phone;
    }

    @Override
    public void init() {
        mobileTv.setText(MessageFormat.format(getResources().getString(R.string.act_update_mobile_str), LoginUtils.getUserInfo().getMobile()));
    }

    @OnMenuOnclick
    public void menuClick() {
        if (StringUtils.isEmpty(textTv.getText().toString())) {
            showMsg("号码不能为空");
            return;
        }
        ARouter.getInstance().build(UpdatePhoneSmsActivity.AROUTER_PATH).withString(KEY_PHONE, textTv.getText().toString()).navigation();
        finish();
    }
}
