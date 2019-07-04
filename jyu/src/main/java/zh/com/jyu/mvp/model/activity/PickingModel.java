package zh.com.jyu.mvp.model.activity;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseModel;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.activity.PickingBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.business.activity.PickingActivity;

/**
 * author : dayezi
 * data :2019/6/13
 * description:
 */
public class PickingModel implements BaseModel<PickingActivity> {
    private PickingActivity activity;
    private MyService myService;

    @Inject
    public PickingModel(PickingActivity activity, MyService myService) {
        this.activity = activity;
        this.myService = myService;
    }

    @Override
    public PickingActivity getBean() {
        return activity;
    }

    public void getStockOutInfo(int id, MyObservable.OnSuccessListener<Data<PickingBean>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity).build();
        myService.getStockOutInfo(id).subscribe(observable);
    }
}
