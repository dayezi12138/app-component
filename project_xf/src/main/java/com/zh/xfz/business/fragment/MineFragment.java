package com.zh.xfz.business.fragment;

import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.UserInfo;
import com.zh.xfz.business.activity.SettingActivity;
import com.zh.xfz.mvp.contract.activity.UserOperationContract;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.view.MyPopupWindow;

/**
 * author : dayezi
 * data :2019/8/8
 * description:
 */
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "我的")
public class MineFragment extends BaseFragment implements UserOperationContract.UserOperationUI {


    @BindView(R.id.name_tv)
    TextView nameTv;

    private MyPopupWindow myPopupWindow;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void init() {
//        presenter.getUserInfo(LoginUtils.getUserId());
        myPopupWindow = new MyPopupWindow.Builder(LayoutInflater.from(getMyActivity()).inflate(R.layout.qcrode_view, null), getActivity())
                .height(ConvertUtils.dp2px(300)).width(ScreenUtils.getScreenWidth() - ScreenUtils.getScreenWidth() / 5).build();
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
        myPopupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);
    }

    @Override
    public void userInfoSuccess(UserInfo userInfo) {
        nameTv.setText(userInfo.getMobile());
    }
}
