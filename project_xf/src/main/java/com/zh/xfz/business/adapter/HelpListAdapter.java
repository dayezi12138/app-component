package com.zh.xfz.business.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.zh.xfz.R;
import com.zh.xfz.bean.fragment.HelpArticle;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;

/**
 * author : dayezi
 * data :2019/12/26
 * description:
 */
public class HelpListAdapter extends MyBaseAdapter<HelpArticle> {
    @Inject
    public HelpListAdapter() {
        super(R.layout.item_help, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, HelpArticle item) {
        helper.setText(R.id.text_tv, item.getTitle());
    }
}
