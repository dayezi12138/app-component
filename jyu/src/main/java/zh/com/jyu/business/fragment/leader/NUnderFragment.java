package zh.com.jyu.business.fragment.leader;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import core.app.zh.com.core.base.BaseFragment;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.CraftBean;
import zh.com.jyu.business.adapter.NUnderAdapter;
import zh.com.jyu.constans.SpKeyConstant;
import zh.com.jyu.listener.SearchListener;
import zh.com.jyu.mvp.contract.fragment.UnderContract;
import zh.com.jyu.mvp.presenter.fragment.leader.UnderPresenter;
import zh.com.jyu.views.pine.PinnedHeaderRecyclerView;
import zh.com.jyu.views.pine.entity.ExpandGroupItemEntity;
import zh.com.jyu.views.pine.entity.PinnedHeaderItemDecoration;

/**
 * author : dayezi
 * data :2019/9/4
 * description:
 */
public class NUnderFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener, UnderContract.UnderUI, BGARefreshLayout.BGARefreshLayoutDelegate {
    private int status = 0;
    private static final String STATUS_KEY = "status";
    @Inject
    NUnderAdapter patrolGroupAdapter;

    @Inject
    UnderPresenter presenter;

    @BindView(R.id.recycler_order_list)
    PinnedHeaderRecyclerView recyclerView;

//    @BindView(R.id.swipeRefreshLayout)
//    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rl_modulename_refresh)
    BGARefreshLayout mRefreshLayout;
    private LinearLayoutManager mLayoutManager;
    private List<ExpandGroupItemEntity<String, CraftBean>> dataList = new ArrayList<>();

    public static NUnderFragment newInstance(int status) {
        Bundle bundle = new Bundle();
        bundle.putInt(STATUS_KEY, status);
        NUnderFragment fragment = new NUnderFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private void initRefreshLayout() {
        // 为BGARefreshLayout 设置代理
        mRefreshLayout.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGAMoocStyleRefreshViewHolder refreshViewHolder = new BGAMoocStyleRefreshViewHolder(getContext(), true);
        // 设置下拉刷新和上拉加载更多的风格
        refreshViewHolder.setOriginalImage(R.mipmap.bga_refresh_loading01);
        refreshViewHolder.setUltimateColor(R.color.colorPrimary);
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);
        mRefreshLayout.shouldHandleRecyclerViewLoadingMore(recyclerView);
    }

    @SuppressLint("ValidFragment")
    private NUnderFragment() {
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.fragment_n_under;
    }

    @Override
    public void init() {
        status = getArguments().getInt(STATUS_KEY);
        recyclerView.setLayoutManager(mLayoutManager = new LinearLayoutManager(getContext()));  //线性布局
        recyclerView.addItemDecoration(new PinnedHeaderItemDecoration());
        patrolGroupAdapter.setOnLoadMoreListener(this, recyclerView);
        patrolGroupAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        patrolGroupAdapter.setEmptyView(R.layout.empty_view);
        recyclerView.setAdapter(patrolGroupAdapter);
        initRefreshLayout();
        if (getUserVisibleHint()) onRefresh();
        /**
         * 当标题栏被悬浮的时候的点击功能
         */
        recyclerView.setOnPinnedHeaderClickListener(adapterPosition -> {
            patrolGroupAdapter.switchExpand(adapterPosition);
            //标题栏被点击之后，滑动到指定位置
            mLayoutManager.scrollToPositionWithOffset(adapterPosition, 0);
        });
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
        map.put("pageSize", 100);
        return map;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        hasMore = true;
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && mRefreshLayout != null) {
            onRefresh();
        }
    }

    public void onRefresh() {
        mRefreshLayout.beginRefreshing();
    }


    @Override
    public void success(List<CraftBean> list, boolean refresh) {
        if (refresh) {
            hasMore = true;
            List<ExpandGroupItemEntity<String, CraftBean>> craftBeans = getData(list);
            mRefreshLayout.endRefreshing();
            dataList.clear();
            Collections.sort(craftBeans);
            dataList.addAll(craftBeans);
        } else {
            if (list.size() < 100) hasMore = false;
            mRefreshLayout.endLoadingMore();
            if (!dataList.isEmpty()) {
                Map<Integer, Integer> map = new HashMap();//key dataList 位置，val 请求数据位置
                List<ExpandGroupItemEntity<String, CraftBean>> craftBeans = getData(list);
                for (int i = 0, j = craftBeans.size(); i < j; i++) {
                    for (int h = 0, s = dataList.size(); h < s; h++) {
                        if (craftBeans.get(i).getParent().equals(dataList.get(h).getParent())) {
                            map.put(h, i);
                        }
                    }
                }
                List<Integer> positions = new ArrayList<>();
                for (Integer p : map.keySet()) {
                    dataList.get(p).getChildList().addAll(craftBeans.get(map.get(p)).getChildList());
                    positions.add(map.get(p));
                }

                for (int i = 0, j = craftBeans.size(); i < j; i++) {
                    for (Integer position : positions) {
                        if (position == i) {
                            craftBeans.remove(craftBeans.get(position));
                        }
                    }
                }
                dataList.addAll(craftBeans);
                Collections.sort(dataList);
            }
        }
//        if (dataList.size() != 0) dataList.get(0).setExpand(true);
        patrolGroupAdapter.setData(dataList);
    }

    private List<ExpandGroupItemEntity<String, CraftBean>> getData(List<CraftBean> list) {
        Map<String, List<CraftBean>> map = convertData(list);
        List<ExpandGroupItemEntity<String, CraftBean>> craftBeans = new ArrayList<>();
        for (String key : map.keySet()) {
            ExpandGroupItemEntity entity = new ExpandGroupItemEntity();
            entity.setExpand(false);
            entity.setParent(key);
            entity.setChildList(map.get(key));
            craftBeans.add(entity);
        }
        return craftBeans;
    }

    private Map<String, List<CraftBean>> convertData(List<CraftBean> list) {
        Map<String, List<CraftBean>> map = new HashMap<>();
        for (int i = 0, j = list.size(); i < j; i++) {
            List<CraftBean> craftBeans = new ArrayList<>();
            if (!map.containsKey(list.get(i).getProduceGoodsReceiptNO())) {
                craftBeans.add(list.get(i));
                for (int h = i + 1; h < j; h++) {
                    if (list.get(i).getProduceGoodsReceiptNO().equals(list.get(h).getProduceGoodsReceiptNO())) {
                        craftBeans.add(list.get(h));
                    }
                }
                if (!craftBeans.isEmpty())
                    map.put(list.get(i).getProduceGoodsReceiptNO(), craftBeans);
            }
        }
        return map;
    }

    private boolean hasMore = true;

    @Override
    public void emptyData(boolean refresh) {
        if (refresh) {
            dataList.clear();
            mRefreshLayout.endRefreshing();
        }
        patrolGroupAdapter.setData(dataList);
        hasMore = false;
        mRefreshLayout.endLoadingMore();
    }

    @Override
    public void showMsg(String msg) {
        if (mRefreshLayout.isLoadingMore()) mRefreshLayout.endLoadingMore();
        else
            mRefreshLayout.endRefreshing();
        super.showMsg(msg);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        presenter.onRefresh(param());
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (hasMore) onLoadMoreRequested();
        return hasMore;
    }

    @Override
    public void onLoadMoreRequested() {
        presenter.onloadMored(param());
    }
}
