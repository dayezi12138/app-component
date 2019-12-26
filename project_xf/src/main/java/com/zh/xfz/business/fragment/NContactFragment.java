//package com.zh.xfz.business.fragment;
//
//import android.os.Build;
//import android.support.annotation.NonNull;
//import android.support.annotation.RequiresApi;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.Toolbar;
//import android.text.Editable;
//import android.text.TextUtils;
//import android.view.Gravity;
//import android.view.MenuItem;
//
//import com.alibaba.android.arouter.launcher.ARouter;
//import com.allenliu.sidebar.SideBar;
//import com.scwang.smart.refresh.layout.api.RefreshLayout;
//import com.zh.annatation.toolbar.OnMenuOnclick;
//import com.zh.annatation.toolbar.ToolbarLeft;
//import com.zh.annatation.toolbar.ToolbarTitle;
//import com.zh.xfz.R;
//import com.zh.xfz.bean.fragment.FriendInfo;
//import com.zh.xfz.business.activity.FriendDetailActivity;
//import com.zh.xfz.business.activity.SearchFriendActivity;
//import com.zh.xfz.business.adapter.ContactAdapter;
//import com.zh.xfz.business.adapter.ContactSearchAdapter;
//import com.zh.xfz.listener.MyTextWatcher;
//import com.zh.xfz.mvp.contract.ConversationContract;
//import com.zh.xfz.mvp.presenter.ConversationPresenter;
//import com.zh.xfz.views.sideBar.TitleItemDecoration;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.inject.Inject;
//
//import butterknife.BindView;
//import core.app.zh.com.core.base.MyBaseAdapter;
//import core.app.zh.com.core.bean.MessageEvent;
//import core.app.zh.com.core.view.MyPopupWindow;
//
///**
// * author : dayezi
// * data :2019/12/23
// * description:
// */
//@ToolbarLeft(menuId = R.menu.menu_fragment_contact)
//@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "通讯录")
//public class NContactFragment extends SmartBaseListFragment<FriendInfo> implements ConversationContract.ContactListUI, MyTextWatcher {
//    public static int CONTACT_EVENT_KEY = 360001;
//    @Inject
//    ContactAdapter contactAdapter;
//    @Inject
//    ConversationPresenter conversationPresenter;
//    @Inject
//    List<FriendInfo> friendInfoList;
//    @Inject
//    LinearLayoutManager manager;
//    @Inject
//    ContactSearchAdapter searchAdapter;
//    @Inject
//    MyPopupWindow popupWindow;
//
//    @BindView(R.id.sideBar)
//    SideBar sideBar;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    private List<FriendInfo> mDateList = new ArrayList<>();
//    private TitleItemDecoration mDecoration;
//
//    @Override
//    public void initRefresh() {
//        mDateList.addAll(friendInfoList);
//        mRecyclerView.setLayoutManager(manager);
//        mDecoration = new TitleItemDecoration(getContext(), mDateList);
//        contactAdapter.setNewData(mDateList);
//        //如果add两个，那么按照先后顺序，依次渲染。
//        mRecyclerView.addItemDecoration(mDecoration);
//        sideBar.setOnStrSelectCallBack((index, selectStr) -> {
//            //该字母首次出现的位置
//            int position = contactAdapter.getPositionForSection(selectStr.charAt(0));
//            if (position != -1) {
//                manager.scrollToPositionWithOffset(position, 0);
//            }
//        });
//        contactAdapter.setOnItemClickListener((adapter, view, position) -> {
//            if (!TextUtils.isEmpty(mDateList.get(position).getPath()))
//                ARouter.getInstance().build(mDateList.get(position).getPath()).navigation();
//            else
//                ARouter.getInstance().build(FriendDetailActivity.AROUTER_PATH).withSerializable(FriendDetailActivity.FRIEND_DETAIL_KEY, mDateList.get(position)).navigation();
//        });
//        EventBus.getDefault().register(this);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//    }
//
//    @Override
//    public boolean needInitRefresh() {
//        return !super.needInitRefresh();
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onGetMessage(MessageEvent message) {
//        if (message.getCode() == CONTACT_EVENT_KEY) onRefresh(refreshLayout);
//    }
//
//    private boolean first = false;
//
//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (!hidden) {
//            if (!first) {
//                first = true;
//                refreshLayout.autoRefresh();
//                conversationPresenter.onRefresh();
//            }
//        }
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @OnMenuOnclick
//    public void menuClick(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_friend_operation:
//                ARouter.getInstance().build(SearchFriendActivity.AROUTER_PATH).navigation();
//                break;
//            case R.id.menu_search_friend:
//                if (popupWindow.isShowing()) return;
//                popupWindow.setBackgroundAlpha();
//                popupWindow.showAtLocation(toolbar, Gravity.LEFT | Gravity.TOP, 0, 0);
//                break;
//        }
//    }
//
//    @Override
//    public MyBaseAdapter getMyBaseAdapter() {
//        return contactAdapter;
//    }
//
//    @Override
//    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//        conversationPresenter.onLoadMore();
//    }
//
//    @Override
//    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//        conversationPresenter.onRefresh();
//    }
//
//    @NonNull
//    @Override
//    public int layoutId() {
//        return R.layout.fragment_n_contact;
//    }
//
//    @Override
//    public void afterTextChanged(Editable s) {
//        if (!TextUtils.isEmpty(s.toString())) {
//            List list = searchValue(s.toString());
//            if (list.isEmpty()) return;
//            searchAdapter.setNewData(searchValue(s.toString()));
//        }
//    }
//
//    private List<FriendInfo> searchValue(String value) {
//        List<FriendInfo> list = new ArrayList<>();
//        for (int i = 2, j = mDateList.size(); i < j; i++) {
//            FriendInfo friendInfo = mDateList.get(i);
//            boolean isTrue = friendInfo.getMobile().contains(value) || friendInfo.getName().contains(value);
//            if (!TextUtils.isEmpty(friendInfo.getRemarkName()))
//                isTrue = isTrue || friendInfo.getRemarkName().contains(value);
//            if (isTrue) list.add(friendInfo);
//        }
//        return list;
//    }
//}
