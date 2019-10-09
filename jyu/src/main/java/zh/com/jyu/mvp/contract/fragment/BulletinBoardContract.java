package zh.com.jyu.mvp.contract.fragment;

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
public interface BulletinBoardContract {
    interface BulletinBoardUI extends BaseView {
        void success(List<BulletinBoard> list);

        void successNBulletinBoard(List<NBulletinBoard> list);
    }

    interface Presenter extends IPresenter {
        void request(String key);

        void getDailyBoardForBoss(String key);
    }
}
