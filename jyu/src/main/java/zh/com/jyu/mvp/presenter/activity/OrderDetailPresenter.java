package zh.com.jyu.mvp.presenter.activity;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.bean.activity.OrderDetailBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.mvp.contract.activity.OrderDetailContract;
import zh.com.jyu.mvp.model.activity.OrderDetailModel;

/**
 * author : dayezi
 * data :2019/6/25
 * description:
 */
public class OrderDetailPresenter extends BasePresenter<OrderDetailContract.OrderDetailUI> implements OrderDetailContract.Presenter {

    private OrderDetailModel model;

    @Inject
    public OrderDetailPresenter(OrderDetailModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void getTradeDetail(int tradeId) {
        model.getTradeDetail(tradeId, new MyObservable.OnSuccessListener<Data<OrderDetailBean>>() {
            @Override
            public void onSuccess(Data<OrderDetailBean> orderDetailBeanData) {
                if (orderDetailBeanData.isState()) view.get().success(orderDetailBeanData.getRes());
                else view.get().showMsg(orderDetailBeanData.getMsg());
            }

            @Override
            public void onFail(String msg) {
                view.get().showMsg(msg);
            }
        });
    }
}
