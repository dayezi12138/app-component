package com.zh.xfz.mvp.contract.activity;

import com.zh.xfz.bean.fragment.FriendInfo;

import java.util.List;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;

/**
 * author : dayezi
 * data :2019/9/24
 * description:
 */
public interface FriendContract {
    interface FriendUI extends BaseView {
        void successFriends(List<FriendInfo> sortModels, boolean refresh, boolean more);

//        void createGroupSuccess();
    }

    interface Presenter extends IPresenter {
        void refresh();

        void loadMore();

        void getTargetUserInfo(String targetId);
    }
}
