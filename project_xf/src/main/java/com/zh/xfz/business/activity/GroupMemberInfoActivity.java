package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.GroupInfo;
import com.zh.xfz.mvp.contract.ConversationContract;
import com.zh.xfz.mvp.presenter.ConversationPresenter;
import com.zh.xfz.utils.LoginHandler;

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
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color)
public class GroupMemberInfoActivity extends BaseActivity implements ConversationContract.GroupMemberUI {
    public final static String AROUTER_PATH = "/main/GroupMemberInfoActivity/";
    public final static String TARGET_KEY = "TARGET_KEY_ID";
    public final static String USERID_KEY = "USER_ID_KEY_ID";

    @Autowired(name = TARGET_KEY)
    String targetId;

    @Autowired(name = USERID_KEY)
    String userId;

    @Inject
    ConversationPresenter conversationPresenter;

    @BindView(R.id.tv_name)
    TextView nameTv;

    @BindView(R.id.alias_tv)
    TextView aliasTv;

    @BindView(R.id.memo)
    TextView memoTv;

    @Inject
    MaterialDialog.Builder builder;

    @Inject
    LoginHandler loginHandler;


    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_group_member_info;
    }

    @Override
    public void init() {
        if (userId.equals(String.valueOf(loginHandler.getCurrentUserId())))
            findViewById(R.id.submit_btn).setVisibility(View.GONE);
        conversationPresenter.getGroupMemberInfo(targetId, userId);
    }

    @OnClick(R.id.submit_btn)
    public void submit() {
        ARouter.getInstance().build(AddFriendActivity.AROUTER_PATH).navigation();
    }

    @Override
    public void groupMemberInfo(GroupInfo groupInfo) {
        if (groupInfo == null) return;
        nameTv.setText(groupInfo.getChineseName());
        memoTv.setText(groupInfo.getRemarkName());
    }
}
