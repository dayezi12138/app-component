package zh.com.jyu.business.activity;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.api.DefaultOptionListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.bean.ToolbarBean;
import zh.com.jyu.R;
import zh.com.jyu.bean.fragment.BulletinBoard;
import zh.com.jyu.business.adapter.ABulletinBoardAdapter;
import zh.com.jyu.business.fragment.board.BulletinBoardFragment;
import zh.com.jyu.mvp.contract.activity.ABulletinBoardContract;
import zh.com.jyu.mvp.presenter.activity.ABulletinBoardPresenter;

/**
 * author : dayezi
 * data :2019/9/5
 * description:
 */
@Route(path = BulletinBoardActivity.AROUTER_PATH)
//@ToolbarNavigation(visibleNavigation = true)
//@ToolbarTitle(title = "涂装")
public class BulletinBoardActivity extends BaseActivity implements ABulletinBoardContract.ABulletinBoardUI, SwipeRefreshLayout.OnRefreshListener {
    public static final String AROUTER_PATH = "/plan/BulletinBoardActivity/";

    public static final String SEARCH_KEY = "keyword";
    public static final String CRAFT_ID = "craftId";
    public static final String TITLE = "title";
    @Inject
    ABulletinBoardPresenter presenter;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Inject
    ABulletinBoardAdapter adapter;

    @Autowired(name = SEARCH_KEY)
    String keyword;

    @Autowired(name = CRAFT_ID)
    String craftId;

    @Autowired(name = TITLE)
    String title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Inject
    DefaultOptionListener defaultOptionListener;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_bulletin;
    }

    @Override
    public void init() {
        ToolbarBean toolbarBean = new ToolbarBean();
        toolbarBean.setBackgroundColor(getResources().getColor(defaultOptionListener.defaultToolbarOption().getToolbarBackgroundColorId()));
        toolbarBean.getToolbarTitle().setTextColor(getResources().getColor(defaultOptionListener.defaultToolbarOption().getTextColorId()));
        toolbarBean.getNavigation().setNavigationIconId(defaultOptionListener.defaultToolbarOption().getIconId());
        toolbarBean.getNavigation().setVisibleNavigation(true);
        toolbarBean.getToolbarTitle().setText(title);
        initToolBar(toolbar, toolbarBean);
        recyclerView.setBackgroundResource(R.color.white);
        swipeRefreshLayout.setColorSchemeResources(R.color.deep_blue, R.color.d, R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));  //线性布局
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(true);
        onRefresh();
        adapter.setOnItemClickListener((adapter, view, position) -> ARouter.getInstance().build(GoodsDetailActivity.AROUTER_PATH)
                .withInt(GoodsDetailActivity.PARAM_ID, children.get(position).getProduceGoodsReceiptId())
                .withInt(GoodsDetailActivity.REFRSH_CODE_KEY, BulletinBoardFragment.REFRSH_CODE)
                .navigation());
    }

    @Override
    public void onRefresh() {
        presenter.request(keyword, craftId);
    }

    private List<BulletinBoard.BulletinBoardChild> children = new ArrayList<>();

    @Override
    public void success(List<BulletinBoard> list) {
        children.clear();
        swipeRefreshLayout.setRefreshing(false);
        for (BulletinBoard bulletinBoard : list) {
            children.addAll(bulletinBoard.getValue());
        }
        adapter.setNewData(children);
    }

    @Override
    public void showMsg(String msg) {
        swipeRefreshLayout.setRefreshing(false);
        super.showMsg(msg);
    }

    @OnClick({R.id.one_ly, R.id.two_ly, R.id.three_ly})
    public void onClick(View view) {
        swipeRefreshLayout.setRefreshing(true);
        List<BulletinBoard.BulletinBoardChild> data = null;
        switch (view.getId()) {
            case R.id.one_ly:
                data = getChild("gray");
                break;
            case R.id.two_ly:
                data = getChild("green");
                break;
            case R.id.three_ly:
                data = getChild("yellow");
                break;
        }
        if (data != null) adapter.setNewData(data);
        swipeRefreshLayout.setRefreshing(false);
    }

    private List<BulletinBoard.BulletinBoardChild> getChild(String key) {
        List<BulletinBoard.BulletinBoardChild> data = new ArrayList<>();
        for (BulletinBoard.BulletinBoardChild bulletinBoardChild : children) {
            if (bulletinBoardChild.getColor().equals(key)) data.add(bulletinBoardChild);
        }
        return data;
    }
}
