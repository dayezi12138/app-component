package com.zh.xfz.business.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.zh.xfz.R;
import com.zh.xfz.bean.fragment.BusinessBean;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;

/**
 * author : dayezi
 * data :2019/12/9
 * description:
 */
public class BusinessListAdapter extends MyBaseAdapter<BusinessBean> {


    @Inject
    public BusinessListAdapter() {
        super(R.layout.item_business, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, BusinessBean item) {
        helper.setText(R.id.text, item.getTenantName());
    }
}
