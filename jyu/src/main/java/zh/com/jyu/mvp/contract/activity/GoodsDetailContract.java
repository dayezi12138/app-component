package zh.com.jyu.mvp.contract.activity;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;
import zh.com.jyu.bean.activity.GoodsDetail;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
public interface GoodsDetailContract {

    interface GoodsDetailUI extends BaseView {
        void success(GoodsDetail goodsDetail);
    }

    interface Presenter extends IPresenter {
        void request(int id);
    }
}
