package com.zh.xfz.mvp.model;

import android.text.TextUtils;

import com.zh.xfz.api.MyService;
import com.zh.xfz.bean.activity.GroupInfo;
import com.zh.xfz.bean.activity.GroupListInfo;
import com.zh.xfz.bean.activity.SearchFriend;
import com.zh.xfz.bean.activity.TargetUserInfo;
import com.zh.xfz.bean.fragment.FriendInfo;
import com.zh.xfz.bean.other.Data;
import com.zh.xfz.constans.Constants;
import com.zh.xfz.utils.AndroidUtils;
import com.zh.xfz.utils.LoginHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseModel;
import core.app.zh.com.core.bean.BaseObservable;
import core.app.zh.com.core.listener.GetMyBaseModelListener;
import core.app.zh.com.core.listener.observable.ObservableListener;
import core.app.zh.com.core.provider.ObservableProvider;

import static com.zh.xfz.constans.RequestParamsConstant.GROUP_ID;
import static com.zh.xfz.constans.RequestParamsConstant.GROUP_NAME;
import static com.zh.xfz.constans.RequestParamsConstant.ID;
import static com.zh.xfz.constans.RequestParamsConstant.IDS;
import static com.zh.xfz.constans.RequestParamsConstant.NAME;
import static com.zh.xfz.constans.RequestParamsConstant.PAGE_INDEX;
import static com.zh.xfz.constans.RequestParamsConstant.PAGE_SIZE;
import static com.zh.xfz.constans.RequestParamsConstant.REMARK;
import static com.zh.xfz.constans.RequestParamsConstant.SEARCH;
import static com.zh.xfz.constans.RequestParamsConstant.TARGET_ID;
import static com.zh.xfz.constans.RequestParamsConstant.TARGET_ID_CAPITAL;
import static com.zh.xfz.constans.RequestParamsConstant.TARGET_ID_I;
import static com.zh.xfz.constans.RequestParamsConstant.TIME_STAMP;
import static com.zh.xfz.constans.RequestParamsConstant.TYPE;
import static com.zh.xfz.constans.RequestParamsConstant.USER_ID;

/**
 * author : dayezi
 * data :2019/12/16
 * description:
 */
public class ConversationModel implements GetMyBaseModelListener {

    @Inject
    MyService myService;
    @Inject
    MyBaseModel myBaseModel;
    @Inject
    ObservableProvider observableProvider;
    @Inject
    LoginHandler loginHandler;

    private int pageIndex = Constants.PAGEINDEX;
    private int pageSize = Constants.PAGESIZE;

    @Inject
    public ConversationModel() {
    }

