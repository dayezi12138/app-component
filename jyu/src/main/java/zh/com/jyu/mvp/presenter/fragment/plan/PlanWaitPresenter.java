package zh.com.jyu.mvp.presenter.fragment.plan;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.bean.fragment.PlanBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.mvp.contract.fragment.PlanWaitModel;
import zh.com.jyu.mvp.presenter.fragment.StartStatusPresenter;

/**
 * author : dayezi
 * data :2019/6/12
 * description:
 */
public class PlanWaitPresenter extends StartStatusPresenter implements MyObservable.OnSuccessListener<Data<List<PlanBean>>> {
    private PlanWaitModel model;

    @Inject
    public PlanWaitPresenter(PlanWaitModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void onRefresh(Map<String, Object> params) {
        model.getPlanPager(false, params, this);
    }

    @Override
    public void onloadMored(Map<String, Object> params) {
        model.getPlanPager(true, params, this);
    }

    @Override
    public void onSuccess(Data<List<PlanBean>> listData) {
        if (listData.isState()) {
            if (listData.getRes().isEmpty()) view.get().emptyData();
            else view.get().success(listData.getRes(), model.getPageIndex() == 1);
        }
    }

    @Override
    public void onFail(String msg) {
        view.get().showMsg(msg);
    }
}
