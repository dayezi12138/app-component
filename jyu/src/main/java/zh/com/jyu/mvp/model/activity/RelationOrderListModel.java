package zh.com.jyu.mvp.model.activity;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.activity.RelationOrderBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.business.activity.RelationOrderListActivity;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
public class RelationOrderListModel extends BaseListModel<RelationOrderBean, RelationOrderListActivity> {
    private RelationOrderListActivity activity;
    private MyService myService;

    @Inject
    public RelationOrderListModel(RelationOrderListActivity activity, MyService myService) {
        this.activity = activity;
        this.myService = myService;
    }

    @Override
    public void requestData(Map<String, Object> params, MyObservable.OnSuccessListener<Data<List<RelationOrderBean>>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity).build();
        myService.getTradeList(params).subscribe(observable);
    }

    @Override
    public RelationOrderListActivity getBean() {
        return activity;
    }
}
