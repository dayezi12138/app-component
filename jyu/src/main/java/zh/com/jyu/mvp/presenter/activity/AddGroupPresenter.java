package zh.com.jyu.mvp.presenter.activity;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.bean.activity.UserList;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.mvp.model.activity.AddGroupModel;

/**
 * author : dayezi
 * data :2019/6/20
 * description:
 */
public class AddGroupPresenter extends BaseListPresenter<UserList.MembersBean> {
    private AddGroupModel model;

    @Inject
    public AddGroupPresenter(AddGroupModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void requestData(Map<String, Object> params) {
        if (params == null) return;
        model.requestData(params, new MyObservable.OnSuccessListener<Data<List<UserList.MembersBean>>>() {
            @Override
            public void onSuccess(Data<List<UserList.MembersBean>> listData) {
                if (listData.isState()) view.get().success(listData.getRes());
                else view.get().showMsg(listData.getMsg());
            }

            @Override
            public void onFail(String msg) {
                view.get().showMsg(msg);
            }
        });
    }

    public void submit(String StaffIDs) {
        model.AddCrew(StaffIDs, new MyObservable.OnSuccessListener<Data<Object>>() {
            @Override
            public void onSuccess(Data<Object> objectData) {
                if (objectData.isState()) {
                    view.get().showMsg("创建成功");
                    model.getBean().finish();
                    return;
                }
            }

            public void onFail(String msg) {
                view.get().showMsg(msg);
            }
        });
    }

}
