package com.zh.xfz.mvp.model.activity;

import com.zh.xfz.api.MyService;
import com.zh.xfz.bean.activity.TargetUserInfo;
import com.zh.xfz.bean.other.Data;
import com.zh.xfz.business.activity.FriendDetailActivity;
import com.zh.xfz.utils.AndroidUtils;
import com.zh.xfz.utils.LoginUtils;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.MyBaseModel;
import core.app.zh.com.core.bean.BaseObservable;
import core.app.zh.com.core.provider.ObservableProvider;
import core.app.zh.com.core.listener.GetMyBaseModelListener;
import core.app.zh.com.core.listener.observable.ObservableListener;

/**
 * author : dayezi
 * data :2019/9/23
 * description:
 */
public class FriendDetailModel implements GetMyBaseModelListener {

    private FriendDetailActivity activity;
    private MyService myService;

    @Inject
    public FriendDetailModel(FriendDetailActivity activity, MyService myService) {
        this.activity = activity;
        this.myService = myService;
    }

    public void delete(String userId, ObservableListener.SuccessListener<Data<Object>> successListener) {
        Map<String, String> params = new HashMap<>();
        params.put("id", userId);
        params.put("userid", LoginUtils.getUserId());
        params.put("timeStamp", AndroidUtils.getUUID());
        try {
            BaseObservable observable = new ObservableProvider(activity, successListener).build(BaseObservable.class);
            myService.deleteFriend(params).subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateFriend(String userId, String name, ObservableListener.SuccessListener<Data<Object>> successListener) {
        Map<String, String> params = new HashMap<>();
        params.put("id", userId);
        params.put("userid", LoginUtils.getUserId());
        params.put("timeStamp", AndroidUtils.getUUID());
        params.put("name", name);
        try {
            BaseObservable observable = new ObservableProvider(activity, successListener).build(BaseObservable.class);
            myService.updateFriend(params).subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTargetUserInfo(String userId, String targetId, ObservableListener.SuccessListener<Data<TargetUserInfo>> successListener) {
        Map<String, String> params = new HashMap<>();
        params.put("userid", LoginUtils.getUserId());
        params.put("timeStamp", AndroidUtils.getUUID());
        params.put("targetid", targetId);
        try {
            BaseObservable observable = new ObservableProvider(activity, successListener).build(BaseObservable.class);
            myService.getTargetUserInfo(params).subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void applyFriend(String targetid, String remark, String name, ObservableListener.SuccessListener<Data<Object>> successListener) {
        Map<String, String> params = new HashMap<>();
        params.put("userid", LoginUtils.getUserId());
        params.put("timeStamp", AndroidUtils.getUUID());
        params.put("targetid", targetid);
        params.put("remark", remark);
        params.put("name", name);
        try {
            BaseObservable observable = new ObservableProvider(activity, successListener).build(BaseObservable.class);
            myService.applyFriend(params).subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public MyBaseModel getMyBaseModel() {
        return new MyBaseModel(activity.getApplication()) {
            @Override
            public BaseView getBaseView() {
                return (activity != null && activity instanceof BaseView) ? activity : null;
            }

            @Override
            public BaseActivity getMyActivity() {
                return activity;
            }
        };
    }
}
