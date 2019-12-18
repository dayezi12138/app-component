package com.zh.xfz.mvp.contract.activity;

import com.zh.xfz.bean.activity.TargetUserInfo;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;

/**
 * author : dayezi
 * data :2019/9/23
 * description:
 */
@Deprecated
public interface FriendDetailContract {

    interface FriendDetailUI extends BaseView {
        void success();

        void updateMemo(String name);

        void successUserInfo(TargetUserInfo targetUserInfo);
    }

    interface Presenter extends IPresenter {

        void delete(String userId);

        void updateMemo(String userId, String memo);

        void getTargetUserInfo(String userId, String targetId);

        void applyFriend(String targetid, String remark, String name);
    }
}
