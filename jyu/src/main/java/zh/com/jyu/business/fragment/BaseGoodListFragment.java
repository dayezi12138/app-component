package zh.com.jyu.business.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.R;
import zh.com.jyu.business.activity.PickingActivity;
import zh.com.jyu.mvp.presenter.fragment.plan.GoodListPresenter;
import zh.com.jyu.utils.ItemDecorationHelper;

/**
 * author : dayezi
 * data :2019/6/13
 * description:
 */
public class BaseGoodListFragment extends BaseFragment {
    public static final String GOOD_LIST_STATUS_KEY = "status";

    private MyBaseAdapter adapter;

    @Inject
    GoodListPresenter presenter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private int status, planId;

    public static BaseGoodListFragment newInstance(int status, int planId) {
        BaseGoodListFragment fragment = new BaseGoodListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(GOOD_LIST_STATUS_KEY, status);
        bundle.putInt(PickingActivity.PARAM_ID, planId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @SuppressLint("ValidFragment")
    private BaseGoodListFragment() {

    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.fragment_good_list;
    }

    @Override
    public void init() {
        status = getArguments().getInt(GOOD_LIST_STATUS_KEY);
        planId = getArguments().getInt(PickingActivity.PARAM_ID);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));  //线性布局
        recyclerView.setAdapter(adapter);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        recyclerView.addItemDecoration(ItemDecorationHelper.getDividerItemDecoration(getContext()));
        presenter.requestDate(status, planId);
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }
}
