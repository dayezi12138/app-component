package zh.com.jyu.mvp.model.activity;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.activity.GroupBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.business.activity.GroupListActivity;

/**
 * author : dayezi
 * data :2019/6/20
 * description:
 */
public class GroupListModel extends BaseListModel<GroupBean, GroupListActivity> {

    private GroupListActivity activity;
    private MyService myService;

    @Inject
    public GroupListModel(GroupListActivity activity, MyService myService) {
        this.activity = activity;
        this.myService = myService;
    }

    @Override
    public void requestData(Map<String, Object> params, MyObservable.OnSuccessListener<Data<List<GroupBean>>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity).build();
        myService.getMyCrews(params).subscribe(observable);
    }

    public void delCrew(int id, MyObservable.OnSuccessListener<Data<Object>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity).build();
        myService.delCrew(id).subscribe(observable);
    }

    @Override
    public GroupListActivity getBean() {
        return activity;
    }
}
