package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.GroupListInfo;
import com.zh.xfz.business.adapter.GroupAdapter;
import com.zh.xfz.mvp.contract.ConversationContract;
import com.zh.xfz.mvp.presenter.ConversationPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;
import io.rong.imkit.RongIM;

/**
 * author : dayezi
 * data :2019/9/24
 * description:
 */
@Route(path = GroupActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "群组")
@ToolbarLeft(menuId = R.menu.group_add)
public class GroupActivity extends BaseActivity implements ConversationContract.GroupListUI {
    public final static String AROUTER_PATH = "/main/GroupActivity/";

    @Inject
    ConversationPresenter conversationPresenter;

    @Inject
    GroupAdapter groupAdapter;

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.recycler_;
    }

    @Override
    public void init() {
        conversationPresenter.groupList();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(groupAdapter);
        groupAdapter.setOnItemClickListener((adapter, view, position) -> RongIM.getInstance().startGroupChat(GroupActivity.this, String.valueOf(groupAdapter.getData().get(position).getID()), groupAdapter.getData().get(position).getGroupName()));
    }



    @OnMenuOnclick
    public void menuClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.group_add:
                ARouter.getInstance().build(AddGroupMembersActivity.AROUTER_PATH).navigation();
                break;
        }
    }

    @Override
    public void listData(List<GroupListInfo> data, boolean isRefresh) {
        groupAdapter.setNewData(data);
    }
}
