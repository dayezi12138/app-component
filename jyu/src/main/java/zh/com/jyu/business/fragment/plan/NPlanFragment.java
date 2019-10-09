package zh.com.jyu.business.fragment.plan;

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
import zh.com.jyu.bean.fragment.PlanBean;
import zh.com.jyu.business.activity.GoodsListActivity;
import zh.com.jyu.business.activity.PickingActivity;
import zh.com.jyu.business.adapter.PlanAdapter;
import zh.com.jyu.constans.CommonConstants;
import zh.com.jyu.listener.SearchListener;
import zh.com.jyu.mvp.contract.fragment.NPlanContract;
import zh.com.jyu.mvp.presenter.fragment.plan.NPlanPresenter;

/**
 * author : dayezi
 * data :2019/6/27
 * description:
 */
public class NPlanFragment extends BaseFragment implements NPlanContract.NPlanUI, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private int status = 0;
    private static final String STATUS_KEY = "status";

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Inject
    NPlanPresenter presenter;

    @Inject
    PlanAdapter planAdapter;
    protected List<PlanBean> dataList = new ArrayList<>();

    public static NPlanFragment newInstance(int status) {
        Bundle bundle = new Bundle();
        bundle.putInt(STATUS_KEY, status);
        NPlanFragment fragment = new NPlanFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @SuppressLint("ValidFragment")
    private NPlanFragment() {
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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && swipeRefreshLayout != null) onRefresh();
    }

    @Override
    public void init() {
        status = getArguments().getInt(STATUS_KEY);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));  //线性布局
        recyclerView.setAdapter(planAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.item_line));
        recyclerView.addItemDecoration(dividerItemDecoration);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.deep_blue, R.color.d, R.color.colorPrimary);
        planAdapter.setOnLoadMoreListener(this, recyclerView);
        planAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        planAdapter.setEmptyView(R.layout.empty_view);
        planAdapter.setOnItemClickListener((adapter, view, position) -> ARouter.getInstance()
                .build(GoodsListActivity.AROUTER_PATH)
                .withInt(PickingActivity.PARAM_ID, dataList.get(position).getID())
                .navigation());
        if (getUserVisibleHint()) onRefresh();
    }

    @Override
    public void success(List<PlanBean> list, boolean refresh) {
        if (refresh) dataList.clear();
        planAdapter.loadMoreComplete();
        dataList.addAll(list);
        planAdapter.setNewData(dataList);
        if (list.size() < CommonConstants.PAGE_SIZE)
            planAdapter.loadMoreEnd();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void emptyData(boolean refresh) {
        if (refresh) dataList.clear();
        planAdapter.setNewData(dataList);
        planAdapter.loadMoreEnd(true);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        presenter.onRefresh(param());
    }

    private Map<String, Object> param() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        String val = "";
        if (getActivity() instanceof SearchListener) {
            SearchListener listener = (SearchListener) getActivity();
            val = listener.inputValue();
        }
        if (getParentFragment() instanceof SearchListener) {
            SearchListener listener = (SearchListener) getParentFragment();
            val = listener.inputValue();
        }
        map.put("keywords", val);
        return map;
    }

    @Override
    public void onLoadMoreRequested() {
        presenter.onloadMored(param());
    }

}
