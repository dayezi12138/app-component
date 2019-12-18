//package com.zh.xfz.mvp.model.activity;
//
//import com.zh.xfz.api.MyService;
//import com.zh.xfz.bean.activity.GroupInfo;
//import com.zh.xfz.bean.activity.GroupListInfo;
//import com.zh.xfz.bean.other.Data;
//import com.zh.xfz.utils.AndroidUtils;
//import com.zh.xfz.utils.LoginHandler;
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
//import static com.zh.xfz.constans.RequestParamsConstant.*;
//
///**
// * author : dayezi
// * data :2019/9/24
// * description:
// */
//@Deprecated
//public class GroupModel implements GetMyBaseModelListener {
//    private BaseActivity activity;
//    private MyService myService;
//
//    @Inject
//    ObservableProvider provider;
//    @Inject
//    LoginHandler loginHandler;
//
//    @Inject
//    public GroupModel(BaseActivity activity, MyService myService) {
//        this.activity = activity;
//        this.myService = myService;
//    }
//
//    public void groupList(ObservableListener.SuccessListener<Data<List<GroupListInfo>>> successListener) {
//        try {
//            Map<String, String> params = new HashMap<>();
//            params.put("userid", LoginUtils.getUserId());
//            params.put("timeStamp", AndroidUtils.getUUID());
//            BaseObservable observable = provider.successListener(successListener).showDialog(false).build(BaseObservable.class);
//            myService.getGroupList(params).subscribe(observable);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public void createGroup(String ids, String groupName, ObservableListener.SuccessListener<Data<Object>> successListener) {
//        try {
//            Map<String, String> paramMap = new HashMap<>();
//            paramMap.put("userid", LoginUtils.getUserId());
//            paramMap.put("timeStamp", AndroidUtils.getUUID());
//            paramMap.put("groupName", groupName);
//            paramMap.put("ids", ids);
//            BaseObservable observable = provider.successListener(successListener).showDialog(false).build(BaseObservable.class);
//            myService.createGroup(paramMap).subscribe(observable);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void inviteGroupMember(String groupId, String ids, ObservableListener.SuccessListener<Data<Object>> successListener) {
//        try {
//            Map<String, String> paramMap = new HashMap<>();
//            paramMap.put("userid", LoginUtils.getUserId());
//            paramMap.put("timeStamp", AndroidUtils.getUUID());
//            paramMap.put("ids", ids);
//            paramMap.put("groupId", groupId);
//            BaseObservable observable = new ObservableProvider(activity, successListener).showDialog(false).build(BaseObservable.class);
//            myService.inviteGroupMember(paramMap).subscribe(observable);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void getGroupMemberList(String groupId, ObservableListener.SuccessListener<Data<List<GroupInfo>>> successListener) {
//        try {
//            Map<String, String> paramMap = new HashMap<>();
//            paramMap.put("userid", LoginUtils.getUserId());
//            paramMap.put("timeStamp", AndroidUtils.getUUID());
//            paramMap.put("groupId", groupId);
//            BaseObservable observable = new ObservableProvider(activity, successListener).showDialog(false).build(BaseObservable.class);
//            myService.getGroupMemberList(paramMap).subscribe(observable);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void getGroupInfo(String groupId, ObservableListener.SuccessListener<Data<GroupListInfo>> successListener) {
//        Map<String, String> paramMap = new HashMap<>();
//        paramMap.put(USER_ID, LoginUtils.getUserId());
//        paramMap.put(TIME_STAMP, AndroidUtils.getUUID());
//        paramMap.put(GROUP_ID, groupId);
//        BaseObservable observable = new ObservableProvider(activity, successListener).showDialog(false).build(BaseObservable.class);
//        myService.getGroupInfo(paramMap).subscribe(observable);
//    }
//
//    public void dismissGroup(String groupId, ObservableListener.SuccessListener<Data<Object>> successListener) {
//        try {
//            Map<String, String> paramMap = new HashMap<>();
//            paramMap.put("userid", LoginUtils.getUserId());
//            paramMap.put("timeStamp", AndroidUtils.getUUID());
//            paramMap.put("groupId", groupId);
//            BaseObservable observable = new ObservableProvider(activity, successListener).showDialog(false).build(BaseObservable.class);
//            myService.dismissGroup(paramMap).subscribe(observable);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void quitGroup(String groupId, ObservableListener.SuccessListener<Data<Object>> successListener) {
//        try {
//            Map<String, String> paramMap = new HashMap<>();
//            paramMap.put("userid", LoginUtils.getUserId());
//            paramMap.put("timeStamp", AndroidUtils.getUUID());
//            paramMap.put("groupId", groupId);
//            BaseObservable observable = new ObservableProvider(activity, successListener).showDialog(false).build(BaseObservable.class);
//            myService.quitGroup(paramMap).subscribe(observable);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void updateGroupName(String groupId, String groupName, ObservableListener.SuccessListener<Data<Object>> successListener, ObservableListener.FailListener failListener) {
//        try {
//            Map<String, String> paramMap = new HashMap<>();
//            paramMap.put("userid", LoginUtils.getUserId());
//            paramMap.put("timeStamp", AndroidUtils.getUUID());
//            paramMap.put("groupName", groupName);
//            paramMap.put("groupId", groupId);
//            BaseObservable observable = new ObservableProvider(activity, successListener).failListener(failListener).showDialog(false).build(BaseObservable.class);
//            myService.updateGroupName(paramMap).subscribe(observable);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void getGroupMemberInfo(String groupId, String targetId, ObservableListener.SuccessListener<Data<GroupInfo>> successListener) {
//        try {
//            Map<String, String> paramMap = new HashMap<>();
//            paramMap.put("userid", LoginUtils.getUserId());
//            paramMap.put("timeStamp", AndroidUtils.getUUID());
//            paramMap.put("TargetId", targetId);
//            paramMap.put("groupId", groupId);
//            BaseObservable observable = new ObservableProvider(activity, successListener).showDialog(false).build(BaseObservable.class);
//            myService.getGroupMemberInfo(paramMap).subscribe(observable);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public void updateGroupMemberName(String groupId, String targetId, String remarkName, ObservableListener.SuccessListener<Data<Object>> successListener) {
//        try {
//            Map<String, String> paramMap = new HashMap<>();
//            paramMap.put("userid", LoginUtils.getUserId());
//            paramMap.put("timeStamp", AndroidUtils.getUUID());
//            paramMap.put("TargetId", targetId);
//            paramMap.put("groupId", groupId);
//            paramMap.put("remarkName", remarkName);
//            BaseObservable observable = new ObservableProvider(activity, successListener).showDialog(false).build(BaseObservable.class);
//            myService.getGroupMemberInfo(paramMap).subscribe(observable);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void transferGroupAdministrator(String groupId, String targetId, ObservableListener.SuccessListener<Data<Object>> successListener, ObservableListener.FailListener failListener) {
//        try {
//            Map<String, String> paramMap = new HashMap<>();
//            paramMap.put("userid", LoginUtils.getUserId());
//            paramMap.put("timeStamp", AndroidUtils.getUUID());
//            paramMap.put("targetId", targetId);
//            paramMap.put("groupId", groupId);
//            BaseObservable observable = new ObservableProvider(activity, successListener).failListener(failListener).build(BaseObservable.class);
//            myService.transferGroupAdministrator(paramMap).subscribe(observable);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public MyBaseModel getMyBaseModel() {
//        return new MyBaseModel(activity.getApplication()) {
//            @Override
//            public BaseActivity getMyActivity() {
//                return activity;
//            }
//
//            @Override
//            public BaseView getBaseView() {
//                return (activity != null && activity instanceof BaseView) ? activity : null;
//            }
//        };
//    }
//}
