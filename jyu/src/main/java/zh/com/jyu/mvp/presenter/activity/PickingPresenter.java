package zh.com.jyu.mvp.presenter.activity;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.bean.activity.PickingBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.mvp.contract.activity.PickingContract;
import zh.com.jyu.mvp.model.activity.PickingModel;

/**
 * author : dayezi
 * data :2019/6/13
 * description:
 */
public class PickingPresenter extends BasePresenter<PickingContract.PickingUI> implements PickingContract.Presenter {
    private PickingModel model;

    @Inject
    public PickingPresenter(PickingModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void getStockOutInfo(int id) {
        model.getStockOutInfo(id, new MyObservable.OnSuccessListener<Data<PickingBean>>() {
            @Override
            public void onSuccess(Data<PickingBean> pickingBeanData) {
                if (pickingBeanData.isState())
                    view.get().stockOutInfo(pickingBeanData.getRes());
                else
                    view.get().fail(pickingBeanData.getMsg());
            }

            @Override
            public void onFail(String msg) {
                view.get().showMsg(msg);
            }
        });
    }
}
