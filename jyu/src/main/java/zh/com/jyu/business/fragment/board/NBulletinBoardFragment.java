package zh.com.jyu.business.fragment.board;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hgdendi.expandablerecycleradapter.BaseExpandableRecyclerViewAdapter;
import com.zh.annatation.toolbar.ToolbarTitle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MessageEvent;
import zh.com.jyu.R;
import zh.com.jyu.bean.fragment.BulletinBoard;
import zh.com.jyu.bean.fragment.NBulletinBoard;
import zh.com.jyu.business.activity.BulletinBoardActivity;
import zh.com.jyu.business.activity.SearchResultActivity;
import zh.com.jyu.business.adapter.NBulletinBoardAdapter;
import zh.com.jyu.listener.IntentSearchLister;
import zh.com.jyu.mvp.contract.fragment.BulletinBoardContract;
import zh.com.jyu.mvp.presenter.fragment.board.BulletinBoardPresenter;

/**
 * author : dayezi
 * data :2019/9/4
 * description:
 */
@ToolbarTitle(title = "看板")
public class NBulletinBoardFragment extends BaseFragment implements BulletinBoardContract.BulletinBoardUI, IntentSearchLister, SwipeRefreshLayout.OnRefreshListener {
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

    @BindView(R.id.ly)
    LinearLayout ly;

    @BindString(R.string.fragment_bulletin_board_search_hint_str)
    String hintStr;

    @Inject
    NBulletinBoardAdapter adapter;

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.fragment_n_bulletin_board;
    }

    @Override
    public void init() {
        clearEditTextView.setHint(hintStr);
        recyclerView.setBackgroundResource(R.color.white);
        swipeRefreshLayout.setColorSchemeResources(R.color.deep_blue, R.color.d, R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));  //线性布局
        recyclerView.setAdapter(adapter);
        adapter.setListener(new BaseExpandableRecyclerViewAdapter.ExpandableRecyclerViewOnClickListener<NBulletinBoard, NBulletinBoard.ValueBean>() {
            @Override
            public boolean onGroupLongClicked(NBulletinBoard groupItem) {
                return false;
            }

            @Override
            public boolean onInterceptGroupExpandEvent(NBulletinBoard groupItem, boolean isExpand) {
                Map<String, List<NBulletinBoard.ValueBean>> valueMap = convertData(groupItem.getValue());
                List<NBulletinBoard.ValueBean> list = new ArrayList<>();
                for (String key : valueMap.keySet()) {
                    List<NBulletinBoard.ValueBean> temp = valueMap.get(key);
                    temp.get(0).setVisible(true);
                    list.addAll(valueMap.get(key));
                }
                groupItem.setValue(list);
                Collections.sort(groupItem.getValue());
                return false;
            }

            @Override
            public void onGroupClicked(NBulletinBoard groupItem) {
            }

            @Override
            public void onChildClicked(NBulletinBoard groupItem, NBulletinBoard.ValueBean childItem) {
                ARouter.getInstance().build(BulletinBoardActivity.AROUTER_PATH)
                        .withString(BulletinBoardActivity.SEARCH_KEY, val)
                        .withString(BulletinBoardActivity.CRAFT_ID, String.valueOf(groupItem.getCraftsID()))
                        .withString(BulletinBoardActivity.TITLE, groupItem.getCraftsName())
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


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(MessageEvent message) {
        if (message.getCode() == SEARCH_EVENT_CODE) {
            if (!TextUtils.isEmpty(message.getMsg())) clear.setVisibility(View.VISIBLE);
            val = message.getMsg();
            clearEditTextView.setText(val);
            presenter.getDailyBoardForBoss(val);
        } else if (message.getCode() == REFRSH_CODE) {
            presenter.getDailyBoardForBoss(val);
        }

    }

    @OnClick(R.id.clear_iv)
    public void clear() {
        clear.setVisibility(View.GONE);
        val = "";
        clearEditTextView.setText(val);
        presenter.getDailyBoardForBoss(val);
    }

    @Override
    public void success(List<BulletinBoard> list) {
    }

    @Override
    public void successNBulletinBoard(List<NBulletinBoard> list) {
        swipeRefreshLayout.setRefreshing(false);
        adapter.setmList(list);

    }

    private Map<String, List<NBulletinBoard.ValueBean>> convertData(List<NBulletinBoard.ValueBean> list) {
        Map<String, List<NBulletinBoard.ValueBean>> map = new HashMap<>();
        for (int i = 0, j = list.size(); i < j; i++) {
            List<NBulletinBoard.ValueBean> craftBeans = new ArrayList<>();
            if (!map.containsKey(list.get(i).getPlanNO())) {
                craftBeans.add(list.get(i));
                for (int h = i + 1; h < j; h++) {
                    if (list.get(i).getPlanNO().equals(list.get(h).getPlanNO())) {
                        craftBeans.add(list.get(h));
                    }
                }
                if (!craftBeans.isEmpty())
                    map.put(list.get(i).getPlanNO(), craftBeans);
            }
        }
        return map;
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
                presenter.getDailyBoardForBoss(val);
            }
        }
    }

    @Override
    public void sendSearchValue(String value) {
        swipeRefreshLayout.setRefreshing(true);
        clearEditTextView.setText(value);
        val = value;
        presenter.getDailyBoardForBoss(val);
    }

    @Override
    public void onRefresh() {
        val = "";
        presenter.getDailyBoardForBoss(val);
    }
}
