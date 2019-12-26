package com.zh.xfz.business.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zh.xfz.R;
import com.zh.xfz.bean.fragment.TenantMember;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;

/**
 * author : dayezi
 * data :2019/12/24
 * description:
 */
public class CompanyMemberListAdapter extends MyBaseAdapter<TenantMember> {

    @Inject
    public CompanyMemberListAdapter() {
        super(R.layout.item_company_member, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, TenantMember item) {
        helper.setText(R.id.name_tv, item.getChineseName());
        ImageView imageView = helper.getView(R.id.img_iv);
        Glide.with(mContext).load(item.getUserIcon()).error(R.drawable.ic_user_white)
                .diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
    }
}
