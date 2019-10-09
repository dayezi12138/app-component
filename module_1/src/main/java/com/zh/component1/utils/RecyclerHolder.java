package com.zh.component1.utils;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.MyBaseAdapter;

/**
 * author : dayezi
 * data :2019/7/8
 * description:
 */
public class RecyclerHolder {
    private MyBaseAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private BaseActivity activity;

    public static void inject(MyBaseAdapter adapter, BaseActivity activity) {

    }
}
