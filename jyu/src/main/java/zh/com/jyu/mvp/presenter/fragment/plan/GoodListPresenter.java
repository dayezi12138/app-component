package zh.com.jyu.mvp.presenter.fragment.plan;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import zh.com.jyu.mvp.contract.fragment.BaseGoodListContract;
import zh.com.jyu.mvp.model.fragment.GoodListModel;

/**
 * author : dayezi
 * data :2019/6/13
 * description:
 */
public class GoodListPresenter extends BasePresenter<BaseGoodListContract.BaseGoodListUI> implements BaseGoodListContract.Presenter {

    @Inject
    public GoodListPresenter(GoodListModel model) {
        super(model);
    }

    @Override
    public void requestDate(int status, int id) {

    }
}
