package zh.com.jyu.mvp.presenter.activity;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.bean.activity.RelationOrderBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.mvp.model.activity.RelationOrderListModel;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
public class RelationOrderListPresenter extends BaseListPresenter<RelationOrderBean> {
    private RelationOrderListModel model;

    @Inject
    public RelationOrderListPresenter(RelationOrderListModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void requestData(Map<String, Object> params) {
        model.requestData(params, new MyObservable.OnSuccessListener<Data<List<RelationOrderBean>>>() {
            @Override
            public void onSuccess(Data<List<RelationOrderBean>> listData) {
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
