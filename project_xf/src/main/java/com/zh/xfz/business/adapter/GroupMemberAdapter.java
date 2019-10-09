package com.zh.xfz.business.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseViewHolder;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.GroupInfo;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;

/**
 * author : dayezi
 * data :2019/9/26
 * description:
 */
public class GroupMemberAdapter extends MyBaseAdapter<GroupInfo> {

    @Inject
    public GroupMemberAdapter() {
        super(R.layout.item_group_member_, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, GroupInfo item) {
        helper.setText(R.id.name, TextUtils.isEmpty(item.getRemarkName()) ? item.getChineseName() : item.getRemarkName());
    }
}
