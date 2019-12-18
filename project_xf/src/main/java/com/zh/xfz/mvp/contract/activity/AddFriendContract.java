package com.zh.xfz.mvp.contract.activity;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;

/**
 * author : dayezi
 * data :2019/9/24
 * description:
 */
@Deprecated
public interface AddFriendContract {
    interface AddFriendUI extends BaseView {
        void success();
    }

    interface Presenter extends IPresenter {

        void applyFriend(String targetid, String remark, String name);
    }
}
