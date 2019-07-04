package zh.com.jyu.mvp.presenter.fragment.leader;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.bean.activity.CraftBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.mvp.contract.fragment.LeaderContract;
import zh.com.jyu.mvp.model.fragment.LeaderModel;

/**
 * author : dayezi
 * data :2019/6/19
 * description:
 */
public class LeaderPresenter extends BasePresenter<LeaderContract.LeaderUI> implements LeaderContract.Presenter {

    private LeaderModel model;

    @Inject
    public LeaderPresenter(LeaderModel model) {
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
    public void onLoadMore(Map<String, Object> map) {
        model.setPageIndex(model.getPageIndex() + 1);
        map.put("pageIndex", model.getPageIndex());
        request(map);
    }

    private void request(Map<String, Object> map) {
        model.getProduceCraftsReceiptListPager(map, new MyObservable.OnSuccessListener<Data<List<CraftBean>>>() {
            @Override
            public void onSuccess(Data<List<CraftBean>> listData) {
                if (listData.isState()) {
                    if (listData.getRes().isEmpty()) view.get().emptyData();
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
