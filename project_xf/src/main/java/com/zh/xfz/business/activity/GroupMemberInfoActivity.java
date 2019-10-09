package com.zh.xfz.business.activity;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.GroupInfo;
import com.zh.xfz.mvp.contract.activity.GroupContract;
import com.zh.xfz.mvp.presenter.activity.GroupPresenter;
import com.zh.xfz.utils.LoginUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/9/26
 * description:
 */
@Route(path = GroupMemberInfoActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_ios)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color)
public class GroupMemberInfoActivity extends BaseActivity implements GroupContract.GroupMemberInfoUI {
    public final static String AROUTER_PATH = "/main/GroupMemberInfoActivity/";
    public final static String TARGET_KEY = "TARGET_KEY_ID";
    public final static String USERID_KEY = "USER_ID_KEY_ID";

    @Autowired(name = TARGET_KEY)
    String targetId;

    @Autowired(name = USERID_KEY)
    String userId;

    @Inject
    GroupPresenter presenter;

    @BindView(R.id.tv_name)
    TextView nameTv;

    @BindView(R.id.alias_tv)
    TextView aliasTv;

    @BindView(R.id.memo)
    TextView memoTv;

    @Inject
    MaterialDialog.Builder builder;

    Dialog dialog;
    private String groupId = "";

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_group_member_info;
    }

    @Override
    public void init() {
        if (userId.equals(LoginUtils.getUserId()))
            findViewById(R.id.submit_btn).setVisibility(View.GONE);
        presenter.getGroupMemberInfo(targetId, userId);
    }

    @Override
    public void groupInfo(GroupInfo groupInfo) {
        if (groupInfo == null) return;
        nameTv.setText(groupInfo.getChineseName());
        memoTv.setText(groupInfo.getRemarkName());
        groupId = String.valueOf(groupInfo.getGroupId());
    }

    @OnClick(R.id.submit_btn)
    public void submit() {
        ARouter.getInstance().build(AddFriendActivity.AROUTER_PATH).navigation();
    }

    //    @OnClick(R.id.memo_ly)
    public void memoOnclick() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
            return;
        }
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_input, null);
        EditText inputEt = view.findViewById(R.id.memo);
        inputEt.setHint("请输入新的备注");
        builder.customView(view, false);
        dialog = builder.build();
        dialog.show();
        builder.onPositive((dialog, which) -> {
            if (!TextUtils.isEmpty(inputEt.getText().toString()))
                presenter.updateGroupMemberName(groupId, targetId, inputEt.getText().toString());
        });
    }
}
