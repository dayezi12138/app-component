package zh.com.jyu.mvp.presenter.activity;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.bean.activity.GoodsDetail;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.mvp.contract.activity.GoodsDetailContract;
import zh.com.jyu.mvp.model.activity.GoodsDetailModel;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
public class GoodsDetailPresenter extends BasePresenter<GoodsDetailContract.GoodsDetailUI> implements GoodsDetailContract.Presenter {
    private GoodsDetailModel model;

    @Inject
    public GoodsDetailPresenter(GoodsDetailModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void request(int id) {
        model.getProduceGoodsReceipt(id, new MyObservable.OnSuccessListener<Data<GoodsDetail>>() {
            @Override
            public void onSuccess(Data<GoodsDetail> goodsDetailData) {
                if (goodsDetailData.isState()) view.get().success(goodsDetailData.getRes());
                else view.get().showMsg(goodsDetailData.getMsg());
            }

            @Override
            public void onFail(String msg) {
                view.get().showMsg(msg);
            }
        });
    }
}
