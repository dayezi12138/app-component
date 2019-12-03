package com.zh.xfz.mvp.model.activity;

import com.zh.xfz.api.MyService;
import com.zh.xfz.bean.activity.TargetUserInfo;
import com.zh.xfz.bean.fragment.FriendInfo;
import com.zh.xfz.bean.other.Data;
import com.zh.xfz.mvp.model.CommonModel;
import com.zh.xfz.utils.AndroidUtils;
import com.zh.xfz.utils.LoginUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.MyBaseModel;
import core.app.zh.com.core.bean.BaseObservable;
import core.app.zh.com.core.provider.ObservableProvider;
import core.app.zh.com.core.listener.GetMyBaseModelListener;
import core.app.zh.com.core.listener.observable.ObservableListener;

/**
 * author : dayezi
 * data :2019/9/24
 * description:
 */
public class FriendModel implements GetMyBaseModelListener {
    @Inject
    BaseActivity activity;
    @Inject
    MyService myService;
    @Inject
    CommonModel model;

    private int pageIndex = 0;
    private int pageSize = 25;

    @Inject
    public FriendModel() {
    }

    public void getUserFriendPageList(ObservableListener.SuccessListener<Data<List<FriendInfo>>> successListener) {
        try {
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("userid", LoginUtils.getUserId());
            paramMap.put("timeStamp", AndroidUtils.getUUID());
            paramMap.put("type", "1");
            paramMap.put("pageSize", String.valueOf(pageSize));
            paramMap.put("pageIndex", String.valueOf(pageIndex));
            BaseObservable observable = new ObservableProvider(activity, successListener).showDialog(false).build(BaseObservable.class);
            myService.getUserFriendPageList(paramMap).subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createGroup(String ids, String groupName, ObservableListener.SuccessListener<Data<Object>> successListener) {
        try {
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("userid", LoginUtils.getUserId());
            paramMap.put("timeStamp", AndroidUtils.getUUID());
            paramMap.put("groupName", groupName);
            paramMap.put("ids", ids);
            BaseObservable observable = new ObservableProvider(activity, successListener).showDialog(false).build(BaseObservable.class);
            myService.createGroup(paramMap).subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTargetUserInfo(String targetId, ObservableListener.SuccessListener<Data<TargetUserInfo>> successListener) {
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

    @Override
    public MyBaseModel getMyBaseModel() {
        return model;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }
}
