package zh.com.jyu.mvp.presenter.activity;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.bean.activity.UserList;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.mvp.model.activity.UserListModel;

/**
 * author : dayezi
 * data :2019/6/19
 * description:
 */
public class UserListPresenter extends BaseListPresenter<UserList> {

    private UserListModel model;

    @Inject
    public UserListPresenter(UserListModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void requestData(Map<String, Object> params) {
        model.requestData(params, new MyObservable.OnSuccessListener<Data<List<UserList>>>() {
            @Override
            public void onSuccess(Data<List<UserList>> listData) {
                if (listData.isState()) view.get().success(listData.getRes());
                else view.get().showMsg(listData.getMsg());
            }

            @Override
            public void onFail(String msg) {
                view.get().showMsg(msg);
            }
        });
    }
}
