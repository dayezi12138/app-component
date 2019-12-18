package com.zh.xfz.business.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.zh.xfz.R;
import com.zh.xfz.db.bean.Tenant;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;

/**
 * author : dayezi
 * data :2019/10/30
 * description:
 */
public class MyCompanyAdapter extends MyBaseAdapter<Tenant> {
    private boolean visibility = true;

    @Inject
    public MyCompanyAdapter() {
        super(R.layout.item_my_company, new ArrayList<>());
    }

    public MyCompanyAdapter(boolean visibility) {
        super(R.layout.item_my_company, new ArrayList<>());
        this.visibility = visibility;
    }

    @Override
    protected void convert(BaseViewHolder helper, Tenant item) {
        helper.setText(R.id.name_tv, item.getTenantName());
        if (visibility)
            helper.getView(R.id.vip_iv).setVisibility(View.VISIBLE);
        else helper.getView(R.id.vip_iv).setVisibility(View.GONE);
    }
}
