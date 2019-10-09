package com.zh.xfz.business.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.GroupListInfo;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;

/**
 * author : dayezi
 * data :2019/9/25
 * description:
 */
public class GroupAdapter extends MyBaseAdapter<GroupListInfo> {

    @Inject
    public GroupAdapter() {
        super(R.layout.item_new_friend, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, GroupListInfo item) {
        helper.getView(R.id.status_btn).setVisibility(View.GONE);
        helper.getView(R.id.img).setVisibility(View.GONE);
        helper.setText(R.id.tv_name, item.getGroupName());
//        if (TextUtils.isEmpty(item.getUserIcon())) LogUtils.e(item);
//        else {
//            NiceImageView imageView = helper.getView(R.id.img);
//            Glide.with(mContext).load(item.getUserIcon()).into(imageView);
//        }
    }
}
