//package com.zh.xfz.mvp.presenter.activity;
//
//import com.zh.xfz.mvp.contract.activity.AddFriendContract;
//import com.zh.xfz.mvp.model.activity.AddFriendModel;
//
//import javax.inject.Inject;
//
//import core.app.zh.com.core.base.BasePresenter;
//
///**
// * author : dayezi
// * data :2019/9/24
// * description:
// */
//@Deprecated
//public class AddFriendPresenter extends BasePresenter<AddFriendContract.AddFriendUI> implements AddFriendContract.Presenter {
//    private AddFriendModel model;
//
//    @Inject
//    public AddFriendPresenter(AddFriendModel model) {
//        super(model);
//        this.model = model;
//    }
//
//    @Override
//    public void applyFriend(String targetid, String remark, String name) {
//        model.applyFriend(targetid, remark, name, data -> {
//            if (data.getCode() == 0) view.get().success();
//            else view.get().showMsg(data.getMsg());
//        });
//    }
//}