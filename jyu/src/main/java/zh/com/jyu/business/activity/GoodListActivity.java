package zh.com.jyu.business.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.api.ToolBarInject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BasePresenter;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.GoodListBean;
import zh.com.jyu.business.adapter.GoodListAdapter;
import zh.com.jyu.mvp.contract.activity.GoodListContract;
import zh.com.jyu.mvp.presenter.activity.GoodListPresenter;

/**
 * author : dayezi
 * data :2019/6/13
 * description:
 */
@Deprecated
@Route(path = GoodListActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true)
@ToolbarTitle(title = "生产货品单")
@ToolbarLeft(menuId = R.menu.good_list_tool_bar)
public class GoodListActivity extends BaseActivity implements GoodListContract.GoodListUI {

    public static final String AROUTER_PATH = "/plan/GoodListActivity/";

    @Inject
    GoodListPresenter presenter;

    @Inject
    GoodListAdapter adapter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Autowired(name = PickingActivity.PARAM_ID)
    int planId;


    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_good_list;
    }

    @Override
    public void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));  //线性布局
        recyclerView.setAdapter(adapter);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        ToolBarInject.inject(this, toolbar);
        adapter.setOnItemClickListener((adapter, view, position) -> ARouter.getInstance().build(GoodsDetailActivity.AROUTER_PATH)
                .withInt(GoodsDetailActivity.PARAM_ID, GoodListActivity.this.adapter.getData().get(position).getID())
                .navigation());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.requestData(planId);
    }

    private List<GoodListBean> goodListBeanList;

    @Override
    public void success(List<GoodListBean> goodListBeanList) {
        this.goodListBeanList = goodListBeanList;
        setData(0);
    }

    @Override
    public boolean addToolbar() {
        return true;
    }

    @OnMenuOnclick
    public void menuOnclick(MenuItem menuItem) {
        int type = 0;
        switch (menuItem.getItemId()) {
            case R.id.menu_cancel:
                type = 0;
                break;
            case R.id.menu_complete:
                type = 1;
                break;
            case R.id.menu_rollback:
                type = 2;
                break;
            case R.id.menu_detail:
                type = 3;
                break;
        }
        setData(type);
    }

    private void setData(int status) {
        List<GoodListBean> listBeans = new ArrayList<>();
        for (GoodListBean bean : goodListBeanList) {
            if (String.valueOf(status).equals(bean.getStatus())) {
                listBeans.add(bean);
            }
        }
        adapter.setNewData(listBeans);
    }
}
