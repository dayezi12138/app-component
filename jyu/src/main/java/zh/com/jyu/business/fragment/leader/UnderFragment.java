package zh.com.jyu.business.fragment.leader;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gavin.com.library.StickyDecoration;
import com.gavin.com.library.listener.GroupListener;
import com.gavin.com.library.listener.OnGroupClickListener;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.BasePresenter;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.CraftBean;
import zh.com.jyu.business.activity.CraftDetailActivity;
import zh.com.jyu.business.adapter.LeaderAdapter;
import zh.com.jyu.constans.CommonConstants;
import zh.com.jyu.constans.SpKeyConstant;
import zh.com.jyu.listener.SearchListener;
import zh.com.jyu.mvp.contract.fragment.UnderContract;
import zh.com.jyu.mvp.presenter.fragment.leader.UnderPresenter;

/**
 * author : dayezi
 * data :2019/6/27
 * description:
 */
public class UnderFragment extends BaseFragment implements UnderContract.UnderUI, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private int status = 0;
    private static final String STATUS_KEY = "status";


    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @Inject
    UnderPresenter presenter;

    @Inject
    LeaderAdapter adapter;

    StickyDecoration decoration;

    private List<CraftBean> dataList = new ArrayList<>();

    public static UnderFragment newInstance(int status) {
        Bundle bundle = new Bundle();
        bundle.putInt(STATUS_KEY, status);
        UnderFragment fragment = new UnderFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @SuppressLint("ValidFragment")
    private UnderFragment() {
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.fragment_under;
    }

    @Override
    public void init() {
        status = getArguments().getInt(STATUS_KEY);
        GroupListener groupListener = position -> {
            //获取分组名
            if (adapter.getData().size() > position)
                return MessageFormat.format(getResources().getString(R.string.fragment_leader_order_num_str), adapter.getData().get(position).getProduceGoodsReceiptNO());
            else return null;
        };
        decoration = StickyDecoration.Builder
                .init(groupListener)
                .setGroupBackground(Color.parseColor("#F7F7F7"))
                .setGroupTextColor(getResources().getColor(R.color.text2))
                .setGroupTextColor(getResources().getColor(R.color.text1))
                .setGroupHeight(110)
                .setGroupTextSize(47)
                .setTextSideMargin((int) getResources().getDimension(R.dimen.widget_margin_tiny))
                .build();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));  //线性布局
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.item_line));
        recyclerView.addItemDecoration(dividerItemDecoration);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.deep_blue, R.color.d, R.color.colorPrimary);
        adapter.setOnLoadMoreListener(this, recyclerView);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        adapter.setEmptyView(R.layout.empty_view);
        adapter.setOnItemClickListener((adapter, view, position) -> ARouter.getInstance().build(CraftDetailActivity.AROUTER_PATH)
                .withInt(CraftDetailActivity.CRAFT_ID_KEY, UnderFragment.this.adapter.getData().get(position).getCraftsReceiptID())
                .navigation());
        if (getUserVisibleHint()) onRefresh();
    }

    @Override
    public void success(List<CraftBean> list, boolean refresh) {
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

    @Override
    public void onRefresh() {
        if (swipeRefreshLayout == null) return;
        swipeRefreshLayout.setRefreshing(true);
        presenter.onRefresh(param());
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
        map.put("userID", SPUtils.getInstance().getInt(SpKeyConstant.LOGIN_UID_KEY));
        return map;
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

}
