//package com.zh.xfz.mvp.presenter.fragment;
//
//import com.blankj.utilcode.util.LogUtils;
//import com.zh.xfz.bean.fragment.FriendInfo;
//import com.zh.xfz.bean.other.Data;
//import com.zh.xfz.mvp.contract.fragment.ContactContract;
//import com.zh.xfz.mvp.model.fragment.ContactModel;
//
//import java.util.List;
//
//import javax.inject.Inject;
//
//import core.app.zh.com.core.base.BasePresenter;
//import core.app.zh.com.core.listener.observable.ObservableListener;
//
///**
// * author : dayezi
// * data :2019/9/10
// * description:
// */
//@Deprecated
//public class ContactPresenter extends BasePresenter<ContactContract.ContactUI> implements ContactContract.Presenter {
//
//    @Inject
//    ContactModel model;
//
//    @Inject
//    public ContactPresenter(ContactModel model) {
//        super(model);
////        this.model = model;
//    }
//
//
//    private void request() {
//        model.getUserFriendPageList((ObservableListener.SuccessListener<Data<List<FriendInfo>>>) data -> {
//            if (data.getCode() == 0) {
//                view.get().successFriends(data.getRes(), model.getPageIndex() == 0, data.getRes().size() == model.getPageSize());
//            } else view.get().showMsg(data.getMsg());
//        });
//    }
//
//    @Override
//    public void refresh() {
//        this.model.setPageIndex(0);
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
//    public void searchFriends(String searchVal) {
//        model.searchFriend(searchVal, new ObservableListener.SuccessListener<Data<Object>>() {
//            @Override
//            public void onSuccess(Data<Object> data) {
//                LogUtils.e(data);
//            }
//        });
//    }
//
//}
