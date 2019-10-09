package zh.com.jyu.mvp.model.activity;

import com.rxjava.rxlife.RxLife;
import com.rxjava.rxlife.ScopeViewModel;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseModel;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.activity.GoodsDetail;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.business.activity.GoodsDetailActivity;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
public class GoodsDetailModel implements BaseModel<GoodsDetailActivity> {
    private GoodsDetailActivity goodsDetailActivity;
    private MyService myService;

    @Inject
    public GoodsDetailModel(GoodsDetailActivity goodsDetailActivity, MyService myService) {
        this.goodsDetailActivity = goodsDetailActivity;
        this.myService = myService;
    }

    @Override
    public GoodsDetailActivity getBean() {
        return goodsDetailActivity;
    }

    public void getProduceGoodsReceipt(int id, MyObservable.OnSuccessListener<Data<GoodsDetail>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, goodsDetailActivity).build();
        myService.getProduceGoodsReceipt(id).subscribe(observable);
    }
}
