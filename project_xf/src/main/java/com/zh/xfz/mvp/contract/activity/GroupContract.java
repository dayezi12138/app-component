package com.zh.xfz.mvp.contract.activity;

import android.app.Dialog;

import com.zh.xfz.bean.activity.GroupInfo;
import com.zh.xfz.bean.activity.GroupListInfo;
import com.zh.xfz.bean.fragment.FriendInfo;

import java.util.List;
import java.util.Set;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;

/**
 * author : dayezi
 * data :2019/9/24
 * description:
 */
public interface GroupContract {
    interface GroupMemberInfoUI extends GroupUI {
        void groupInfo(GroupInfo groupInfo);

    }

    interface GroupUI extends BaseView {

        default void successGroupList(List<GroupListInfo> data) {

        }

        default void createGroupSuccess() {

        }

        default void successGroupInfoList(List<GroupInfo> data) {

        }

        default void successGroupDetail(GroupListInfo groupListInfo) {
        }

        default void successUpdateNickName(String groupName,boolean isTrue) {

        }


    }

    interface Presenter extends IPresenter {
        void groupList();

        void addGroup(Set<Integer> select, List<FriendInfo> mDateList);

        void inviteGroupMember(Set<Integer> select, List<FriendInfo> mDateList, String groupId);

        void getGroupMemberList(String groupId);

        void getGroupInfo(String groupId);

        void dismissGroup(String groupId);

        void quitGroup(String groupId);

        void updateGroupName(String groupId, String groupName, Dialog dialog);

        void getGroupMemberInfo(String groupId, String targetId);

        void refreshGroupInfo();

        void updateGroupMemberName(String groupId, String targetId, String remarkName);

        void transferGroupAdministrator(String groupId, String targetId, Dialog dialog);
    }
}
