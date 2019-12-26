package com.zh.xfz.business.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.zh.xfz.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.IBaseListView;
import core.app.zh.com.core.base.MyBaseAdapter;

/**
 * author : dayezi
 * data :2019/12/23
 * description:
 */
public abstract class SmartBaseListFragment<T> extends BaseFragment implements IBaseListView<T>, OnRefreshLoadMoreListener {

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private List<T> data = new ArrayList<>();
    private MyBaseAdapter myBaseAdapter;

    @BindView(R.id.empty)
    LinearLayout emptyLy;


    @Override
    public void listData(List<T> result, boolean isRefresh) {
        emptyLy.setVisibility(View.GONE);
        if ((result == null || result.isEmpty()) && !isRefresh) {
            refreshLayout.finishLoadMore(true);
            return;
        }
        if ((result == null || result.isEmpty()) && isRefresh) {
            refreshLayout.finishLoadMoreWithNoMoreData();
            emptyLy.setVisibility(View.VISIBLE);
            return;
        }

        if (isRefresh) {
            refreshLayout.finishRefresh(true);
            data.clear();
        }
        myBaseAdapter.loadMoreComplete();
        data.addAll(result);
        myBaseAdapter.setNewData(data);
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.smart_refresh_view;
    }

    @Override
    public void init() {
        if (getMyBaseAdapter() == null)
            throw new RuntimeException("...适配器未添加...");
        myBaseAdapter = getMyBaseAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(myBaseAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()).setSpinnerStyle(SpinnerStyle.FixedBehind).setPrimaryColorId(R.color.background_color).setAccentColorId(R.color.light_blue_color));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.FixedBehind).setPrimaryColorId(R.color.background_color).setAccentColorId(R.color.light_blue_color));
        refreshLayout.setOnRefreshLoadMoreListener(this);
        if (!canRefresh()) {
            refreshLayout.setEnableRefresh(false);
            refreshLayout.setEnableLoadMore(false);
        } else if (needInitRefresh()) refreshLayout.autoRefresh();
        initRefresh();
    }

    @Override
    public void showMsg(String msg) {
        super.showMsg(msg);
        refreshLayout.finishLoadMoreWithNoMoreData();
    }

    public abstract void initRefresh();

    public boolean canRefresh() {
        return true;
    }

    public boolean needInitRefresh() {
        return true;
    }

    public abstract MyBaseAdapter getMyBaseAdapter();
}
