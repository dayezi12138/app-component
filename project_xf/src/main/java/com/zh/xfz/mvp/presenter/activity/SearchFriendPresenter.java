package com.zh.xfz.mvp.presenter.activity;

import com.zh.xfz.mvp.contract.activity.SearchFriendContract;
import com.zh.xfz.mvp.model.activity.SearchFriendModel;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;

/**
 * author : dayezi
 * data :2019/9/23
 * description:
 */
public class SearchFriendPresenter extends BasePresenter<SearchFriendContract.SearchFriendUI> implements SearchFriendContract.Presenter {
    private SearchFriendModel model;

    @Inject
    public SearchFriendPresenter(SearchFriendModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void searchFriend(String search) {
        model.searchFriend(search, listData -> {
            if (listData.getCode() == 0) view.get().success(listData.getRes());
            else view.get().showMsg(listData.getMsg());
        });
    }
}
