package zh.com.jyu.mvp.contract.activity;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;

/**
 * author : dayezi
 * data :2019/6/10
 * description:
 */
public interface LoginContract {
    interface LoginUI extends BaseView {
    }

    interface Presenter extends IPresenter {

        void login(String userName, String password);
    }
}
