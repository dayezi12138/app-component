package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shehuan.niv.NiceImageView;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.Account;
import com.zh.xfz.utils.LoginUtils;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/10/29
 * description:
 */
@Route(path = PersonDetailInfoActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "个人信息")
public class PersonDetailInfoActivity extends BaseActivity {
    public final static String AROUTER_PATH = "/main/PersonDetailInfoActivity/";
    @BindView(R.id.portrait_iv)
    NiceImageView imageView;

    @BindView(R.id.name_tv)
    TextView nameTv;

    @BindView(R.id.mobile_tv)
    TextView mobileTv;

    @BindView(R.id.company_tv)
    TextView companyTv;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_person_detail_info;
    }

    @Override
    public void init() {
//        Account userInfo = LoginUtils.getUserInfo();
//        if (userInfo !=null && !StringUtils.isEmpty(userInfo.getUserIcon()))
//            Glide.with(getMyActivity()).load(userInfo.getUserIcon())
//                    .error(R.drawable.rc_default_portrait)
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)//关闭Glide的硬盘缓存机制
//                    .into(imageView);
//        if (userInfo != null) {
//            nameTv.setText(userInfo.getChineseName());
//            mobileTv.setText(userInfo.getMobile());
//            if (userInfo.getTenant()!= null&& userInfo.getTenant().size()>0){
//                companyTv.setText(userInfo.getTenant().get(0).getTenantName());
//            }
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Account userInfo = LoginUtils.getUserInfo();
        if (userInfo != null && !StringUtils.isEmpty(userInfo.getUserIcon()))
            Glide.with(getMyActivity()).load(userInfo.getUserIcon())
                    .error(R.drawable.rc_default_portrait)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)//关闭Glide的硬盘缓存机制
                    .into(imageView);
        if (userInfo != null) {
            nameTv.setText(userInfo.getChineseName());
            mobileTv.setText(userInfo.getMobile());
            if (userInfo.getTenant() != null && userInfo.getTenant().size() > 0) {
                companyTv.setText(userInfo.getTenant().get(0).getTenantName());
            }
        }
    }

    @OnClick(R.id.portrait_iv)
    public void portraitClick() {
        ARouter.getInstance().build(UpLoadPortraitActivity.AROUTER_PATH).navigation();
    }

    @OnClick(R.id.name_ly)
    public void nameClick() {
        ARouter.getInstance().build(UpdatePersonNameActivity.AROUTER_PATH)
                .withString(UpdatePersonNameActivity.NAME_KEY, nameTv.getText().toString()).navigation();
    }

    @OnClick(R.id.company_ly)
    public void companyClick() {
        ARouter.getInstance().build(CompanyActivity.AROUTER_PATH).navigation();
    }
}
