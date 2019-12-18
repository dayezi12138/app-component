//package com.zh.xfz.mvp.presenter.activity;
//
//import android.app.Dialog;
//import android.content.Intent;
//import android.net.Uri;
//
//import com.blankj.utilcode.util.LogUtils;
//import com.zh.xfz.application.MyApplication;
//import com.zh.xfz.bean.activity.GroupListInfo;
//import com.zh.xfz.bean.fragment.FriendInfo;
//import com.zh.xfz.business.activity.MainActivity;
//import com.zh.xfz.business.activity.im.ConversationActivity;
//import com.zh.xfz.mvp.contract.activity.GroupContract;
//import com.zh.xfz.utils.ResponseStatusUtils;
//
//import org.apache.commons.lang3.StringUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//import javax.inject.Inject;
//
//import core.app.zh.com.core.base.BasePresenter;
//import io.rong.imkit.RongIM;
//import io.rong.imlib.RongIMClient;
//import io.rong.imlib.model.Conversation;
//import io.rong.imlib.model.Group;
//import io.rong.imlib.model.Message;
//import io.rong.message.InformationNotificationMessage;
//
//import static com.zh.xfz.constans.Constants.FLAG_STR;
//
///**
// * author : dayezi
// * data :2019/9/24
// * description:
// */
//@Deprecated
//public class GroupPresenter extends BasePresenter<GroupContract.GroupUI> implements GroupContract.Presenter {
//    private GroupModel model;
//
//    @Inject
//    MyApplication application;
//
//    @Inject
//    public GroupPresenter(GroupModel model) {
//        super(model);
//        this.model = model;
//    }
//
//    @Override
//    public void groupList() {
//        model.groupList(listData -> {
//            if (listData.getCode() == 0) view.get().successGroupList(listData.getRes());
//            else view.get().showMsg(listData.getMsg());
//        });
//    }
//
//    @Override
//    public void addGroup(Set<Integer> select, List<FriendInfo> mDateList) {
//        List<String> idList = new ArrayList<>();
//        StringBuilder name = new StringBuilder();
//        for (Integer position : select) {
//            idList.add(String.valueOf(mDateList.get(position).getID()));
//            name.append(mDateList.get(position).getName()).append(FLAG_STR);
//        }
//        String ids = StringUtils.join(idList.toArray(), ",");
//        model.createGroup(ids, name.toString().substring(0, name.length() - 1), data -> {
//            if (data.getCode() == 0) view.get().createGroupSuccess();
//            else view.get().showMsg(data.getMsg());
//        });
//    }
//
//    @Override
//    public void inviteGroupMember(Set<Integer> select, List<FriendInfo> mDateList, String groupId) {
//        List<String> idList = new ArrayList<>();
//        StringBuilder name = new StringBuilder();
//        for (Integer position : select) {
//            idList.add(String.valueOf(mDateList.get(position).getID()));
//            name.append(mDateList.get(position).getName()).append(FLAG_STR);
//        }
//        String ids = StringUtils.join(idList.toArray(), ",");
//        model.inviteGroupMember(groupId, ids, data -> {
//            if (data.getCode() == 0) {
//                InformationNotificationMessage msg = InformationNotificationMessage.obtain(name.toString().substring(0, name.length() - 1) + "，已加入群組");
//                Message myMessage = Message.obtain(groupId, Conversation.ConversationType.GROUP, msg);
//                RongIM.getInstance().sendMessage(myMessage, null, null, new RongIMClient.SendMessageCallback() {
//                    @Override
//                    public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {
//                        LogUtils.e(errorCode);
//                    }
//
//                    @Override
//                    public void onSuccess(Integer integer) {
//                        LogUtils.e(integer);
//                    }
//                });
//                model.getMyBaseModel().getMyActivity().showMsg("添加成功");
//                model.getMyBaseModel().getMyActivity().finish();
//
//            } else view.get().showMsg(data.getMsg());
//        });
//    }
//
//    @Override
//    public void getGroupMemberList(String groupId) {
//        model.getGroupMemberList(groupId, data -> {
//            if (data.getCode() == 0) {
//                view.get().successGroupInfoList(data.getRes());
//            } else view.get().showMsg(data.getMsg());
//        });
//    }
//
//    @Override
//    public void getGroupInfo(String groupId) {
//        model.getGroupInfo(groupId, data -> {
//            if (data.getCode() == 0) view.get().successGroupDetail(data.getRes());
//            else view.get().showMsg(data.getMsg());
//        });
//    }
//
//    @Override
//    public void dismissGroup(String groupId) {
//        model.dismissGroup(groupId, data -> {
//            if (data.getCode() == 0) quit();
//            else view.get().showMsg(data.getMsg());
//        });
//    }
//
//    @Override
//    public void quitGroup(String groupId) {
//        model.quitGroup(groupId, data -> {
//            if (data.getCode() == 0) quit();
//            else view.get().showMsg(data.getMsg());
//        });
//    }
//
//    @Override
//    public void updateGroupName(String groupId, String groupName, Dialog dialog) {
//        model.updateGroupName(groupId, groupName, data -> {
//            if (data.getCode() == 0) {
//                dialog.dismiss();
//                view.get().successUpdateNickName(groupName, true);
//            } else {
//                view.get().showMsg(data.getMsg());
//                view.get().successUpdateNickName(groupName, false);
//            }
//            dialog.dismiss();
//        }, ex -> {
//            view.get().showMsg(ex.getMessage());
//            view.get().successUpdateNickName(groupName, false);
//            dialog.dismiss();
//        });
//    }
//
//    @Override
//    public void getGroupMemberInfo(String groupId, String targetId) {
//        model.getGroupMemberInfo(groupId, targetId, data -> {
//            if (data.getCode() == 0 && view.get() instanceof GroupContract.GroupMemberInfoUI)
//                ((GroupContract.GroupMemberInfoUI) view.get()).groupInfo(data.getRes());
//            else view.get().showMsg(data.getMsg());
//        });
//    }
//
//    @Override
//    public void getGroupInfoById(String groupId) {
//        model.getGroupInfo(groupId, data -> {
//            if (ResponseStatusUtils.isNormalSuccess(data.getCode())) {
//                GroupListInfo groupListInfo = data.getRes();
//                RongIM.getInstance().refreshGroupInfoCache(new Group(groupId, groupListInfo.getGroupName(), Uri.EMPTY));
//            }
//        });
//    }
//
//    @Override
//    public void updateGroupMemberName(String groupId, String targetId, String remarkName) {
//        model.updateGroupMemberName(groupId, targetId, remarkName, data -> {
//            if (data.getCode() == 0) {
//                model.getMyBaseModel().getMyActivity().startActivity(new Intent(model.getMyBaseModel().getMyActivity(), ConversationActivity.class));
//            } else view.get().showMsg(data.getMsg());
//        });
//    }
//
//    @Override
//    public void transferGroupAdministrator(String groupId, String targetId, Dialog dialog) {
//        model.transferGroupAdministrator(groupId, targetId, data -> {
//            if (data.getCode() == 0) {
//                view.get().showMsg("转让成功");
//            } else view.get().showMsg(data.getMsg());
//            dialog.dismiss();
//        }, ex -> dialog.dismiss());
//    }
//
//    private void quit() {
//        Intent intent = new Intent(model.getMyBaseModel().getMyActivity(), MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        model.getMyBaseModel().getMyActivity().startActivity(intent);
//    }
//}
