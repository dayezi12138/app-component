//package core.app.zh.com.core.base;
//
//import android.support.annotation.NonNull;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import core.app.zh.com.core.R;
//import core.app.zh.com.core.listener.SwipeRefreshListOptionListener;
//
///**
// * author : dayezi
// * data :2019/6/26
// * description:
// */
//@Deprecated
//public abstract class BaseListActivity<T> extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, BaseListView<T> {
//
//    private SwipeRefreshLayout swipeRefreshLayout;
//    private RecyclerView recyclerView;
//    private BaseQuickAdapter adapter;
//    protected List<T> dataList = new ArrayList<>();
//
//    @NonNull
//    @Override
//    public int layoutId() {
//        if (optionListener == null) return R.layout.recycler_;
//        return R.layout.swiper_recycler;
//    }
//
//    @Override
//    public void init() {
//        recyclerView = findViewById(R.id.recycler);
//        if (optionListener != null) {
//            swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
//            swipeRefreshLayout.setColorSchemeResources(optionListener.colorSchemeResources());
//            swipeRefreshLayout.setOnRefreshListener(this);
//            if (adapter != null) {
//                adapter.setOnLoadMoreListener(this, recyclerView);
//                this.adapter.setEmptyView(optionListener.emptyLayout());
//            }
//        }
//        recyclerView.setLayoutManager(getLayoutManager());
//        if (adapter != null) recyclerView.setAdapter(adapter);
//        if (!onResumeLoadData()) onRefresh();
//    }
//
//    @Override
//    public void onRefresh() {
//        if (optionListener != null) {
//            swipeRefreshLayout.setRefreshing(true);
//            optionListener.loadData(true);
//        } else loadData();
//    }
//
//    @Override
//    public void onLoadMoreRequested() {
//        if (optionListener != null) {
//            swipeRefreshLayout.setRefreshing(true);
//            optionListener.loadData(false);
//        }
//    }
//
//    private SwipeRefreshListOptionListener optionListener;
//
//    public void setSwipeRefreshListOptionListener(SwipeRefreshListOptionListener optionListener) {
//        this.optionListener = optionListener;
//    }
//
//    public RecyclerView.LayoutManager getLayoutManager() {
//        return new LinearLayoutManager(this);
//    }
//
//    public void setAdapter(BaseQuickAdapter adapter) {
//        this.adapter = adapter;
//    }
//
//
//    /**
//     * 无下拉刷新的recycler请求调用该方法
//     */
//    public void loadData() {
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (onResumeLoadData()) onRefresh();
//    }
//
//    /**
//     * 是否onResume刷新
//     * @return
//     */
//    public boolean onResumeLoadData() {
//        return false;
//    }
//
//    /**
//     * 分页请求总数
//     * @return
//     */
//    public int pageSize() {
//        return 10;
//    }
//
//    @Override
//    public void success(List<T> data, boolean isRefresh) {
//        if (optionListener != null && adapter != null) {
//            if (isRefresh) dataList.clear();
//            adapter.loadMoreComplete();
//            dataList.addAll(data);
//            adapter.setNewData(dataList);
//            if (data.size() < pageSize())
//                adapter.loadMoreEnd();
//            swipeRefreshLayout.setRefreshing(false);
//            return;
//        }
//        if (adapter != null) adapter.setNewData(data);
//    }
//
//    @Override
//    public void emptyData() {
//        if (optionListener == null || adapter == null) return;
//        dataList.clear();
//        adapter.loadMoreEnd(true);
//        swipeRefreshLayout.setRefreshing(false);
//    }
//
//    @Override
//    public void showMsg(String msg) {
//        super.showMsg(msg);
//        if (optionListener != null) {
//            adapter.loadMoreFail();
//            swipeRefreshLayout.setRefreshing(false);
//        }
//    }
//}
