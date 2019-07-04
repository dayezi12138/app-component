package zh.com.jyu.mvp.presenter.fragment.produce;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.bean.activity.GoodListBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.mvp.contract.fragment.ProduceListContract;
import zh.com.jyu.mvp.model.fragment.ProduceListModel;

/**
 * author : dayezi
 * data :2019/6/27
 * description:
 */
public class ProduceListPresenter extends BasePresenter<ProduceListContract.ProduceListUI> implements ProduceListContract.Presenter {
    private ProduceListModel model;

    @Inject
    public ProduceListPresenter(ProduceListModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void onRefresh(Map<String, Object> map) {
        model.setPageIndex(1);
        map.put("pageIndex", model.getPageIndex());
        request(map);
    }

    @Override
    public void onloadMored(Map<String, Object> map) {
        model.setPageIndex(model.getPageIndex() + 1);
        map.put("pageIndex", model.getPageIndex());
        request(map);
    }

    private void request(Map<String, Object> map) {
        model.getProduceGoodsReceiptPageList(map, new MyObservable.OnSuccessListener<Data<List<GoodListBean>>>() {
            @Override
            public void onSuccess(Data<List<GoodListBean>> listData) {
                if (listData.isState()) {
                    if (listData.getRes().isEmpty()) view.get().emptyData(model.getPageIndex() == 1);
                    else view.get().success(listData.getRes(), model.getPageIndex() == 1);
                } else view.get().showMsg(listData.getMsg());
            }

            @Override
            public void onFail(String msg) {
                view.get().showMsg(msg);
            }
        });
    }
}
