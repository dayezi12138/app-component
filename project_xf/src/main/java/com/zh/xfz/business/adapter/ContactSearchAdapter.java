package com.zh.xfz.business.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.zh.xfz.R;
import com.zh.xfz.bean.fragment.FriendInfo;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;

/**
 * author : dayezi
 * data :2019/10/16
 * description:
 */
public class ContactSearchAdapter extends MyBaseAdapter<FriendInfo> {

    @Inject
    public ContactSearchAdapter() {
        super(R.layout.item_search_friend, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendInfo item) {
        helper.setText(R.id.name, item.getName());
    }
}

