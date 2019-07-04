package zh.com.jyu.mvp.contract.fragment;

import java.util.List;
import java.util.Map;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;
import zh.com.jyu.bean.activity.CraftBean;

/**
 * author : dayezi
 * data :2019/6/27
 * description:
 */
public interface UnderContract {
    interface UnderUI extends BaseView {

        void success(List<CraftBean> list, boolean refresh);

        void emptyData(boolean refresh);
    }

    interface Presenter extends IPresenter {
        void onloadMored(Map<String, Object> params);

        void onRefresh(Map<String, Object> params);
    }
}
