package com.zh.xfz.business.adapter;

import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zh.xfz.R;
import com.zh.xfz.bean.fragment.FriendInfo;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;

/**
 * author : dayezi
 * data :2019/9/3
 * description:EasyRecyclerViewAdapter
 * implements EasyRecyclerSectionIndexer<EasySection>
 */
public class ContactAdapter extends MyBaseAdapter<FriendInfo> {
    private boolean aBoolean;
    private AddFriendListner addFriendListner;

    @Inject
    public ContactAdapter() {
        super(R.layout.item_contact, new ArrayList<>());
    }

    public ContactAdapter(@LayoutRes int itemLayoutId) {
        super(itemLayoutId, new ArrayList<>());
        aBoolean = true;
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendInfo item) {
        helper.setText(R.id.tv_name, item.getName());
        if (aBoolean) {
            Button button = helper.getView(R.id.status_btn);
            if (item.getStatus() == 0) {
                button.setBackgroundResource(R.drawable.shape_select_create_busi);
                button.setText(mContext.getResources().getString(R.string.new_friend_str));
                button.setTextColor(mContext.getResources().getColor(R.color.white));
                if (addFriendListner != null)
                    button.setOnClickListener(v -> addFriendListner.addFriend(helper.getLayoutPosition()));
            } else {
                button.setBackgroundResource(R.drawable.shape_new_friend_1);
                button.setText(mContext.getResources().getString(R.string.has_new_friend_str));
                button.setTextColor(mContext.getResources().getColor(R.color.item_new_friend_status_1_color));
            }
        }
        ImageView imageView = helper.getView(R.id.img);
        if (!TextUtils.isEmpty(item.getUserIcon())) {
            Glide.with(mContext).load(Uri.parse(item.getUserIcon())).into(imageView);
        } else {
            imageView.setBackgroundResource(R.drawable.rc_default_portrait);
        }
//        else {
//            ImageView imageView = helper.getView(R.id.img);
//            if (!TextUtils.isEmpty(item.getUserIcon())) {
//                Glide.with(mContext).load(Uri.parse(item.getUserIcon())).into(imageView);
//            } else {
//                imageView.setBackgroundResource(R.drawable.rc_default_portrait);
//            }
//        }
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的char ascii值
     */
    public int getSectionForPosition(int position) {
        return mData.get(position).getLetters().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        if (mData.size() < getItemCount()) return -1;
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = mData.get(i).getLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    public void setAddFriendListner(AddFriendListner addFriendListner) {
        this.addFriendListner = addFriendListner;
    }

    public interface AddFriendListner {
        void addFriend(int position);
    }
}
