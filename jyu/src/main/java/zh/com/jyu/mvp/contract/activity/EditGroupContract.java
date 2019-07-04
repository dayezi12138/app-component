package zh.com.jyu.mvp.contract.activity;

import java.util.List;
import java.util.Map;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;
import zh.com.jyu.bean.activity.UserList;

/**
 * author : dayezi
 * data :2019/6/20
 * description:
 */
public interface EditGroupContract {

    interface EditGroupUI extends BaseView {
        void success(List<UserList.MembersBean> data);
        void successUser(List<UserList> data);
    }

    interface Presenter extends IPresenter {
        void requestData(Map<String, Object> params);

        void editCrew(Map<String, Object> params);

        void requestUser(Map<String, Object> params);
    }
}
