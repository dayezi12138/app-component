package zh.com.jyu.business.fragment.board;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hgdendi.expandablerecycleradapter.BaseExpandableRecyclerViewAdapter;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.api.ToolBarInject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MessageEvent;
import zh.com.jyu.R;
import zh.com.jyu.bean.fragment.BulletinBoard;
import zh.com.jyu.business.activity.GoodsDetailActivity;
import zh.com.jyu.business.activity.SearchResultActivity;
import zh.com.jyu.business.adapter.BulletinBoardAdapter;
import zh.com.jyu.listener.IntentSearchLister;
import zh.com.jyu.mvp.contract.fragment.BulletinBoardContract;
import zh.com.jyu.mvp.presenter.fragment.board.BulletinBoardPresenter;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
@ToolbarTitle(title = "看板")
public class BulletinBoardFragment extends BaseFragment implements BulletinBoardContract.BulletinBoardUI, IntentSearchLister, SwipeRefreshLayout.OnRefreshListener {
    public static final int SEARCH_EVENT_CODE = 100005;
    public static final int REFRSH_CODE = 10009;
    @Inject
    BulletinBoardPresenter presenter;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.et_search)
    TextView clearEditTextView;

    @BindView(R.id.clear_iv)
    ImageView clear;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.ly)
    LinearLayout ly;

    @BindString(R.string.fragment_bulletin_board_search_hint_str)
    String hintStr;

    private BulletinBoardAdapter adapter;

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.fragment_bulletin_board;
    }

    @Override
    public void init() {
        clearEditTextView.setHint(hintStr);
        ToolBarInject.inject(this, toolbar);
        recyclerView.setBackgroundResource(R.color.white);
        swipeRefreshLayout.setColorSchemeResources(R.color.deep_blue, R.color.d, R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));  //线性布局
        adapter = new BulletinBoardAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        adapter.setListener(new BaseExpandableRecyclerViewAdapter.ExpandableRecyclerViewOnClickListener<BulletinBoard, BulletinBoard.BulletinBoardChild>() {
            @Override
            public boolean onGroupLongClicked(BulletinBoard groupItem) {
                return false;
            }

            @Override
            public boolean onInterceptGroupExpandEvent(BulletinBoard groupItem, boolean isExpand) {
                return false;
            }

            @Override
            public void onGroupClicked(BulletinBoard groupItem) {

            }

            @Override
            public void onChildClicked(BulletinBoard groupItem, BulletinBoard.BulletinBoardChild childItem) {
                ARouter.getInstance().build(GoodsDetailActivity.AROUTER_PATH)
                        .withInt(GoodsDetailActivity.PARAM_ID, childItem.getProduceGoodsReceiptId())
                        .withInt(GoodsDetailActivity.REFRSH_CODE_KEY, BulletinBoardFragment.REFRSH_CODE)
                        .navigation();
            }
        });
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean addToolbar() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(MessageEvent message) {
        if (message.getCode() == SEARCH_EVENT_CODE) {
            if (!TextUtils.isEmpty(message.getMsg())) clear.setVisibility(View.VISIBLE);
            val = message.getMsg();
            clearEditTextView.setText(val);
            presenter.request(val);
        } else if (message.getCode() == REFRSH_CODE) {
            presenter.request(val);
        }

    }

    @OnClick(R.id.clear_iv)
    public void clear() {
        clear.setVisibility(View.GONE);
        val = "";
        clearEditTextView.setText(val);
        presenter.request(val);
    }

    @Override
    public void success(List<BulletinBoard> list) {
        swipeRefreshLayout.setRefreshing(false);
        adapter.setmList(list);
    }

    private String val = "";

    @OnClick(R.id.et_search)
    public void search() {
        ARouter.getInstance().build(SearchResultActivity.AROUTER_PATH)
                .withString(SearchResultActivity.HINT_KEY, hintStr)
                .withInt(SearchResultActivity.SEARCH_KEY_CODE, SEARCH_EVENT_CODE)
                .navigation();
    }

    private boolean first = false;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (!first) {
                first = true;
                val = "";
                swipeRefreshLayout.setRefreshing(true);
                presenter.request(val);
            }
        }
    }

    @Override
    public void sendSearchValue(String value) {
        swipeRefreshLayout.setRefreshing(true);
        clearEditTextView.setText(value);
        val = value;
        presenter.request(val);
    }

    @Override
    public void onRefresh() {
        val = "";
        presenter.request(val);
    }
}
