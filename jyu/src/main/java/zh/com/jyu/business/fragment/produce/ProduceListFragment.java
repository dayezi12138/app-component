package zh.com.jyu.business.fragment.produce;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.BasePresenter;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.GoodListBean;
import zh.com.jyu.business.activity.GoodsDetailActivity;
import zh.com.jyu.business.adapter.GoodListAdapter;
import zh.com.jyu.constans.CommonConstants;
import zh.com.jyu.listener.SearchListener;
import zh.com.jyu.mvp.contract.fragment.ProduceListContract;
import zh.com.jyu.mvp.presenter.fragment.produce.ProduceListPresenter;

/**
 * author : dayezi
 * data :2019/6/27
 * description:
 */
public class ProduceListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, ProduceListContract.ProduceListUI {

    @Inject
    GoodListAdapter adapter;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private int status = 0;
    private static final String STATUS_KEY = "status";

    private List<GoodListBean> dataList = new ArrayList<>();

    @Inject
    ProduceListPresenter presenter;

    public static ProduceListFragment newInstance(int status) {
        Bundle bundle = new Bundle();
        bundle.putInt(STATUS_KEY, status);
        ProduceListFragment fragment = new ProduceListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @SuppressLint("ValidFragment")
    private ProduceListFragment() {
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.swiper_recycler;
    }

    @Override
    public void init() {
        status = getArguments().getInt(STATUS_KEY);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));  //线性布局
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.item_line));
        recyclerView.addItemDecoration(dividerItemDecoration);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.deep_blue, R.color.d, R.color.colorPrimary);
        adapter.setOnLoadMoreListener(this, recyclerView);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        adapter.setEmptyView(R.layout.view_list_empty);
        adapter.setOnItemClickListener((adapter, view, position) -> ARouter.getInstance().build(GoodsDetailActivity.AROUTER_PATH)
                .withInt(GoodsDetailActivity.PARAM_ID, ProduceListFragment.this.adapter.getData().get(position).getID())
                .withInt(GoodsDetailActivity.REFRSH_CODE_KEY, ProduceFragment.REFRESH_CODE)
                .navigation());
        if (getUserVisibleHint()) onRefresh();
    }


    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        presenter.onRefresh(param());
    }

    @Override
    public void onLoadMoreRequested() {
        presenter.onloadMored(param());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && swipeRefreshLayout != null) onRefresh();
    }

    @Override
    public void success(List<GoodListBean> list, boolean refresh) {
        if (refresh) dataList.clear();
        adapter.loadMoreComplete();
        dataList.addAll(list);
        adapter.setNewData(dataList);
        if (list.size() < CommonConstants.PAGE_SIZE)
            adapter.loadMoreEnd();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void emptyData(boolean refresh) {
        if (refresh) dataList.clear();
        adapter.setNewData(dataList);
        adapter.loadMoreEnd(true);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMsg(String msg) {
        swipeRefreshLayout.setRefreshing(false);
        super.showMsg(msg);
    }

    private Map<String, Object> param() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        String val = "";
        if (this.getParentFragment() instanceof SearchListener) {
            SearchListener listener = (SearchListener) this.getParentFragment();
            val = listener.inputValue();
        }
        map.put("keywords", val);
        return map;
    }
}
