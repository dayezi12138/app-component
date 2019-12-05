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
import com.zh.xfz.bean.activity.Account;
import com.zh.xfz.bean.activity.UserInfo;
import com.zh.xfz.business.activity.MyCompanyActivity;
import com.zh.xfz.business.activity.PersonCardActivity;
import com.zh.xfz.business.activity.PersonDetailInfoActivity;
import com.zh.xfz.business.activity.SettingActivity;
import com.zh.xfz.mvp.contract.activity.UserOperationContract;
import com.zh.xfz.mvp.presenter.UserOperationPresenter;
import com.zh.xfz.utils.LoginUtils;

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
public class MineFragment extends BaseFragment implements UserOperationContract.UserOperationUI {

    @BindView(R.id.name_tv)
    TextView nameTv;

    @BindView(R.id.img_iv)
    NiceImageView imageView;

    @Inject
    UserOperationPresenter presenter;

//    private MyPopupWindow myPopupWindow;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void init() {
        Account userInfo = LoginUtils.getUserInfo();
        if (userInfo != null && !StringUtils.isEmpty(userInfo.getUserIcon()))
            Glide.with(getMyActivity()).load(userInfo.getUserIcon())
                    .error(R.drawable.rc_default_portrait)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)//关闭Glide的硬盘缓存机制
                    .into(imageView);
//        presenter.getUserInfo(LoginUtils.getUserId());
//        myPopupWindow = new MyPopupWindow.Builder(LayoutInflater.from(getMyActivity()).inflate(R.layout.qcrode_view, null), getActivity())
//                .height(ConvertUtils.dp2px(300)).width(ScreenUtils.getScreenWidth() - ScreenUtils.getScreenWidth() / 5).build();
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

    @OnClick(R.id.img_iv)
    public void clickPortrait() {
        ARouter.getInstance().build(PersonDetailInfoActivity.AROUTER_PATH).navigation();
    }


    @OnClick(R.id.my_company_ly)
    public void clickCompany() {
        ARouter.getInstance().build(MyCompanyActivity.AROUTER_PATH).navigation();
    }

    @Override
    public void userInfoSuccess(UserInfo userInfo) {
        nameTv.setText(userInfo.getMobile());
    }
}
