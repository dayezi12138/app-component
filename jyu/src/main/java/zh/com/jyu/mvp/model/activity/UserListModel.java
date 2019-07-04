package zh.com.jyu.mvp.model.activity;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.activity.UserList;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.business.activity.UserListActivity;

/**
 * author : dayezi
 * data :2019/6/19
 * description:
 */
public class UserListModel extends BaseListModel<UserList, UserListActivity> {

    private UserListActivity userListActivity;
    private MyService myService;

    @Inject
    public UserListModel(UserListActivity userListActivity, MyService myService) {
        this.userListActivity = userListActivity;
        this.myService = myService;
    }

    @Override
    public void requestData(Map<String, Object> params, MyObservable.OnSuccessListener<Data<List<UserList>>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, userListActivity).build();
        myService.getCrewsMember(params).subscribe(observable);
    }

    @Override
    public UserListActivity getBean() {
        return userListActivity;
    }
}
