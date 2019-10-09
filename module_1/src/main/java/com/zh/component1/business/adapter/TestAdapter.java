package com.zh.component1.business.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.zh.component1.R;

import java.util.ArrayList;

import core.app.zh.com.core.base.MyBaseAdapter;

/**
 * author : dayezi
 * data :2019/7/8
 * description:
 */
public class TestAdapter extends MyBaseAdapter<String> {


    public TestAdapter() {
        super(R.layout.item_test, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {


    }
}
