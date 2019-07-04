package zh.com.jyu.mvp.contract.activity;

import java.util.List;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;
import zh.com.jyu.bean.activity.GoodListBean;

/**
 * author : dayezi
 * data :2019/6/13
 * description:
 */
public interface GoodListContract {

    interface GoodListUI extends BaseView {

        void success(List<GoodListBean> goodListBeanList);

    }

    interface Presenter extends IPresenter {
        void requestData(int planId);
    }
}
