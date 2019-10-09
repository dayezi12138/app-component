package com.zh.xfz.business.activity;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.shehuan.niv.NiceImageView;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.fragment.FriendInfo;
import com.zh.xfz.mvp.contract.activity.FriendDetailContract;
import com.zh.xfz.mvp.presenter.activity.FriendDetailPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.view.MyPopupWindow;
import io.rong.imkit.RongIM;

/**
 * author : dayezi
 * data :2019/9/20
 * description:
 */
@Route(path = FriendDetailActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_ios)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color)
@ToolbarLeft(menuId = R.menu.friend_detail)
public class FriendDetailActivity extends BaseActivity implements View.OnClickListener, FriendDetailContract.FriendDetailUI, MaterialDialog.SingleButtonCallback {
    public final static String AROUTER_PATH = "/main/FriendDetailActivity/";
    public final static String FRIEND_DETAIL_KEY = "FRIEND_DETAIL_KEY";

    @Autowired(name = FRIEND_DETAIL_KEY)
    FriendInfo friendInfo;

    @BindView(R.id.img)
    NiceImageView niceImageView;

    @BindView(R.id.tv_name)
    TextView nameTv;

    @BindView(R.id.memo)
    TextView memo;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    MyPopupWindow popupWindow;

    @Inject
    FriendDetailPresenter presenter;

//    @Inject
//    UserOperationPresenter userOperationPresenter;

    @Inject
    Dialog dialog;


    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_friend_detail;
    }

    @Override
    public void init() {
        nameTv.setText(friendInfo.getName());
        if (TextUtils.isEmpty(friendInfo.getPath()))
            Glide.with(this).load(friendInfo.getPath()).into(niceImageView);
        TextView textView = new TextView(this);
        Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER;
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setGravity(Gravity.CENTER);
        textView.setText(friendInfo.getName());
        toolbar.addView(textView);
        memo.setText(friendInfo.getRemark());
    }

    @OnClick(R.id.submit_btn)
    public void submit() {
        RongIM.getInstance().startPrivateChat(this, String.valueOf(friendInfo.getTargetId()), friendInfo.getName());
    }

    @OnClick(R.id.memo)
    public void updateMemo() {
        dialog.show();
    }

    @OnMenuOnclick
    public void menuClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_friend_operation:
                if (popupWindow.isShowing()) return;
                popupWindow.showAtLocation(findViewById(R.id.ly), Gravity.BOTTOM, 0, 0);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete:
                presenter.delete(String.valueOf(friendInfo.getID()));
                break;
            case R.id.cancel:
                success();
                break;
        }
    }

    @Override
    public void success() {
        if (popupWindow.isShowing()) popupWindow.dismiss();
    }

    @Override
    public void updateMemo(String name) {
        memo.setText(name);
    }

    @Override
    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
        EditText editText = dialog.getCustomView().findViewById(R.id.memo);
        presenter.updateMemo(String.valueOf(friendInfo.getID()), editText.getText().toString());
        editText.setText("");
        dialog.dismiss();
    }
}
