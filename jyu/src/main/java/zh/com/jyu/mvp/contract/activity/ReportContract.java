package zh.com.jyu.mvp.contract.activity;

import core.app.zh.com.core.base.IPresenter;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
public interface ReportContract {
    interface Presenter extends IPresenter {
        void delete(int reportId,int position);
    }
}
