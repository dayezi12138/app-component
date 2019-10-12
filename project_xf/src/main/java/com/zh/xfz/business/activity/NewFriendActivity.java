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
import com.zh.xfz.mvp.contract.activity.NewFriendContract;
import com.zh.xfz.mvp.presenter.activity.NewFriendPresenter;

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
//@ToolbarLeft(menuId = R.menu.new_friend)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_ios)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "新朋友")
public class NewFriendActivity extends BaseActivity implements NewFriendContract.NewFriendUI, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    public final static String AROUTER_PATH = "/main/NewFriendActivity/";

    @Inject
    NewFriendPresenter presenter;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

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
        contactAdapter.setAddFriendListner(position -> presenter.applyFriend(mDateList.get(position), position));
    }


    @Override
    public void successFriends(List<FriendInfo> sortModels, boolean refresh, boolean more) {
        if (refresh) {
            mDateList.clear();
        }
        swipeRefreshLayout.setRefreshing(false);
        mDateList.addAll(sortModels);
        contactAdapter.setNewData(mDateList);
        if (!more) contactAdapter.loadMoreEnd();
    }

    @Override
    public void successApplyFriend(int position) {
        mDateList.get(position).setStatus(2);
        contactAdapter.notifyDataSetChanged();
        MessageEvent messageEvent = new MessageEvent(CONTACT_EVENT_KEY, "");
        EventBus.getDefault().post(messageEvent);
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

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        presenter.refresh();
    }
}
