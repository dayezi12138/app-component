package com.zh.xfz.business.fragment;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import core.app.zh.com.core.R;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.IBaseListView;
import core.app.zh.com.core.base.MyBaseAdapter;

import static com.zh.xfz.constans.Constans.PAGESIZE;

/**
 * author : dayezi
 * data :2019/12/9
 * description:
 */
public abstract class BaseListFragment<T> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, IBaseListView<T> {

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private List<T> data = new ArrayList<>();
    private MyBaseAdapter myBaseAdapter;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.swiper_recycler;
    }

    @Override
    public void init() {
        if (getMyBaseAdapter() == null) throw new RuntimeException("...适配器未添加...");
        myBaseAdapter = getMyBaseAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(myBaseAdapter);
        swipeRefreshLayout.setOnRefreshListener(this::onRefresh);
        getMyBaseAdapter().setOnLoadMoreListener(this::onLoadMoreRequested, mRecyclerView);
//        swipeRefreshLayout.setEnabled(false);
        refreshBeforeInit();
        onRefresh();
        refreshBeforeAfter();
    }

    public abstract MyBaseAdapter getMyBaseAdapter();

    @Override
    public void listData(List<T> result, boolean isRefresh) {
        if (isRefresh) data.clear();
        myBaseAdapter.loadMoreComplete();
        data.addAll(result);
        myBaseAdapter.setNewData(data);
        if (result.size() < PAGESIZE) myBaseAdapter.loadMoreEnd();
        swipeRefreshLayout.setRefreshing(false);
    }

    public abstract void refreshBeforeInit();

    public abstract void refreshBeforeAfter();


}
