package zh.com.jyu.mvp.model.activity;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseModel;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.activity.OrderDetailBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.business.activity.OrderDetailActivity;

/**
 * author : dayezi
 * data :2019/6/25
 * description:
 */
public class OrderDetailModel implements BaseModel<OrderDetailActivity> {
    private MyService myService;
    private OrderDetailActivity activity;

    @Inject
    public OrderDetailModel(MyService myService, OrderDetailActivity activity) {
        this.myService = myService;
        this.activity = activity;
    }

    @Override
    public OrderDetailActivity getBean() {
        return activity;
    }

    public void getTradeDetail(int tradeId, MyObservable.OnSuccessListener<Data<OrderDetailBean>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity).build();
        myService.getTradeDetail(tradeId).subscribe(observable);
    }

}
