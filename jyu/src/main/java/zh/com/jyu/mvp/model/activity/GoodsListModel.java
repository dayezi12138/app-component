package zh.com.jyu.mvp.model.activity;

import java.util.List;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseModel;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.activity.GoodListBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.business.activity.GoodsListActivity;

/**
 * author : dayezi
 * data :2019/6/21
 * description:
 */
public class GoodsListModel implements BaseModel<GoodsListActivity> {
    private GoodsListActivity activity;
    private MyService myService;

    @Inject
    public GoodsListModel(GoodsListActivity activity, MyService myService) {
        this.activity = activity;
        this.myService = myService;
    }

    @Override
    public GoodsListActivity getBean() {
        return activity;
    }

    public void getProduceGoodsReceiptList(int planId, MyObservable.OnSuccessListener<Data<List<GoodListBean>>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity).build();
        myService.getProduceGoodsReceiptList(planId).subscribe(observable);
    }
}
