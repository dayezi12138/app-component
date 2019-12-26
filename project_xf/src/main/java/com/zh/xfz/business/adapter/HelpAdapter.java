package com.zh.xfz.business.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.LocalApplication;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;

/**
 * author : dayezi
 * data :2019/10/30
 * description:
 */
public class HelpAdapter extends MyBaseAdapter<LocalApplication> {

    @Inject
    public HelpAdapter() {
        super(R.layout.item_help, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, LocalApplication item) {
        helper.setText(R.id.text_tv, item.getName());
    }
}
