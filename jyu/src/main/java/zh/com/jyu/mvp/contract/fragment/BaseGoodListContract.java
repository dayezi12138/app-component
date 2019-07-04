package zh.com.jyu.mvp.contract.fragment;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;

/**
 * author : dayezi
 * data :2019/6/13
 * description:
 */
public interface BaseGoodListContract {

    interface BaseGoodListUI extends BaseView {

    }

    interface Presenter extends IPresenter {
        void requestDate(int status, int id);
    }
}
