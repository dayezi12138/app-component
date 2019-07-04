package zh.com.jyu.business.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.api.ToolBarInject;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BasePresenter;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.OrderDetailBean;
import zh.com.jyu.mvp.contract.activity.OrderDetailContract;
import zh.com.jyu.mvp.presenter.activity.OrderDetailPresenter;

/**
 * author : dayezi
 * data :2019/6/25
 * description:
 */
@Route(path = OrderDetailActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true)
@ToolbarTitle(title = "订单详情")
public class OrderDetailActivity extends BaseActivity implements OrderDetailContract.OrderDetailUI {

    public final static String AROUTER_PATH = "/plan/OrderDetailActivity/";
    public final static String TRADE_ID_KEY = "TRADE_ID_KEY";
    @Inject
    OrderDetailPresenter presenter;

    @Autowired(name = TRADE_ID_KEY)
    int tradeId;

    @BindViews({R.id.trade_status_tv, R.id.trade_no_tv, R.id.trade_time_tv, R.id.account_tv, R.id.priceDis_tv, R.id.send_tv, R.id.remark_tv})
    List<TextView> textViewList;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_order_detail;
    }

    @Override
    public void init() {
        ToolBarInject.inject(this, toolbar);
        presenter.getTradeDetail(tradeId);
    }

    private List<OrderDetailBean.TradeGoodsListBean> tradeGoodsList;

    @Override
    public void success(OrderDetailBean bean) {
        this.tradeGoodsList = bean.getTrade_GoodsList();
        textViewList.get(0).setText(bean.getTrade_TradeList().getTradeStatusExt());
        textViewList.get(1).setText(bean.getTrade_TradeList().getTradeNO());
        textViewList.get(2).setText(bean.getTrade_TradeList().getTradeTime());
        textViewList.get(3).setText(bean.getTrade_TradeList().getCustomerName());
        textViewList.get(4).setText(TextUtils.isEmpty(String.valueOf(bean.getTrade_TradeList().getPriceDis())) ? "" : String.valueOf(bean.getTrade_TradeList().getPriceDis()) + "种");
        textViewList.get(5).setText(String.valueOf(bean.getTrade_TradeList().getYSndTime()));
        textViewList.get(6).setText(String.valueOf(bean.getTrade_TradeList().getRemark()));
    }

    @OnClick(R.id.goods_list_ly)
    public void onClick() {
        if (tradeGoodsList != null) {
            ARouter.getInstance().build(GoodsListHActivity.AROUTER_PATH).withString(GoodsListHActivity.GOODS_LIST_JSON_KEY, new Gson().toJson(tradeGoodsList)).navigation();
        }
    }

    @Override
    public boolean addToolbar() {
        return true;
    }
}
