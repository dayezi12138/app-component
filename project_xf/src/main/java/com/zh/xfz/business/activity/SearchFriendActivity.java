package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.SearchFriend;
import com.zh.xfz.business.adapter.SearchFriendAdapter;
import com.zh.xfz.mvp.contract.ConversationContract;
import com.zh.xfz.mvp.presenter.ConversationPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.view.ClearEditTextView;

import static com.zh.xfz.business.activity.AddFriendActivity.ADD_FRIEND_INFO;

/**
 * author : dayezi
 * data :2019/9/23
 * description:
 */
@Route(path = SearchFriendActivity.AROUTER_PATH)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "添加朋友")
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
public class SearchFriendActivity extends BaseActivity implements ConversationContract.SearchUI {
    public final static String AROUTER_PATH = "/main/SearchFriendActivity/";

    @BindView(R.id.scan_img)
    ImageView scanImg;

    @BindView(R.id.et_search)
    ClearEditTextView clearEditTextView;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Inject
    SearchFriendAdapter searchFriendAdapter;
    @Inject
    ConversationPresenter conversationPresenter;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_search_friend;
    }

    @Override
    public void init() {
        clearEditTextView.setOnChangeEditextListener(value -> {
            if (TextUtils.isEmpty(value)) {
                searchFriendAdapter.getData().clear();
                searchFriendAdapter.notifyDataSetChanged();
                scanImg.setVisibility(View.VISIBLE);
            } else conversationPresenter.searchFriend(value);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(searchFriendAdapter);
        searchFriendAdapter.setOnItemClickListener((adapter, view, position) -> ARouter.getInstance().build(AddFriendActivity.AROUTER_PATH).withSerializable(ADD_FRIEND_INFO, searchFriendAdapter.getData().get(position)).navigation());
    }


    @Override
    public void searchResult(List<SearchFriend> searchFriends) {
        scanImg.setVisibility(View.GONE);
        searchFriendAdapter.setNewData(searchFriends);
    }
}
