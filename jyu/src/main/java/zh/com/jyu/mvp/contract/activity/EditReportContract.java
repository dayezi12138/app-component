package zh.com.jyu.mvp.contract.activity;

import java.util.Map;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;

/**
 * author : dayezi
 * data :2019/6/20
 * description:
 */
public interface EditReportContract {

    interface EditReportUI extends BaseView {

    }

    interface Presenter extends IPresenter {
        void edit(Map<String, Object> params);
    }
}
