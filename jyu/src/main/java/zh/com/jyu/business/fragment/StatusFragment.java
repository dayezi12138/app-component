package zh.com.jyu.business.fragment;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.R;
import zh.com.jyu.business.activity.SearchResultActivity;
import zh.com.jyu.constans.CommonConstants;
import zh.com.jyu.listener.IntentSearchLister;
import zh.com.jyu.mvp.contract.fragment.StartStatusContract;
import zh.com.jyu.mvp.presenter.fragment.StartStatusPresenter;
import zh.com.jyu.views.ClearEditTextView;

/**
 * author : dayezi
 * data :2019/6/12
 * description:
 */
public abstract class StatusFragment<T> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, StartStatusContract.StartStatusUI<T>, IntentSearchLister {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.et_search)
    protected ClearEditTextView clearEditTextView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindString(R.string.fragment_plan_search_hint_str)
    String hintStr;

    private MyBaseAdapter adapter;
    private StartStatusPresenter presenter;
    protected List<T> dataList = new ArrayList<>();


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && swipeRefreshLayout != null) onRefresh();
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.fragment_start_state;
    }

    @Override
    public void init() {
        if (getPresenter() == null || !(getPresenter() instanceof StartStatusPresenter) || adapter() == null)
            return;
        clearEditTextView.setHint(hintStr);
        adapter = adapter();
        presenter = (StartStatusPresenter) getPresenter();
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
        adapter.setOnItemClickListener((adapter, view, position) -> StatusFragment.this.onItemClick(position));
        if (getUserVisibleHint()) onRefresh();
    }


    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        presenter.onRefresh(params());
    }

    @Override
    public void onLoadMoreRequested() {
        presenter.onloadMored(params());
    }

    @Override
    public void success(List<T> list, boolean refresh) {
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
        if (TextUtils.isEmpty(val))
            ARouter.getInstance().build(SearchResultActivity.AROUTER_PATH).withString(SearchResultActivity.SEARCH_VALUE, clearEditTextView.getText().toString())
                    .withString(SearchResultActivity.HINT_KEY, hintStr).navigation();
        else {
            val = "";
            clearEditTextView.setText("");
        }
    }

    @Override
    public void sendSearchValue(String value) {
        clearEditTextView.setText(value);
        val = value;
        swipeRefreshLayout.setRefreshing(true);
        presenter.onRefresh(params());
    }

    public void start() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
            presenter.onRefresh(params());
        }
    }

    public abstract MyBaseAdapter adapter();

    public abstract Map<String, Object> params();

    public abstract void onItemClick(int position);
}
