package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.fragment.FriendInfo;
import com.zh.xfz.business.adapter.AddGroupMembersAdapter;
import com.zh.xfz.mvp.contract.activity.FriendContract;
import com.zh.xfz.mvp.contract.activity.GroupContract;
import com.zh.xfz.mvp.presenter.activity.FriendPresenter;
import com.zh.xfz.mvp.presenter.activity.GroupPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.bean.MessageEvent;

/**
 * author : dayezi
 * data :2019/9/24
 * description:
 */
@Route(path = AddGroupMembersActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_ios)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "创建群组")
@ToolbarLeft(menuId = R.menu.add_group_members)
public class AddGroupMembersActivity extends BaseActivity implements FriendContract.FriendUI, GroupContract.GroupUI, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    public final static String AROUTER_PATH = "/main/AddGroupMembersActivity/";
    public final static String ADD_GROUP = "ADD_GROUP_KEY";
    @Inject
    FriendPresenter presenter;

    @Inject
    GroupPresenter groupPresenter;

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Autowired(name = ADD_GROUP)
    String targetId;

    @Inject
    AddGroupMembersAdapter addGroupMembersAdapter;


    private List<FriendInfo> mDateList = new ArrayList<>();

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.swiper_recycler;
    }

    private Set<Integer> select = new HashSet<>();

    @Override
    public void init() {
        mRecyclerView.setPadding(0, ConvertUtils.dp2px(getResources().getDimension(R.dimen.widget_margin_tiny)), 0, 0);
        swipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(addGroupMembersAdapter);
        addGroupMembersAdapter.setOnLoadMoreListener(this, mRecyclerView);
        addGroupMembersAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        addGroupMembersAdapter.setEmptyView(R.layout.empty_view);
        addGroupMembersAdapter.setOnItemClickListener((adapter, view, position) -> {
            ImageView imageView = view.findViewById(R.id.img);
            if (select.contains(position)) {
                select.remove(position);
                imageView.setBackgroundResource(R.drawable.shape_item_add_group_members_unselect);
            } else {
                select.add(position);
                imageView.setBackgroundResource(R.drawable.shape_item_add_group_members_select);
            }
        });
//        if (isADD) toolbar.getMenu().clear();
        onRefresh();
    }

    @Override
    public void successFriends(List<FriendInfo> sortModels, boolean refresh, boolean more) {
        if (refresh) mDateList.clear();
        swipeRefreshLayout.setRefreshing(false);
        mDateList.addAll(sortModels);
        addGroupMembersAdapter.setNewData(mDateList);
        if (!more) addGroupMembersAdapter.loadMoreEnd();
    }

    @Override
    public void createGroupSuccess() {
        MessageEvent message = new MessageEvent(GroupMemberListActivity.MEMBER_EVENT_KEY, "");
        EventBus.getDefault().post(message);
        showMsg("创建成功");
        finish();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        presenter.refresh();
    }

    @Override
    public void onLoadMoreRequested() {
        swipeRefreshLayout.setRefreshing(true);
        presenter.loadMore();
    }

    @Override
    public void showMsg(String msg) {
        swipeRefreshLayout.setRefreshing(false);
        super.showMsg(msg);
    }

    @OnMenuOnclick
    public void menuClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_group_members:
                if (!TextUtils.isEmpty(targetId))
                    groupPresenter.inviteGroupMember(select, mDateList, targetId);

                else groupPresenter.addGroup(select, mDateList);
                break;
        }
    }
}
