package com.zh.xfz.mvp.contract.fragment;


import com.zh.xfz.bean.fragment.FriendInfo;

import java.util.List;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;

/**
 * author : dayezi
 * data :2019/9/10
 * description:
 */
@Deprecated
public interface ContactContract {
    interface ContactUI extends BaseView {
        void successFriends(List<FriendInfo> sortModels, boolean refresh, boolean more);
    }

    interface Presenter extends IPresenter {

        void refresh();

        void loadMore();

        void searchFriends(String searchVal);
    }
}
