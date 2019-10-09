package com.zh.xfz.mvp.model.activity;

import com.zh.xfz.api.MyService;
import com.zh.xfz.bean.other.Data;
import com.zh.xfz.business.activity.AddFriendActivity;
import com.zh.xfz.utils.AndroidUtils;
import com.zh.xfz.utils.LoginUtils;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.MyBaseModel;
import core.app.zh.com.core.bean.BaseObservable;
import core.app.zh.com.core.bean.ObservableProvider;
import core.app.zh.com.core.listener.GetMyBaseModelListener;
import core.app.zh.com.core.listener.observable.ObservableListener;

/**
 * author : dayezi
 * data :2019/9/24
 * description:
 */
public class AddFriendModel implements GetMyBaseModelListener {
    private AddFriendActivity activity;
    private MyService myService;

    @Inject
    public AddFriendModel(AddFriendActivity activity, MyService myService) {
        this.activity = activity;
        this.myService = myService;
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
