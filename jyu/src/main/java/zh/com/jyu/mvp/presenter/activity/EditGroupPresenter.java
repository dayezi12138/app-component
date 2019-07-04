package zh.com.jyu.mvp.presenter.activity;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.bean.activity.UserList;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.mvp.contract.activity.EditGroupContract;
import zh.com.jyu.mvp.model.activity.EditGroupModel;

/**
 * author : dayezi
 * data :2019/6/20
 * description:
 */
public class EditGroupPresenter extends BasePresenter<EditGroupContract.EditGroupUI> implements EditGroupContract.Presenter {

    private EditGroupModel model;

    @Inject
    public EditGroupPresenter(EditGroupModel model) {
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

    @Override
    public void editCrew(Map<String, Object> params) {
        if (params == null) return;
        model.editCrew(params, new MyObservable.OnSuccessListener<Data<Object>>() {
            @Override
            public void onSuccess(Data<Object> objectData) {
                if (objectData.isState()) {
                    view.get().showMsg("提交成功");
                    model.getBean().finish();
                } else view.get().showMsg(objectData.getMsg());
            }

            @Override
            public void onFail(String msg) {
                view.get().showMsg(msg);
            }
        });
    }

    @Override
    public void requestUser(Map<String, Object> params) {
        if (params == null) return;
        model.getCrewsMember(params, new MyObservable.OnSuccessListener<Data<List<UserList>>>() {
            @Override
            public void onSuccess(Data<List<UserList>> listData) {
                if (listData.isState()) {
                    view.get().successUser(listData.getRes());
                } else view.get().showMsg(listData.getMsg());
            }

            @Override
            public void onFail(String msg) {
                view.get().showMsg(msg);
            }
        });
    }


}
