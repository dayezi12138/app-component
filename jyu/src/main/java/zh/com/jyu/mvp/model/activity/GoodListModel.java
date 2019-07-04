package zh.com.jyu.mvp.model.activity;

import java.util.List;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseModel;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.activity.GoodListBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.business.activity.GoodListActivity;

/**
 * author : dayezi
 * data :2019/6/13
 * description:
 */
public class GoodListModel implements BaseModel<GoodListActivity> {
    private GoodListActivity activity;
    private MyService myService;

    @Inject
    public GoodListModel(GoodListActivity activity, MyService myService) {
        this.activity = activity;
        this.myService = myService;
    }

    @Override
    public GoodListActivity getBean() {
        return activity;
    }

    public void getProduceGoodsReceiptList(int planId, MyObservable.OnSuccessListener<Data<List<GoodListBean>>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity).build();
        myService.getProduceGoodsReceiptList(planId).subscribe(observable);
    }
}
