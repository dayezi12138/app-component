package com.zh.xfz.business.activity;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.GroupInfo;
import com.zh.xfz.bean.fragment.FriendInfo;
import com.zh.xfz.business.adapter.GroupMemberAdapter;
import com.zh.xfz.mvp.contract.activity.GroupContract;
import com.zh.xfz.mvp.presenter.activity.GroupPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.bean.MessageEvent;

/**
 * author : dayezi
 * data :2019/9/25
 * description:
 */
@Route(path = GroupMemberListActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "成员信息")
@ToolbarLeft(menuId = R.menu.group_add)
public class GroupMemberListActivity extends BaseActivity implements GroupContract.GroupUI {
    public static int MEMBER_EVENT_KEY = 360002;
    public final static String AROUTER_PATH = "/main/GroupMemberListActivity/";
    public final static String GROUP_ID_KEY = "GROUP_ID_KEY";
    public final static String TRANSER_KEY = "TRANSER_KEY";
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    @Inject
    GroupPresenter presenter;

    @Inject
    GroupMemberAdapter groupMemberAdapter;

    @Autowired(name = GROUP_ID_KEY, required = true)
    String groupId;

    @Autowired(name = TRANSER_KEY)
    boolean isTranser;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    MaterialDialog.Builder builder;
    private Dialog dialog;
    private GroupInfo groupInfo;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.recycler_;
    }

    @Override
    public void init() {
        mRecyclerView.setBackgroundResource(R.color.white);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(groupMemberAdapter);
        groupMemberAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (isTranser) {
                if (dialog != null && !dialog.isShowing()) dialog.show();
                this.groupInfo = groupMemberAdapter.getData().get(position);
            } else {
                FriendInfo friendInfo = new FriendInfo();
                friendInfo.setSourceId(Long.valueOf(groupMemberAdapter.getData().get(position).getGroupId()));
                friendInfo.setTargetId(Long.valueOf(groupMemberAdapter.getData().get(position).getTargetId()));
                ARouter.getInstance().build(FriendDetailActivity.AROUTER_PATH).withSerializable(FriendDetailActivity.FRIEND_DETAIL_KEY, friendInfo).navigation();
            }
        });
        builder.onPositive((dialog, which) -> {
            if (groupInfo != null)
                presenter.transferGroupAdministrator(groupId, String.valueOf(groupInfo.getTargetId()), dialog);
        }).onNegative((dialog, which) -> groupInfo = null);
        dialog = builder.build();
        presenter.getGroupMemberList(groupId);
        EventBus.getDefault().register(this);
//        toolbar.getMenu().findItem(R.id.group_transfer).setVisible(false);
        toolbar.getMenu().findItem(R.id.group_add).setVisible(!isTranser);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(MessageEvent message) {
        if (message.getCode() == MEMBER_EVENT_KEY) presenter.getGroupMemberList(groupId);
    }

    @Override
    public void successGroupInfoList(List<GroupInfo> data) {
        groupMemberAdapter.setNewData(data);
    }

    @OnMenuOnclick
    public void menuClick(MenuItem item) {
        ARouter.getInstance().build(AddGroupMembersActivity.AROUTER_PATH).withString(AddGroupMembersActivity.ADD_GROUP, groupId).navigation();
    }
}
