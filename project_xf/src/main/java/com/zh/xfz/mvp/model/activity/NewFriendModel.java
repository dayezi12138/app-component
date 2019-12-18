//package com.zh.xfz.mvp.model.activity;
//
//import android.text.TextUtils;
//
//import com.zh.xfz.api.MyService;
//import com.zh.xfz.bean.fragment.FriendInfo;
//import com.zh.xfz.bean.other.Data;
//import com.zh.xfz.business.activity.NewFriendActivity;
//import com.zh.xfz.utils.AndroidUtils;
//import com.zh.xfz.utils.LoginUtils;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.inject.Inject;
//
//import core.app.zh.com.core.base.BaseActivity;
//import core.app.zh.com.core.base.BaseView;
//import core.app.zh.com.core.base.MyBaseModel;
//import core.app.zh.com.core.bean.BaseObservable;
//import core.app.zh.com.core.provider.ObservableProvider;
//import core.app.zh.com.core.listener.GetMyBaseModelListener;
//import core.app.zh.com.core.listener.observable.ObservableListener;
//
///**
// * author : dayezi
// * data :2019/9/12
// * description:
// */
//@Deprecated
//public class NewFriendModel implements GetMyBaseModelListener {
//    private NewFriendActivity activity;
//    private MyService myService;
//    private int pageIndex = 0;
//    private int pageSize = 25;
//
//    public int getPageIndex() {
//        return pageIndex;
//    }
//
//    public void setPageIndex(int pageIndex) {
//        this.pageIndex = pageIndex;
//    }
//
//    public int getPageSize() {
//        return pageSize;
//    }
//
//    public void setPageSize(int pageSize) {
//        this.pageSize = pageSize;
//    }
//
//    @Inject
//    public NewFriendModel(NewFriendActivity activity, MyService myService) {
//        this.activity = activity;
//        this.myService = myService;
//    }
//
//    public void getNewFriend(ObservableListener.SuccessListener<Data<List<FriendInfo>>> successListener) {
//        try {
//            Map<String, String> paramMap = new HashMap<>();
//            paramMap.put("userid", LoginUtils.getUserId());
//            paramMap.put("timeStamp", AndroidUtils.getUUID());
//            paramMap.put("pageSize", String.valueOf(pageSize));
//            paramMap.put("pageIndex", String.valueOf(pageIndex));
//            BaseObservable observable = new ObservableProvider(activity, successListener).showDialog(false).build(BaseObservable.class);
//            myService.getNewFriend(paramMap).subscribe(observable);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void friendOperate(FriendInfo friendInfo, ObservableListener.SuccessListener<Data<Object>> successListener) {
//        Map<String, String> paramMap = new HashMap<>();
//        paramMap.put("userid", LoginUtils.getUserId());
//        paramMap.put("timeStamp", AndroidUtils.getUUID());
//        paramMap.put("type", "1");
//        paramMap.put("remarkName", TextUtils.isEmpty(friendInfo.getRemarkName()) ? "" : friendInfo.getRemarkName());
//        paramMap.put("id", String.valueOf(friendInfo.getID()));
//
//        try {
//            BaseObservable observable = new ObservableProvider(activity, successListener).showDialog(false).build(BaseObservable.class);
//            myService.friendOperate(paramMap).subscribe(observable);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public MyBaseModel getMyBaseModel() {
//        return new MyBaseModel(activity.getApplication()) {
//            @Override
//            public BaseView getBaseView() {
//                return activity;
//            }
//
//            @Override
//            public BaseActivity getMyActivity() {
//                return (activity != null && activity instanceof BaseView) ? activity : null;
//            }
//        };
//    }
//}
