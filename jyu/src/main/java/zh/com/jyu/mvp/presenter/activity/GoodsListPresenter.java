package zh.com.jyu.mvp.presenter.activity;

import java.util.List;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.bean.activity.GoodListBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.mvp.contract.activity.GoodListContract;
import zh.com.jyu.mvp.model.activity.GoodsListModel;

/**
 * author : dayezi
 * data :2019/6/21
 * description:
 */
public class GoodsListPresenter extends BasePresenter<GoodListContract.GoodListUI> implements GoodListContract.Presenter {
    private GoodsListModel model;

    @Inject
    public GoodsListPresenter(GoodsListModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void requestData(int planId) {
        model.getProduceGoodsReceiptList(planId, new MyObservable.OnSuccessListener<Data<List<GoodListBean>>>() {
            @Override
            public void onSuccess(Data<List<GoodListBean>> goodListBeanData) {
                if (goodListBeanData.isState()) view.get().success(goodListBeanData.getRes());
                else view.get().showMsg(goodListBeanData.getMsg());
            }

            @Override
            public void onFail(String msg) {
                view.get().showMsg(msg);
            }
        });
    }
}
