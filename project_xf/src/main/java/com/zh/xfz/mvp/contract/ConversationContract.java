package com.zh.xfz.mvp.contract;

import android.app.Dialog;

import com.zh.xfz.bean.activity.GroupInfo;
import com.zh.xfz.bean.activity.GroupListInfo;
import com.zh.xfz.bean.activity.SearchFriend;
import com.zh.xfz.bean.activity.TargetUserInfo;
import com.zh.xfz.bean.fragment.FriendInfo;

import java.util.List;
import java.util.Set;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IBaseListView;
import core.app.zh.com.core.base.IPresenter;

/**
 * author : dayezi
 * data :2019/12/16
 * description:
 */
public interface ConversationContract {
    interface ContactListUI extends IBaseListView<FriendInfo> {

    }

    interface NewFriendListUI extends IBaseListView<FriendInfo> {
        void agreeNewFriend(int position);
    }

    interface FriendDetailInfoUI extends BaseView {
        void friendInfo(TargetUserInfo targetUserInfo);

        void deleteFriendSuccess();

        void updateFriendSuccess(String name);
    }

    interface GroupListUI extends IBaseListView<GroupListInfo> {

    }

    interface GroupDetailUI extends BaseView {
        void successGroupInfoList(List<GroupInfo> data);

        void groupDetailSuccess(GroupListInfo info);

        void updateGroupNameStatus(String groupName, boolean isSuccess, String errorMsg);

    }


    interface GroupMemberUI extends BaseView {
        void groupMemberInfo(GroupInfo groupInfo);
    }


    interface SearchUI extends BaseView {
        void searchResult(List<SearchFriend> searchFriends);
    }

    interface Presenter extends IPresenter {
        void getGroupInfoById(String groupId);

        void onRefresh();

        void onLoadMore();

        void applyFriend(String targetid, String remark, String name);

        void addGroupMember(Set<Integer> select, List<FriendInfo> mDateList, String groupId);

        void createGroup(Set<Integer> select, List<FriendInfo> mDateList);

        void onRefreshNewFriend();

        void onLoadMoreNewFriend();

        void agreeNewFriend(FriendInfo friendInfo, int position);

        void getFriendDetailInfo(String targetId);

        void deleteFriend(String id);

        void updateFriendMemo(String targetId, String name);

        void groupList();

        void getGroupMemberList(String groupId);

        void dismissOrQuitGroup(String groupId, boolean isDismiss);

        void updateGroupName(String groupId, String groupName);

        void getGroupMemberInfo(String groupId, String targetId);

        void searchFriend(String search);

        void transferGroupAdministrator(String groupId, String targetId, Dialog dialog);
    }
}
