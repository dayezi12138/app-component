//package com.zh.xfz.mvp.presenter.activity;
//
//import com.zh.xfz.bean.fragment.FriendInfo;
//import com.zh.xfz.mvp.contract.activity.NewFriendContract;
//import com.zh.xfz.mvp.model.activity.NewFriendModel;
//
//import javax.inject.Inject;
//
//import core.app.zh.com.core.base.BasePresenter;
//
///**
// * author : dayezi
// * data :2019/9/12
// * description:
// */
//@Deprecated
//public class NewFriendPresenter extends BasePresenter<NewFriendContract.NewFriendUI> implements NewFriendContract.Presenter {
//    private NewFriendModel model;
//
//    @Inject
//    public NewFriendPresenter(NewFriendModel model) {
//        super(model);
//        this.model = model;
//    }
//
//
//    private void request() {
//        model.getNewFriend(data -> {
//            if (data.getCode() == 0)
//                view.get().successFriends(data.getRes(), model.getPageIndex() == 0, data.getRes().size() == model.getPageSize());
//            else view.get().showMsg(data.getMsg());
//        });
//    }
//
//    @Override
//    public void refresh() {
//        model.setPageIndex(0);
//        request();
//    }
//
//    @Override
//    public void loadMore() {
//        int pageIndex = this.model.getPageIndex() + 1;
//        this.model.setPageIndex(pageIndex);
//        request();
//    }
//
//    @Override
//    public void applyFriend(FriendInfo friendInfo, int position) {
//        this.model.friendOperate(friendInfo, data -> {
//            if (data.getCode() == 0) view.get().successApplyFriend(position);
//            else view.get().showMsg(data.getMsg());
//        });
//    }
//}
