package zh.com.jyu.mvp.contract.activity;

import java.util.List;
import java.util.Map;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;
import zh.com.jyu.bean.activity.CraftBean;

/**
 * author : dayezi
 * data :2019/6/28
 * description:
 */
public interface CraftListContract {
    interface CraftListUI extends BaseView {

        void success(List<CraftBean> list, boolean refresh);

        void emptyData();
    }

    interface Presenter extends IPresenter {
        void onloadMored(Map<String, Object> params);

        void onRefresh(Map<String, Object> params);
    }
}
