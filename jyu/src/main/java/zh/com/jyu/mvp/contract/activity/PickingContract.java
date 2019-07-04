package zh.com.jyu.mvp.contract.activity;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;
import zh.com.jyu.bean.activity.PickingBean;

/**
 * author : dayezi
 * data :2019/6/13
 * description:
 */
public interface PickingContract {
    interface PickingUI extends BaseView {
        void stockOutInfo(PickingBean pickingBean);

        void fail(String msg);
    }

    interface Presenter extends IPresenter {
        void getStockOutInfo(int id);
    }
}
