package zh.com.jyu.mvp.contract.activity;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;
import zh.com.jyu.bean.activity.OrderDetailBean;

/**
 * author : dayezi
 * data :2019/6/25
 * description:
 */
public interface OrderDetailContract {
    interface OrderDetailUI extends BaseView {

        void success(OrderDetailBean bean);
    }

    interface Presenter extends IPresenter {

        void getTradeDetail(int tradeId);
    }
}
