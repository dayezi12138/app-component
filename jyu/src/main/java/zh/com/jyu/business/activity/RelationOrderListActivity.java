package zh.com.jyu.business.activity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.bean.activity.RelationOrderBean;
import zh.com.jyu.business.adapter.RelationOrderListAdapter;
import zh.com.jyu.mvp.presenter.activity.RelationOrderListPresenter;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
@Route(path = RelationOrderListActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true)
@ToolbarTitle(title = "相关订单")
public class RelationOrderListActivity extends BaseListActivity<RelationOrderBean> {

    public final static String AROUTER_PATH = "/plan/RelationOrderListActivity/";
    public final static String PRODUCE_ID_KEY = "id";

    @Autowired(name = PRODUCE_ID_KEY)
    int produceId;

    @Inject
    RelationOrderListAdapter adapter;

    @Inject
    RelationOrderListPresenter presenter;

    @Override
    public MyBaseAdapter myBaseAdapter() {
        return adapter;
    }

    @Override
    public void onItemClick(int position) {
        ARouter.getInstance()
                .build(OrderDetailActivity.AROUTER_PATH)
                .withInt(OrderDetailActivity.TRADE_ID_KEY, adapter.getData().get(position).getTradeID())
                .navigation();
    }

    @Override
    public Map<String, Object> params() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", produceId);
        return map;
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }
}
