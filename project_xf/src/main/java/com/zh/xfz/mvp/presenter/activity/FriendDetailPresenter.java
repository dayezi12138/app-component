package com.zh.xfz.mvp.presenter.activity;

import com.zh.xfz.mvp.contract.activity.FriendDetailContract;
import com.zh.xfz.mvp.model.activity.FriendDetailModel;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;

/**
 * author : dayezi
 * data :2019/9/23
 * description:
 */
public class FriendDetailPresenter extends BasePresenter<FriendDetailContract.FriendDetailUI> implements FriendDetailContract.Presenter {
    private FriendDetailModel model;

    @Inject
    public FriendDetailPresenter(FriendDetailModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void delete(String userId) {
        model.delete(userId, data -> {
            if (data.getCode() == 0) {
                view.get().success();
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void updateMemo(String userId, String memo) {
        model.updateFriend(userId, memo, data -> {
            if (data.getCode() == 0) {
                view.get().updateMemo(memo);
            } else view.get().showMsg(data.getMsg());
        });
    }
}
