package zh.com.jyu.mvp.contract.fragment;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;
import zh.com.jyu.bean.fragment.UpdateInfo;

/**
 * author : dayezi
 * data :2019/8/7
 * description:
 */
public interface MineContract {
    interface MineUI extends BaseView {
        void success(UpdateInfo updateInfo);
    }

    interface Presenter extends IPresenter {

        void checkUpdate();

        void downLoad(String url);
    }
}
