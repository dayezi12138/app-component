package zh.com.jyu.business.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
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
import zh.com.jyu.business.adapter.HomePagerAdapter;
import zh.com.jyu.business.fragment.plan.GoodsListFragment;
import zh.com.jyu.mvp.contract.activity.GoodListContract;
import zh.com.jyu.mvp.presenter.activity.GoodsListPresenter;

/**
 * author : dayezi
 * data :2019/6/21
 * description:
 */
@Route(path = GoodsListActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true)
@ToolbarTitle(title = "生产货品单")
public class GoodsListActivity extends BaseActivity implements GoodListContract.GoodListUI {
    public static final String AROUTER_PATH = "/plan/GoodsListActivity/";
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.mytab)
    TabLayout myTabLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Autowired(name = PickingActivity.PARAM_ID)
    int planId;

    @Inject
    GoodsListPresenter presenter;

    @Inject
    HomePagerAdapter homePagerAdapter;

    private List<GoodListBean> goodListBeanList;

    @Inject
    List<Fragment> fragmentList;

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_goods_list;
    }

    @Override
    public void init() {
        ToolBarInject.inject(this, toolbar);
        viewPager.setAdapter(homePagerAdapter);
        myTabLayout.setupWithViewPager(viewPager, true);
        viewPager.setOffscreenPageLimit(homePagerAdapter.getCount());
        viewPager.setCurrentItem(0);
        myTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setData(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean addToolbar() {
        return true;
    }

    @Override
    public void success(List<GoodListBean> goodListBeanList) {
        this.goodListBeanList = goodListBeanList;
        setData(0);
    }

    private void setData(int status) {
        List<GoodListBean> listBeans = new ArrayList<>();
        for (GoodListBean bean : goodListBeanList) {
            if (String.valueOf(status).equals(bean.getStatus())) {
                listBeans.add(bean);
            }
        }
        GoodsListFragment fragment = (GoodsListFragment) fragmentList.get(status);
        fragment.setData(listBeans);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.requestData(planId);
    }
}
