package com.zh.xfz.business.fragment;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
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
import com.zh.xfz.constans.Constants;
import com.zh.xfz.listener.MyTextWatcher;
import com.zh.xfz.mvp.contract.ConversationContract;
import com.zh.xfz.mvp.presenter.ConversationPresenter;
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
import core.app.zh.com.core.view.MyPopupWindow;

/**
 * author : dayezi
 * data :2019/9/3
 * description:menu_fragment_contact
 */
@ToolbarLeft(menuId = R.menu.menu_fragment_contact)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "通讯录")
public class ContactFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, ConversationContract.ContactListUI, MyTextWatcher {
    public static int CONTACT_EVENT_KEY = 360001;

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    @BindView(R.id.sideBar)
    WaveSideBar mSideBar;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.et_search)
    ClearEditTextView clearEditTextView;

    @BindView(R.id.rl)
    RelativeLayout relativeLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    private List<FriendInfo> mDateList = new ArrayList<>();
    private TitleItemDecoration mDecoration;

    @Inject
    LinearLayoutManager manager;
    @Inject
    ContactAdapter contactAdapter;
    @Inject
    ConversationPresenter conversationPresenter;
    @Inject
    Comparator mComparator;
    @Inject
    List<FriendInfo> friendInfoList;
    @Inject
    MyPopupWindow popupWindow;
    @Inject
    ContactSearchAdapter searchAdapter;


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
        contactAdapter.setEmptyView(R.layout.empty_view);
        contactAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (!TextUtils.isEmpty(mDateList.get(position).getPath()))
                ARouter.getInstance().build(mDateList.get(position).getPath()).navigation();
            else
                ARouter.getInstance().build(FriendDetailActivity.AROUTER_PATH).withSerializable(FriendDetailActivity.FRIEND_DETAIL_KEY, mDateList.get(position)).navigation();
        });
        swipeRefreshLayout.setOnRefreshListener(this);
        EventBus.getDefault().register(this);
        clearEditTextView.addTextChangedListener(this);
        clearEditTextView.setmClearDrawableRecourse(R.drawable.ic_clear_gray);
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
    public void showMsg(String msg) {
        swipeRefreshLayout.setRefreshing(false);
        if (contactAdapter.getData().isEmpty()) {
            mDateList.clear();
            mDateList.addAll(friendInfoList);
            contactAdapter.setNewData(mDateList);
        }
        contactAdapter.loadMoreEnd();
        super.showMsg(msg);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        conversationPresenter.onRefresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(MessageEvent message) {
        if (message.getCode() == CONTACT_EVENT_KEY) onRefresh();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onLoadMoreRequested() {
        swipeRefreshLayout.setRefreshing(true);
        conversationPresenter.onLoadMore();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnMenuOnclick
    public void menuClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_friend_operation:
                ARouter.getInstance().build(SearchFriendActivity.AROUTER_PATH).navigation();
                break;
            case R.id.menu_search_friend:
                if (popupWindow.isShowing()) return;
                popupWindow.setBackgroundAlpha();
                popupWindow.showAtLocation(toolbar, Gravity.LEFT | Gravity.TOP, 0, 0);
                break;
        }
    }

    @Override
    public void listData(List<FriendInfo> data, boolean isRefresh) {
        contactAdapter.loadMoreComplete();
        if (isRefresh) {
            mDateList.clear();
            mDateList.addAll(friendInfoList);
        }
        swipeRefreshLayout.setRefreshing(false);
        mDateList.addAll(data);
        // 根据a-z进行排序源数据
        Collections.sort(mDateList, mComparator);
        contactAdapter.setNewData(mDateList);
        mRecyclerView.removeItemDecoration(mDecoration);
        mDecoration.setData(mDateList);
        //如果add两个，那么按照先后顺序，依次渲染。
        mRecyclerView.addItemDecoration(mDecoration);
        if (data.size() < Constants.PAGESIZE) contactAdapter.loadMoreEnd();
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!TextUtils.isEmpty(s.toString())) {
            searchAdapter.setNewData(searchValue(s.toString()));
        }
    }
}
