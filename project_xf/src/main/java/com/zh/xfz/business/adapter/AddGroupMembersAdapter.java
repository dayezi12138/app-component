package com.zh.xfz.business.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.zh.xfz.R;
import com.zh.xfz.bean.fragment.FriendInfo;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;

/**
 * author : dayezi
 * data :2019/9/24
 * description:
 */
public class AddGroupMembersAdapter extends MyBaseAdapter<FriendInfo> {
//    private Map<Integer, Boolean> set = new HashMap<>();

    @Inject
    public AddGroupMembersAdapter() {
        super(R.layout.item_add_group_members, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendInfo item) {
        helper.setText(R.id.name, item.getName());
//        ImageView imageView = helper.getView(R.id.img);
//        if (set.containsKey(helper.getPosition())) {
//            boolean is = set.remove(helper.getPosition());
//            imageView.setBackgroundResource(R.drawable.shape_item_add_group_members_unselect);
//        } else {
//            set.add(helper.getPosition());
//            imageView.setBackgroundResource(R.drawable.shape_item_add_group_members_select);
//        }
    }

//    public void setSelectPosition(int position) {
//        if (set.containsKey(position))
//            set.put(position, !set.get(position));
//        else set.put(position, true);
//        notifyDataSetChanged();
//    }
}
