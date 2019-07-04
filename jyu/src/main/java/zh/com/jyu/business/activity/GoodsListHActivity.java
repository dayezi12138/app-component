package zh.com.jyu.business.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.api.ToolBarInject;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BasePresenter;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.OrderDetailBean;
import zh.com.jyu.business.adapter.GoodsListHAdapter;

/**
 * author : dayezi
 * data :2019/6/25
 * description:
 */
@Route(path = GoodsListHActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true)
@ToolbarTitle(title = "商品清单")
public class GoodsListHActivity extends BaseActivity {

    public final static String AROUTER_PATH = "/plan/GoodsListHActivity/";
    public final static String GOODS_LIST_JSON_KEY = "GOODS_LIST_JSON_KEY";

    @Autowired(name = GOODS_LIST_JSON_KEY)
    String dataJson;

    @Inject
    GoodsListHAdapter goodsListHAdapter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_good_list;
    }

    @Override
    public void init() {
        ToolBarInject.inject(this, toolbar);
        if (!TextUtils.isEmpty(dataJson)) {
            List<OrderDetailBean.TradeGoodsListBean> listBeanList = new Gson().fromJson(dataJson, new TypeToken<List<OrderDetailBean.TradeGoodsListBean>>() {
            }.getType());
            recyclerView.setLayoutManager(new LinearLayoutManager(this));  //线性布局
            recyclerView.setAdapter(goodsListHAdapter);
            goodsListHAdapter.setNewData(listBeanList);
        }
    }

    @Override
    public boolean addToolbar() {
        return true;
    }
}
