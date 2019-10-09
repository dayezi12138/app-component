package com.zh.xfz.mvp.contract.activity;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;

/**
 * author : dayezi
 * data :2019/9/23
 * description:
 */
public interface FriendDetailContract {

    interface FriendDetailUI extends BaseView {
        void success();

        void updateMemo(String name);
    }

    interface Presenter extends IPresenter {

        void delete(String userId);

        void updateMemo(String userId, String memo);
    }
}
