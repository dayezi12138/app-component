package zh.com.jyu.business.fragment.leader;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gavin.com.library.StickyDecoration;
import com.gavin.com.library.listener.GroupListener;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.BasePresenter;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.CraftBean;
import zh.com.jyu.business.activity.CraftDetailActivity;
import zh.com.jyu.business.activity.SearchResultActivity;
import zh.com.jyu.business.adapter.LeaderAdapter;
import zh.com.jyu.constans.CommonConstants;
import zh.com.jyu.constans.SpKeyConstant;
import zh.com.jyu.listener.IntentSearchLister;
import zh.com.jyu.mvp.contract.fragment.LeaderContract;
import zh.com.jyu.mvp.presenter.fragment.leader.LeaderPresenter;
import zh.com.jyu.views.ClearEditTextView;
import zh.com.jyu.views.MyRecyclerView;

/**
 * author : dayezi
 * data :2019/6/18
 * description:
 */
public class LeaderFragment extends BaseFragment implements LeaderContract.LeaderUI, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, IntentSearchLister {


    private static final String STATUS_KEY = "status";
    @BindView(R.id.recycler)
    MyRecyclerView recyclerView;

    @BindView(R.id.et_search)
    ClearEditTextView clearEditTextView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    LeaderAdapter adapter;

    @BindString(R.string.fragment_leader_search_hint_str)
    String hintStr;

    private List<CraftBean> dataList = new ArrayList<>();

    @Inject
    LeaderPresenter presenter;

    private int status;
    StickyDecoration decoration;

    public static LeaderFragment newInstance(int status) {
        Bundle bundle = new Bundle();
        bundle.putInt(STATUS_KEY, status);
        LeaderFragment fragment = new LeaderFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @SuppressLint("ValidFragment")
    private LeaderFragment() {

    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.fragment_leader_1;
    }

    @Override
    public void init() {
        clearEditTextView.setHint(hintStr);
        status = getArguments().getInt(STATUS_KEY);
        GroupListener groupListener = position -> {
            //获取分组名
            if (adapter.getData().size() > position)
                return MessageFormat.format(getResources().getString(R.string.fragment_leader_order_num_str), adapter.getData().get(position).getProduceGoodsReceiptNO());
            else return null;
        };
        decoration = StickyDecoration.Builder
                .init(groupListener)
                .setGroupBackground(getResources().getColor(R.color.divider))
                .setGroupTextColor(getResources().getColor(R.color.text2))
                .setGroupTextColor(getResources().getColor(R.color.text1))
                .setGroupTextSize((int) clearEditTextView.getTextSize())
                .setTextSideMargin((int) getResources().getDimension(R.dimen.widget_margin_tiny))
                .build();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));  //线性布局
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.deep_blue, R.color.d, R.color.colorPrimary);
        adapter.setOnLoadMoreListener(this, recyclerView);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        adapter.setEmptyView(R.layout.view_list_empty);
        adapter.setOnItemClickListener((adapter, view, position) ->
                ARouter.getInstance().build(CraftDetailActivity.AROUTER_PATH)
                        .withInt(CraftDetailActivity.CRAFT_ID_KEY, LeaderFragment.this.adapter.getData().get(position).getCraftsReceiptID())
                        .navigation());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
            presenter.onRefresh(params());
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            presenter.onRefresh(params());
        }
    }

    public Map<String, Object> params() {
        Map<String, Object> params = new HashMap<>();
        params.put("status", status);
        params.put("keywords", TextUtils.isEmpty(clearEditTextView.getText().toString()) ? "" : clearEditTextView.getText().toString());
        params.put("userID", SPUtils.getInstance().getInt(SpKeyConstant.LOGIN_UID_KEY));
        return params;
    }

    @Override
    public void onRefresh() {
        presenter.onRefresh(params());
    }

    @Override
    public void onLoadMoreRequested() {
        presenter.onLoadMore(params());
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
    public void emptyData() {
        dataList.clear();
        adapter.notifyDataSetChanged();
        adapter.loadMoreEnd(true);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMsg(String msg) {
        super.showMsg(msg);
        adapter.loadMoreFail();
        swipeRefreshLayout.setRefreshing(false);
    }

    private String val = "";

    @OnClick(R.id.et_search)
    public void search() {
        ARouter.getInstance().build(SearchResultActivity.AROUTER_PATH).withString(SearchResultActivity.HINT_KEY, hintStr).withString(SearchResultActivity.SEARCH_VALUE, clearEditTextView.getText().toString()).navigation();
    }


    @Override
    public void sendSearchValue(String value) {
        clearEditTextView.setText(value);
        val = value;
        onRefresh();
    }
}
