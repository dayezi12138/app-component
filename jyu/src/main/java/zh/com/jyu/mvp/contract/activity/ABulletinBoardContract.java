package zh.com.jyu.mvp.contract.activity;

import java.util.List;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;
import zh.com.jyu.bean.fragment.BulletinBoard;
import zh.com.jyu.bean.fragment.NBulletinBoard;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
public interface ABulletinBoardContract {
    interface ABulletinBoardUI extends BaseView {
        void success(List<BulletinBoard> list);

    }

    interface Presenter extends IPresenter {
        void request(String key,String craftId);

    }
}
