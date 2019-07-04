package zh.com.jyu.mvp.model.activity;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.activity.CraftBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.business.activity.CraftListActivity;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
public class CraftListModel extends BaseListModel<CraftBean, CraftListActivity> {
    private CraftListActivity activity;
    private MyService myService;

    @Inject
    public CraftListModel(CraftListActivity activity, MyService myService) {
        this.activity = activity;
        this.myService = myService;
    }

    @Override
    public void requestData(Map<String, Object> params, MyObservable.OnSuccessListener<Data<List<CraftBean>>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity).build();
        myService.getProduceCraftsReceiptList(params).subscribe(observable);
    }

    @Override
    public CraftListActivity getBean() {
        return activity;
    }
}
