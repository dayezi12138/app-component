package zh.com.jyu.mvp.model.activity;

import com.blankj.utilcode.util.SPUtils;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.activity.UserList;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.business.activity.AddGroupActivity;
import zh.com.jyu.constans.SpKeyConstant;

/**
 * author : dayezi
 * data :2019/6/20
 * description:
 */
public class AddGroupModel extends BaseListModel<UserList.MembersBean, AddGroupActivity> {
    private AddGroupActivity activity;
    private MyService myService;

    @Inject
    public AddGroupModel(AddGroupActivity activity, MyService myService) {
        this.activity = activity;
        this.myService = myService;
    }

    @Override
    public void requestData(Map<String, Object> params, MyObservable.OnSuccessListener<Data<List<UserList.MembersBean>>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity).build();
        myService.getUserUnder1(params).subscribe(observable);
    }

    public void AddCrew(String staffIDs, MyObservable.OnSuccessListener<Data<Object>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity).build();
        myService.addCrew(SPUtils.getInstance().getInt(SpKeyConstant.LOGIN_UID_KEY), staffIDs).subscribe(observable);
    }


    @Override
    public AddGroupActivity getBean() {
        return activity;
    }
}
