package zh.com.jyu.mvp.presenter.activity;

import java.util.List;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.bean.activity.CraftDetailBean;
import zh.com.jyu.bean.activity.UserListBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.mvp.contract.activity.CraftDetailContract;
import zh.com.jyu.mvp.model.activity.CraftDetailModel;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
public class CraftDetailPresenter extends BasePresenter<CraftDetailContract.CraftDetailUI> implements CraftDetailContract.Presenter {
    private CraftDetailModel model;

    @Inject
    public CraftDetailPresenter(CraftDetailModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void getProduceCraftsReceipt(int id) {
        model.getProduceCraftsReceipt(id, new MyObservable.OnSuccessListener<Data<CraftDetailBean>>() {
            @Override
            public void onSuccess(Data<CraftDetailBean> craftDetailBeanData) {
                if (craftDetailBeanData.isState()) view.get().success(craftDetailBeanData.getRes());
                else view.get().showMsg(craftDetailBeanData.getMsg());
            }

            @Override
            public void onFail(String msg) {
                view.get().showMsg(msg);
            }
        });
    }

    @Override
    public void selectUser() {
        model.userList(new MyObservable.OnSuccessListener<Data<List<UserListBean>>>() {
            @Override
            public void onSuccess(Data<List<UserListBean>> listData) {
                if (listData.isState()) view.get().successUserList(listData.getRes());
                else view.get().showMsg(listData.getMsg());
            }

            @Override
            public void onFail(String msg) {
                view.get().showMsg(msg);
            }
        });
    }

    @Override
    public void operation(int status, int craftsReceiptID) {
        model.produceCraftsReceiptOperation(status, craftsReceiptID, new MyObservable.OnSuccessListener<Data<Object>>() {
            @Override
            public void onSuccess(Data<Object> objectData) {
                if (objectData.isState()) {
                    view.get().successSub();
                    model.getBean().finish();
                } else view.get().showMsg(objectData.getMsg());
            }

            @Override
            public void onFail(String msg) {
                view.get().showMsg(msg);
            }
        });
    }
}
