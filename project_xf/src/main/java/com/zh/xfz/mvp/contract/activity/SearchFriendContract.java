package com.zh.xfz.mvp.contract.activity;

import com.zh.xfz.bean.activity.SearchFriend;

import java.util.List;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;

/**
 * author : dayezi
 * data :2019/9/23
 * description:
 */
@Deprecated
public interface SearchFriendContract {
    interface SearchFriendUI extends BaseView {
        void success(List<SearchFriend> data);
    }

    interface Presenter extends IPresenter {

        void searchFriend(String search);
    }
}
