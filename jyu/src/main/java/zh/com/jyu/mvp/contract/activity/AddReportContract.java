package zh.com.jyu.mvp.contract.activity;

import java.util.List;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;
import zh.com.jyu.bean.activity.AddReportParam;
import zh.com.jyu.bean.activity.UserListBean;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
public interface AddReportContract {
    interface AddReportUI extends BaseView {
        void successUserList(List<UserListBean> listBeans);

        void submitSuccess();

        void tipShowMsg(String msg);
    }

    interface Presenter extends IPresenter {
        void selectUser();

        void submit(AddReportParam addReportParam);

        void eidt(int reportID, int reportedCount, String remark);
    }
}
