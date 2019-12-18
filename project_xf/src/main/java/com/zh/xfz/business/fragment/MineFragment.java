package com.zh.xfz.business.fragment;

import android.support.annotation.NonNull;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shehuan.niv.NiceImageView;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.business.activity.BusinessListActivity;
import com.zh.xfz.business.activity.HelpActivity;
import com.zh.xfz.business.activity.MyCompanyActivity;
import com.zh.xfz.business.activity.PersonCardActivity;
import com.zh.xfz.business.activity.PersonDetailInfoActivity;
import com.zh.xfz.business.activity.SettingActivity;
import com.zh.xfz.db.bean.Tenant;
import com.zh.xfz.utils.LoginHandler;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseFragment;

/**
 * author : dayezi
 * data :2019/8/8
 * description:
 */
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "我的")
public class MineFragment extends BaseFragment  {

    @BindView(R.id.name_tv)
    TextView nameTv;

    @BindView(R.id.company_tv)
    TextView companyTv;

    @BindView(R.id.img_iv)
    NiceImageView imageView;

    @Inject
    LoginHandler loginHandler;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void init() {
    }

    @Override
    public void onResume() {
        super.onResume();
        Tenant tenant = loginHandler.getCurrentTenant();
        if (tenant != null)
            companyTv.setText(tenant.getTenantName());
        com.zh.xfz.db.bean.UserInfo userInfo = loginHandler.getCurrentUserInfo();
        if (userInfo != null && !StringUtils.isEmpty(userInfo.getUserIcon())) {
            Glide.with(getMyActivity()).load(userInfo.getUserIcon())
                    .error(R.drawable.ic_user_white)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)//关闭Glide的硬盘缓存机制
                    .into(imageView);
        }
        if (userInfo != null)
            nameTv.setText(StringUtils.isEmpty(userInfo.getChineseName()) ? userInfo.getMobile() : userInfo.getChineseName());
    }

    @OnClick(R.id.setting_ly)
    public void setting() {
//        //联系人类型
//        Bitmap bitmap = new QREncode.Builder(this)
//                .setParsedResultType(ParsedResultType.ADDRESSBOOK)
//                .setAddressBookUri(contactUri).build().encodeAsBitmap();

        ARouter.getInstance().build(SettingActivity.AROUTER_PATH).navigation();
    }

    @BindView(R.id.ly)
    LinearLayout linearLayout;

    @OnClick(R.id.qcrode_img)
    public void addressImg() {
        ARouter.getInstance().build(PersonCardActivity.AROUTER_PATH).navigation();
//        myPopupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);
    }

    @OnClick(R.id.content)
    public void clickPortrait() {
        ARouter.getInstance().build(PersonDetailInfoActivity.AROUTER_PATH).navigation();
    }

    @OnClick(R.id.my_company_ly)
    public void clickComany() {
        ARouter.getInstance().build(MyCompanyActivity.AROUTER_PATH).navigation();
    }

    @OnClick(R.id.help_ly)
    public void clickHelp() {
        ARouter.getInstance().build(HelpActivity.AROUTER_PATH).navigation();
    }

    @OnClick(R.id.test_tv)
    public void test() {
        ARouter.getInstance().build(BusinessListActivity.AROUTER_PATH).navigation();
    }


//    @Override
//    public void userInfoSuccess(UserInfo userInfo) {
//        nameTv.setText(userInfo.getMobile());
//    }
}
