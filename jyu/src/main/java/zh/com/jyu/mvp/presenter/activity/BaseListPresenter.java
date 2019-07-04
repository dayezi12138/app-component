package zh.com.jyu.mvp.presenter.activity;

import core.app.zh.com.core.base.BaseModel;
import core.app.zh.com.core.base.BasePresenter;
import zh.com.jyu.mvp.contract.activity.BaseListContract;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
public abstract class BaseListPresenter<T> extends BasePresenter<BaseListContract.BaseListUI<T>> implements BaseListContract.BaseListPreseneter {

    public BaseListPresenter(BaseModel model) {
        super(model);
    }

}
