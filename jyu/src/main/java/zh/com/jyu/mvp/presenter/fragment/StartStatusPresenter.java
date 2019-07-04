package zh.com.jyu.mvp.presenter.fragment;


import core.app.zh.com.core.base.BaseModel;
import core.app.zh.com.core.base.BasePresenter;
import zh.com.jyu.mvp.contract.fragment.StartStatusContract;

/**
 * author : dayezi
 * data :2019/6/12
 * description:
 */
public abstract class StartStatusPresenter extends BasePresenter<StartStatusContract.StartStatusUI> implements StartStatusContract.Presenter {
    public StartStatusPresenter(BaseModel model) {
        super(model);
    }
}
