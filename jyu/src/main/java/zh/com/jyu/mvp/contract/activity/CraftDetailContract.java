package zh.com.jyu.mvp.contract.activity;

import java.util.List;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;
import zh.com.jyu.bean.activity.CraftDetailBean;
import zh.com.jyu.bean.activity.UserListBean;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
public interface CraftDetailContract {

    interface CraftDetailUI extends BaseView {
        void success(CraftDetailBean craftDetailBean);

        void successUserList(List<UserListBean> listBeans);

        void successSub();
    }

    interface Presenter extends IPresenter {
        void getProduceCraftsReceipt(int id);

        void selectUser();

        void operation(int status, int craftsReceiptID);
    }
}
