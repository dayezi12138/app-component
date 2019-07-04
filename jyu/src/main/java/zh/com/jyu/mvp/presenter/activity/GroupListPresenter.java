package zh.com.jyu.mvp.presenter.activity;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.bean.activity.GroupBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.business.adapter.GroupListAdapter;
import zh.com.jyu.mvp.model.activity.GroupListModel;

/**
 * author : dayezi
 * data :2019/6/20
 * description:
 */
public class GroupListPresenter extends BaseListPresenter<GroupBean> {
    private GroupListModel model;

    @Inject
    public GroupListPresenter(GroupListModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void requestData(Map<String, Object> params) {
        if (params == null) return;
        model.requestData(params, new MyObservable.OnSuccessListener<Data<List<GroupBean>>>() {
            @Override
            public void onSuccess(Data<List<GroupBean>> listData) {
                if (listData.isState()) view.get().success(listData.getRes());
                else view.get().showMsg(listData.getMsg());
            }

            @Override
            public void onFail(String msg) {
                view.get().showMsg(msg);
            }
        });
    }

    public void delete(int position, GroupListAdapter groupListAdapter) {
        model.delCrew(groupListAdapter.getData().get(position).getCrewID(), new MyObservable.OnSuccessListener<Data<Object>>() {
            @Override
            public void onSuccess(Data<Object> objectData) {
                if (objectData.isState()) {
                    groupListAdapter.remove(position);
                } else view.get().showMsg(objectData.getMsg());
            }

            @Override
            public void onFail(String msg) {
                view.get().showMsg(msg);
            }
        });
    }
}