    /**
     * 根据ID获取群组信息
     *
     * @param groupId
     * @param successListener
     */
    public void getGroupInfo(String groupId, ObservableListener.SuccessListener<Data<GroupListInfo>> successListener) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(USER_ID, String.valueOf(loginHandler.getCurrentUserId()));
        paramMap.put(TIME_STAMP, AndroidUtils.getUUID());
        paramMap.put(GROUP_ID, groupId);
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(false).build(BaseObservable.class);
        myService.getGroupInfo(paramMap).subscribe(observable);
    }

    /**
     * 获取好友列表
     *
     * @param successListener
     */
    public void getUserFriendPageList(ObservableListener.SuccessListener<Data<List<FriendInfo>>> successListener) {
        try {
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put(USER_ID, String.valueOf(loginHandler.getCurrentUserId()));
            paramMap.put(TIME_STAMP, AndroidUtils.getUUID());
            paramMap.put(TYPE, "1");
            paramMap.put(PAGE_SIZE, String.valueOf(pageSize));
            paramMap.put(PAGE_INDEX, String.valueOf(pageIndex));
            BaseObservable observable = observableProvider.successListener(successListener).showDialog(false).build(BaseObservable.class);
            myService.getUserFriendPageList(paramMap).subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加好友
     *
     * @param targetid
     * @param remark
     * @param name
     * @param successListener
     */
    public void applyFriend(String targetid, String remark, String name, ObservableListener.SuccessListener<Data<Object>> successListener) {
        Map<String, String> params = new HashMap<>();
        params.put(USER_ID, String.valueOf(loginHandler.getCurrentUserId()));
        params.put(TIME_STAMP, AndroidUtils.getUUID());
        params.put(TARGET_ID, targetid);
        params.put(REMARK, remark);
        params.put(NAME, name);
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(true).build(BaseObservable.class);
        myService.applyFriend(params).subscribe(observable);
    }

    /**
     * 添加群成员
     *
     * @param groupId
     * @param ids
     * @param successListener
     */
    public void inviteGroupMember(String groupId, String ids, ObservableListener.SuccessListener<Data<Object>> successListener) {
        try {
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put(USER_ID, String.valueOf(loginHandler.getCurrentUserId()));
            paramMap.put(TIME_STAMP, AndroidUtils.getUUID());
            paramMap.put(IDS, ids);
            paramMap.put(GROUP_ID, groupId);
            BaseObservable observable = observableProvider.successListener(successListener).showDialog(true).build(BaseObservable.class);
            myService.inviteGroupMember(paramMap).subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     *创建群
     * @param ids
     * @param groupName
     * @param successListener
     */
    public void createGroup(String ids, String groupName, ObservableListener.SuccessListener<Data<Object>> successListener) {
        try {
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put(USER_ID, String.valueOf(loginHandler.getCurrentUserId()));
            paramMap.put(TIME_STAMP, AndroidUtils.getUUID());
            paramMap.put(GROUP_NAME, groupName);
            paramMap.put(IDS, ids);
            BaseObservable observable = observableProvider.successListener(successListener).showDialog(true).build(BaseObservable.class);
            myService.createGroup(paramMap).subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     *好友申请列表
     * @param successListener
     */
    public void newFriendList(ObservableListener.SuccessListener<Data<List<FriendInfo>>> successListener) {
        try {
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put(USER_ID, String.valueOf(loginHandler.getCurrentUserId()));
            paramMap.put(TIME_STAMP, AndroidUtils.getUUID());
            paramMap.put(PAGE_SIZE, String.valueOf(pageSize));
            paramMap.put(PAGE_INDEX, String.valueOf(pageIndex));
            BaseObservable observable = observableProvider.successListener(successListener).showDialog(false).build(BaseObservable.class);
            myService.getNewFriend(paramMap).subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 同意申请
     *
     * @param friendInfo
     * @param successListener
     */
    public void friendOperate(FriendInfo friendInfo, ObservableListener.SuccessListener<Data<Object>> successListener) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(USER_ID, String.valueOf(loginHandler.getCurrentUserId()));
        paramMap.put(TIME_STAMP, AndroidUtils.getUUID());
        paramMap.put(TYPE, "1");
        paramMap.put(REMARK, TextUtils.isEmpty(friendInfo.getRemarkName()) ? "" : friendInfo.getRemarkName());
        paramMap.put(ID, String.valueOf(friendInfo.getID()));
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(false).build(BaseObservable.class);
        myService.friendOperate(paramMap).subscribe(observable);
    }

    /**
     * 获取好友详细信息
     *
     * @param targetId
     * @param successListener
     */
    public void getTargetUserInfo(String targetId, ObservableListener.SuccessListener<Data<TargetUserInfo>> successListener) {
        Map<String, String> params = new HashMap<>();
        params.put(USER_ID, String.valueOf(loginHandler.getCurrentUserId()));
        params.put(TIME_STAMP, AndroidUtils.getUUID());
        params.put(TARGET_ID, targetId);
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(true).build(BaseObservable.class);
        myService.getTargetUserInfo(params).subscribe(observable);
    }

    /**
     * 删除好友
     *
     * @param targetId
     * @param successListener
     */
    public void deleteFriend(String targetId, ObservableListener.SuccessListener<Data<Object>> successListener) {
        Map<String, String> params = new HashMap<>();
        params.put(ID, targetId);
        params.put(USER_ID, String.valueOf(loginHandler.getCurrentUserId()));
        params.put(TIME_STAMP, AndroidUtils.getUUID());
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(true).build(BaseObservable.class);
        myService.deleteFriend(params).subscribe(observable);
    }

    /**
     * 更新好友备注
     *
     * @param targetId
     * @param name
     * @param successListener
     */
    public void updateFriendMemo(String targetId, String name, ObservableListener.SuccessListener<Data<Object>> successListener) {
        Map<String, String> params = new HashMap<>();
        params.put(ID, targetId);
        params.put(USER_ID, String.valueOf(loginHandler.getCurrentUserId()));
        params.put(TIME_STAMP, AndroidUtils.getUUID());
        params.put(NAME, name);
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(true).build(BaseObservable.class);
        myService.updateFriend(params).subscribe(observable);
    }

    /**
     * 搜好友
     *
     * @param search
     * @param successListener
     */
    public void searchFriend(String search, ObservableListener.SuccessListener<Data<List<SearchFriend>>> successListener) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(USER_ID, String.valueOf(loginHandler.getCurrentUserId()));
        paramMap.put(TIME_STAMP, AndroidUtils.getUUID());
        paramMap.put(SEARCH, search);
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(true).build(BaseObservable.class);
        myService.searchFriend(paramMap).subscribe(observable);
    }

    /**
     * 群列表
     *
     * @param successListener
     */
    public void groupList(ObservableListener.SuccessListener<Data<List<GroupListInfo>>> successListener) {
        Map<String, String> params = new HashMap<>();
        params.put(USER_ID, String.valueOf(loginHandler.getCurrentUserId()));
        params.put(TIME_STAMP, AndroidUtils.getUUID());
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(false).build(BaseObservable.class);
        myService.getGroupList(params).subscribe(observable);
    }

    /**
     * 群成员列表
     *
     * @param groupId
     * @param successListener
     */
    public void getGroupMemberList(String groupId, ObservableListener.SuccessListener<Data<List<GroupInfo>>> successListener) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(USER_ID, String.valueOf(loginHandler.getCurrentUserId()));
        paramMap.put(TIME_STAMP, AndroidUtils.getUUID());
        paramMap.put(GROUP_ID, groupId);
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(false).build(BaseObservable.class);
        myService.getGroupMemberList(paramMap).subscribe(observable);
    }

    /**
     * 解散群/退出群
     *
     * @param groupId
     * @param successListener
     */
    public void dismissOrQuitGroup(String groupId, boolean isDismiss, ObservableListener.SuccessListener<Data<Object>> successListener) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(USER_ID, String.valueOf(loginHandler.getCurrentUserId()));
        paramMap.put(TIME_STAMP, AndroidUtils.getUUID());
        paramMap.put(GROUP_ID, groupId);
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(false).build(BaseObservable.class);
        if (isDismiss) myService.dismissGroup(paramMap).subscribe(observable);
        else myService.quitGroup(paramMap).subscribe(observable);
    }

    /**
     * 更新群名称
     *
     * @param groupId
     * @param groupName
     * @param successListener
     * @param failListener
     */
    public void updateGroupName(String groupId, String groupName, ObservableListener.SuccessListener<Data<Object>> successListener, ObservableListener.FailListener failListener) {
        try {
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put(USER_ID, String.valueOf(loginHandler.getCurrentUserId()));
            paramMap.put(TIME_STAMP, AndroidUtils.getUUID());
            paramMap.put(GROUP_NAME, groupName);
            paramMap.put(GROUP_ID, groupId);
            BaseObservable observable = observableProvider.successListener(successListener).failListener(failListener).showDialog(false).build(BaseObservable.class);
            myService.updateGroupName(paramMap).subscribe(observable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取群成员详细信息
     *
     * @param groupId
     * @param targetId
     * @param successListener
     */
    public void getGroupMemberInfo(String groupId, String targetId, ObservableListener.SuccessListener<Data<GroupInfo>> successListener) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(USER_ID, String.valueOf(loginHandler.getCurrentUserId()));
        paramMap.put(TIME_STAMP, AndroidUtils.getUUID());
        paramMap.put(TARGET_ID_CAPITAL, targetId);
        paramMap.put(GROUP_ID, groupId);
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(false).build(BaseObservable.class);
        myService.getGroupMemberInfo(paramMap).subscribe(observable);
    }


    /**
     * 转让管理员
     *
     * @param groupId
     * @param targetId
     * @param successListener
     * @param failListener
     */
    public void transferGroupAdministrator(String groupId, String targetId, ObservableListener.SuccessListener<Data<Object>> successListener, ObservableListener.FailListener failListener) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(USER_ID, String.valueOf(loginHandler.getCurrentUserId()));
        paramMap.put(TIME_STAMP, AndroidUtils.getUUID());
        paramMap.put(TARGET_ID_I, targetId);
        paramMap.put(GROUP_ID, groupId);
        BaseObservable observable = observableProvider.successListener(successListener).showDialog(true).build(BaseObservable.class);
        myService.transferGroupAdministrator(paramMap).subscribe(observable);
    }

    @Override
    public MyBaseModel getMyBaseModel() {
        return myBaseModel;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }
}
