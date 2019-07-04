package zh.com.jyu.mvp.presenter.fragment.plan;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.bean.fragment.PlanBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.mvp.contract.fragment.NPlanContract;
import zh.com.jyu.mvp.model.fragment.NPlanModel;

/**
 * author : dayezi
 * data :2019/6/27
 * description:
 */
public class NPlanPresenter extends BasePresenter<NPlanContract.NPlanUI> implements NPlanContract.Presenter, MyObservable.OnSuccessListener<Data<List<PlanBean>>> {
    private NPlanModel model;

    @Inject
    public NPlanPresenter(NPlanModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void onloadMored(Map<String, Object> params) {
        model.getPlanPager(true, params, this);
    }

    @Override
    public void onRefresh(Map<String, Object> params) {
        model.getPlanPager(false, params, this);
    }

    @Override
    public void onSuccess(Data<List<PlanBean>> listData) {
        if (listData.isState()) {
            if (listData.getRes().isEmpty()) view.get().emptyData(model.getPageIndex() == 1);
            else view.get().success(listData.getRes(), model.getPageIndex() == 1);
        }
    }

    @Override
    public void onFail(String msg) {
        view.get().showMsg(msg);
    }
}
