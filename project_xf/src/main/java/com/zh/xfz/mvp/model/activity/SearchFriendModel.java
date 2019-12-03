package com.zh.xfz.mvp.model.activity;

import com.zh.xfz.api.MyService;
import com.zh.xfz.bean.activity.SearchFriend;
import com.zh.xfz.bean.other.Data;
import com.zh.xfz.business.activity.SearchFriendActivity;
import com.zh.xfz.utils.AndroidUtils;
import com.zh.xfz.utils.LoginUtils;

import java.util.HashMap;
import java.util.List;
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
public class SearchFriendModel implements GetMyBaseModelListener {
    private SearchFriendActivity activity;
    private MyService myService;

    @Inject
    public SearchFriendModel(SearchFriendActivity activity, MyService myService) {
        this.activity = activity;
        this.myService = myService;
    }

    public void searchFriend(String search, ObservableListener.SuccessListener<Data<List<SearchFriend>>> successListener) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("userid", LoginUtils.getUserId());
        paramMap.put("timeStamp", AndroidUtils.getUUID());
        paramMap.put("search", search);
        try {
            BaseObservable observable = new ObservableProvider(activity, successListener).build(BaseObservable.class);
            myService.searchFriend(paramMap).subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public MyBaseModel getMyBaseModel() {
        return new MyBaseModel(activity.getApplication()) {
            @Override
            public BaseView getBaseView() {
                return activity;
            }

            @Override
            public BaseActivity getMyActivity() {
                return (activity != null && activity instanceof BaseView) ? activity : null;
            }
        };
    }

}
