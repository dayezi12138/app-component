package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.fragment.FriendInfo;
import com.zh.xfz.business.adapter.ContactAdapter;
import com.zh.xfz.constans.Constants;
import com.zh.xfz.mvp.contract.ConversationContract;
import com.zh.xfz.mvp.presenter.ConversationPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.bean.MessageEvent;

import static com.zh.xfz.business.fragment.ContactFragment.CONTACT_EVENT_KEY;

/**
 * author : dayezi
 * data :2019/9/12
 * description:
 */
@Route(path = NewFriendActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "新朋友")
public class NewFriendActivity extends BaseActivity implements ConversationContract.NewFriendListUI, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    public final static String AROUTER_PATH = "/main/NewFriendActivity/";

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    @Inject
    ConversationPresenter conversationPresenter;
    @Inject
    ContactAdapter contactAdapter;

    private List<FriendInfo> mDateList = new ArrayList<>();

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_new_friend;
    }

    @Override
    public void init() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(contactAdapter);
        contactAdapter.setOnLoadMoreListener(this, mRecyclerView);
        contactAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        contactAdapter.setEmptyView(R.layout.empty_view);
        swipeRefreshLayout.setOnRefreshListener(this);
        onRefresh();
        contactAdapter.setAddFriendListner(position -> conversationPresenter.agreeNewFriend(mDateList.get(position), position));
    }

    @Override
    public void onLoadMoreRequested() {
        swipeRefreshLayout.setRefreshing(true);
        conversationPresenter.onLoadMoreNewFriend();
    }

    @Override
    public void showMsg(String msg) {
        swipeRefreshLayout.setRefreshing(false);
        super.showMsg(msg);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        conversationPresenter.onRefreshNewFriend();
    }

    @Override
    public void listData(List<FriendInfo> data, boolean isRefresh) {
        if (isRefresh) mDateList.clear();
        swipeRefreshLayout.setRefreshing(false);
        mDateList.addAll(data);
        contactAdapter.setNewData(mDateList);
        if (data.size() < Constants.PAGESIZE) contactAdapter.loadMoreEnd();
    }

    @Override
    public void agreeNewFriend(int position) {
        mDateList.get(position).setStatus(2);
        contactAdapter.notifyDataSetChanged();
        MessageEvent messageEvent = new MessageEvent(CONTACT_EVENT_KEY, "");
        EventBus.getDefault().post(messageEvent);
    }
}
