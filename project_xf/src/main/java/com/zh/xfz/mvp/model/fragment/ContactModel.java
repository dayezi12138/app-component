//package com.zh.xfz.mvp.model.fragment;
//
//import com.zh.xfz.api.MyService;
//import com.zh.xfz.business.fragment.ContactFragment;
//import com.zh.xfz.constans.Constants;
//import com.zh.xfz.utils.AndroidUtils;
//import com.zh.xfz.utils.LoginUtils;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.inject.Inject;
//
//import core.app.zh.com.core.base.BaseActivity;
//import core.app.zh.com.core.base.BaseView;
//import core.app.zh.com.core.base.MyBaseModel;
//import core.app.zh.com.core.bean.BaseObservable;
//import core.app.zh.com.core.listener.GetMyBaseModelListener;
//import core.app.zh.com.core.listener.observable.ObservableListener;
//import core.app.zh.com.core.provider.ObservableProvider;
//
///**
// * author : dayezi
// * data :2019/9/10
// * description:
// */
//@Deprecated
//public class ContactModel implements GetMyBaseModelListener {
//    private MyService myService;
//    private ContactFragment fragment;
//    private int pageIndex = 0;
//    private int pageSize = Constants.PAGESIZE;
//
//    @Inject
//    public ContactModel(MyService myService, ContactFragment fragment) {
//        this.myService = myService;
//        this.fragment = fragment;
//    }
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
//    public void getUserFriendPageList(ObservableListener.SuccessListener successListener) {
//        try {
//            Map<String, String> paramMap = new HashMap<>();
//            paramMap.put("userid", LoginUtils.getUserId());
//            paramMap.put("timeStamp", AndroidUtils.getUUID());
//            paramMap.put("type", "1");
//            paramMap.put("pageSize", String.valueOf(pageSize));
//            paramMap.put("pageIndex", String.valueOf(pageIndex));
//            BaseObservable observable = new ObservableProvider(fragment.getMyActivity(), successListener).showDialog(false).build(BaseObservable.class);
//            myService.getUserFriendPageList(paramMap).subscribe(observable);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void searchFriend(String searchVal, ObservableListener.SuccessListener successListener) {
//        try {
//            Map<String, String> paramMap = new HashMap<>();
//            paramMap.put("search", searchVal);
//            paramMap.put("timeStamp", AndroidUtils.getUUID());
//            paramMap.put("userid", LoginUtils.getUserId());
//            BaseObservable observable = new ObservableProvider(fragment.getMyActivity(), successListener).build(BaseObservable.class);
//            myService.getUserFriendPageList(paramMap).subscribe(observable);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public MyBaseModel getMyBaseModel() {
//        return new MyBaseModel(fragment.getActivity().getApplication()) {
//            @Override
//            public BaseView getBaseView() {
//                return (fragment != null && fragment instanceof BaseView) ? fragment : null;
//            }
//
//            @Override
//            public BaseActivity getMyActivity() {
//                return fragment.getMyActivity();
//            }
//        };
//    }
//}
