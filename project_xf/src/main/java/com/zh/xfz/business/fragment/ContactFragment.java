package com.zh.xfz.business.fragment;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.fragment.FriendInfo;
import com.zh.xfz.business.activity.FriendDetailActivity;
import com.zh.xfz.business.activity.SearchFriendActivity;
import com.zh.xfz.business.adapter.ContactAdapter;
import com.zh.xfz.business.adapter.ContactSearchAdapter;
import com.zh.xfz.mvp.contract.fragment.ContactContract;
import com.zh.xfz.mvp.presenter.fragment.ContactPresenter;
import com.zh.xfz.views.sideBar.PinyinComparator;
import com.zh.xfz.views.sideBar.TitleItemDecoration;
import com.zh.xfz.views.sideBar.WaveSideBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.bean.MessageEvent;
import core.app.zh.com.core.view.ClearEditTextView;

/**
 * author : dayezi
 * data :2019/9/3
 * description:menu_fragment_contact
 */
@ToolbarLeft(menuId = R.menu.menu_fragment_contact)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "通讯录")
public class ContactFragment extends BaseFragment implements ContactContract.ContactUI, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    public static int CONTACT_EVENT_KEY = 360001;

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    @BindView(R.id.sideBar)
    WaveSideBar mSideBar;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.et_search)
    ClearEditTextView clearEditTextView;

    @BindView(R.id.search_recycler)
    RecyclerView searchRecyclerView;

    @BindView(R.id.rl)
    RelativeLayout relativeLayout;

    @Inject
    ContactSearchAdapter searchFriendAdapter;

    private List<FriendInfo> mDateList = new ArrayList<>();
    private TitleItemDecoration mDecoration;

    @Inject
    LinearLayoutManager manager;
    @Inject
    ContactAdapter contactAdapter;
    @Inject
    ContactPresenter presenter;
    @Inject
    Comparator mComparator;
    @Inject
    List<FriendInfo> friendInfoList;


    @SuppressLint("ValidFragment")
    private ContactFragment() {
    }

    public static ContactFragment getInstance() {
        return new ContactFragment();
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.fragment_contact;
    }

    @Override
    public void init() {
        mDateList.addAll(friendInfoList);
        mComparator = new PinyinComparator();
        mRecyclerView.setLayoutManager(manager);
        mDecoration = new TitleItemDecoration(getContext(), mDateList);
        mRecyclerView.setAdapter(contactAdapter);
        //如果add两个，那么按照先后顺序，依次渲染。
        mRecyclerView.addItemDecoration(mDecoration);
        //设置右侧SideBar触摸监听
        mSideBar.setOnTouchLetterChangeListener(letter -> {
            //该字母首次出现的位置
            int position = contactAdapter.getPositionForSection(letter.charAt(0));
            if (position != -1) {
                manager.scrollToPositionWithOffset(position, 0);
            }
        });
        contactAdapter.setOnLoadMoreListener(this, mRecyclerView);
        contactAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        contactAdapter.setEmptyView(R.layout.empty_view);
        contactAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (!TextUtils.isEmpty(mDateList.get(position).getPath()))
                ARouter.getInstance().build(mDateList.get(position).getPath()).navigation();
            else
                ARouter.getInstance().build(FriendDetailActivity.AROUTER_PATH).withSerializable(FriendDetailActivity.FRIEND_DETAIL_KEY, mDateList.get(position)).navigation();
        });
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        searchRecyclerView.setAdapter(searchFriendAdapter);
        searchFriendAdapter.setOnItemClickListener((adapter, view, position) -> ARouter.getInstance().build(FriendDetailActivity.AROUTER_PATH).withSerializable(FriendDetailActivity.FRIEND_DETAIL_KEY, searchFriendAdapter.getData().get(position)).navigation());
        swipeRefreshLayout.setOnRefreshListener(this);
        EventBus.getDefault().register(this);
        clearEditTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    relativeLayout.setVisibility(View.GONE);
                    searchRecyclerView.setVisibility(View.VISIBLE);
                    searchFriendAdapter.setNewData(searchValue(s.toString()));
                } else {
                    searchRecyclerView.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.VISIBLE);
                }
            }
        });
        onRefresh();
    }

    private List<FriendInfo> searchValue(String value) {
        List<FriendInfo> list = new ArrayList<>();
        for (int i = 2, j = mDateList.size(); i < j; i++) {
            FriendInfo friendInfo = mDateList.get(i);
            boolean isTrue = friendInfo.getMobile().contains(value) || friendInfo.getName().contains(value);
            if (!TextUtils.isEmpty(friendInfo.getRemarkName()))
                isTrue = isTrue || friendInfo.getRemarkName().contains(value);
            if (isTrue) list.add(friendInfo);
        }
        return list;
    }

    @Override
    public void successFriends(List<FriendInfo> sortModels, boolean refresh, boolean more) {
        if (refresh) {
            mDateList.clear();
            mDateList.addAll(friendInfoList);
        }
        swipeRefreshLayout.setRefreshing(false);
        mDateList.addAll(sortModels);
        // 根据a-z进行排序源数据
        Collections.sort(mDateList, mComparator);
        contactAdapter.setNewData(mDateList);
        mRecyclerView.removeItemDecoration(mDecoration);
        mDecoration.setData(mDateList);
        //如果add两个，那么按照先后顺序，依次渲染。
        mRecyclerView.addItemDecoration(mDecoration);
        if (!more) contactAdapter.loadMoreEnd();
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

//    private boolean isFirst = false;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(MessageEvent message) {
        if (message.getCode() == CONTACT_EVENT_KEY) onRefresh();
    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (!hidden) {
//            onRefresh();
//        }
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onLoadMoreRequested() {
        swipeRefreshLayout.setRefreshing(true);
        presenter.loadMore();
    }

    @OnMenuOnclick
    public void menuClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_friend_operation:
                ARouter.getInstance().build(SearchFriendActivity.AROUTER_PATH).navigation();
                break;
        }
    }


}
