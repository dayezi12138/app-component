//package core.app.zh.com.core.base;
//
//import android.support.annotation.NonNull;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//
//import butterknife.BindView;
//import core.app.zh.com.core.R;
//
///**
// * author : dayezi
// * data :2019/12/9
// * description:
// */
//public class BaseListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
//
//    @BindView(R.id.recycler)
//    RecyclerView mRecyclerView;
//
//    @BindView(R.id.swipeRefreshLayout)
//    SwipeRefreshLayout swipeRefreshLayout;
//
//    private MyBaseAdapter myBaseAdapter;
//
//    @NonNull
//    @Override
//    public int layoutId() {
//        return R.layout.swiper_recycler;
//    }
//
//    @Override
//    public void init() {
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//    }
//
//    @Override
//    public void onRefresh() {
//
//    }
//
//    @Override
//    public void onLoadMoreRequested() {
//
//    }
//}
