package zh.com.jyu.mvp.model.activity;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseModel;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.activity.UserList;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.business.activity.EditGroupActivity;

/**
 * author : dayezi
 * data :2019/6/20
 * description:
 */
public class EditGroupModel implements BaseModel<EditGroupActivity> {
    private EditGroupActivity activity;
    private MyService myService;

    @Inject
    public EditGroupModel(EditGroupActivity activity, MyService myService) {
        this.activity = activity;
        this.myService = myService;
    }

    @Override
    public EditGroupActivity getBean() {
        return activity;
    }

    public void requestData(Map<String, Object> params, MyObservable.OnSuccessListener<Data<List<UserList.MembersBean>>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity).build();
        myService.getUserUnder1(params).subscribe(observable);
    }

    public void editCrew(Map<String, Object> params, MyObservable.OnSuccessListener<Data<Object>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity).build();
        myService.editCrew(params).subscribe(observable);
    }

    public void getCrewsMember(Map<String, Object> params, MyObservable.OnSuccessListener<Data<List<UserList>>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity).build();
        myService.getCrewsMember(params).subscribe(observable);
    }
}
