package zh.com.jyu.mvp.contract.activity;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;

/**
 * author : dayezi
 * data :2019/4/12
 * description:
 */
public interface MainContract {
    interface MainUI extends BaseView {
    }

    interface Presenter extends IPresenter {
        void addFragment();
        void setTabPosition(int index);
    }
}
