package com.zh.xfz.business.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.SearchFriend;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;

/**
 * author : dayezi
 * data :2019/9/23
 * description:
 */
public class SearchFriendAdapter extends MyBaseAdapter<SearchFriend> {

    @Inject
    public SearchFriendAdapter() {
        super(R.layout.item_search_friend, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchFriend item) {
        helper.setText(R.id.name, item.getName());
    }
}
