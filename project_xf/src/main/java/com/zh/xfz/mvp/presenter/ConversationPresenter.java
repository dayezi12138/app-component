package com.zh.xfz.mvp.presenter;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;

import com.blankj.utilcode.util.LogUtils;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.GroupListInfo;
import com.zh.xfz.bean.fragment.FriendInfo;
import com.zh.xfz.business.activity.MainActivity;
import com.zh.xfz.constans.Constants;
import com.zh.xfz.mvp.contract.ConversationContract;
import com.zh.xfz.mvp.model.ConversationModel;
import com.zh.xfz.utils.ResponseStatusUtils;

import org.apache.commons.lang3.StringUtils;
import org.greenrobot.eventbus.EventBus;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IBaseListView;
import core.app.zh.com.core.bean.MessageEvent;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.Message;
import io.rong.message.InformationNotificationMessage;

import static com.zh.xfz.business.fragment.ContactFragment.CONTACT_EVENT_KEY;
import static com.zh.xfz.constans.Constants.FLAG_STR;
import static com.zh.xfz.constans.Constants.PAGEINDEX;

/**
 * author : dayezi
 * data :2019/12/16
 * description:
 */
public class ConversationPresenter extends BasePresenter<BaseView> implements ConversationContract.Presenter {
    private ConversationModel model;

    @Inject
    public ConversationPresenter(ConversationModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void getGroupInfoById(String groupId) {
        model.getGroupInfo(groupId, groupListInfoData -> model.getGroupInfo(groupId, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode()) && view.get() instanceof ConversationContract.GroupDetailUI) {
                ConversationContract.GroupDetailUI ui = (ConversationContract.GroupDetailUI) view.get();
                ui.groupDetailSuccess(data.getRes());
                return;
            }
            if (ResponseStatusUtils.isNormalSuccess(data.getCode())) {
                GroupListInfo groupListInfo = data.getRes();
                RongIM.getInstance().refreshGroupInfoCache(new Group(groupId, groupListInfo.getGroupName(), Uri.EMPTY));
                return;
            }
        }));
    }

    @Override
    public void onRefresh() {
        this.model.setPageIndex(Constants.PAGEINDEX);
        getUserFriendPageList();
    }

    @Override
    public void onLoadMore() {
        this.model.setPageIndex(this.model.getPageIndex() + 1);
        getUserFriendPageList();
    }

    @Override
    public void applyFriend(String targetid, String remark, String name) {
        model.applyFriend(targetid, remark, name, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode())) {
                MessageEvent messageEvent = new MessageEvent(CONTACT_EVENT_KEY, "");
                EventBus.getDefault().post(messageEvent);
                view.get().showMsg(model.getMyBaseModel().getMyActivity().getResources().getString(R.string.rong_tip_add_friend_success_msg));
                model.getMyBaseModel().getMyActivity().finish();
            }
        });
    }

    @Override
    public void addGroupMember(Set<Integer> select, List<FriendInfo> mDateList, String groupId) {
        List<String> idList = new ArrayList<>();
        StringBuilder name = new StringBuilder();
        for (Integer position : select) {
            idList.add(String.valueOf(mDateList.get(position).getID()));
            name.append(mDateList.get(position).getName()).append(FLAG_STR);
        }
        String ids = StringUtils.join(idList.toArray(), FLAG_STR);
        model.inviteGroupMember(groupId, ids, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode())) {
                String format = MessageFormat.format(model.getMyBaseModel().getMyActivity().getString(R.string.rong_tip_add_group_success_msg)
                        , name.toString().substring(0, name.length() - 1));
                InformationNotificationMessage msg = InformationNotificationMessage.obtain(format);
//                InformationNotificationMessage msg = InformationNotificationMessage.obtain(name.toString().substring(0, name.length() - 1) + "，已加入群組");
                Message myMessage = Message.obtain(groupId, Conversation.ConversationType.GROUP, msg);
                RongIM.getInstance().sendMessage(myMessage, "", "", new RongIMClient.SendMessageCallback() {
                    @Override
                    public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {
                        LogUtils.e(errorCode);
                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        LogUtils.e(integer);
                    }
                });
                view.get().showMsg(model.getMyBaseModel().getMyActivity().getString(R.string.rong_tip_add_friend_success_msg));
                model.getMyBaseModel().getMyActivity().finish();

            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void createGroup(Set<Integer> select, List<FriendInfo> mDateList) {
        List<String> idList = new ArrayList<>();
        StringBuilder name = new StringBuilder();
        for (Integer position : select) {
            idList.add(String.valueOf(mDateList.get(position).getID()));
            name.append(mDateList.get(position).getName()).append(FLAG_STR);
        }
        String ids = StringUtils.join(idList.toArray(), FLAG_STR);
        model.createGroup(ids, name.toString().substring(0, name.length() - 1), data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode())) {
                BaseActivity activity = model.getMyBaseModel().getMyActivity();
                activity.showMsg(activity.getResources().getString(R.string.act_create_success_toast_msg));
                activity.finish();
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void onRefreshNewFriend() {
        model.setPageIndex(PAGEINDEX);
        newFriendList();
    }

    @Override
    public void onLoadMoreNewFriend() {
        model.setPageIndex(model.getPageIndex() + 1);
        newFriendList();
    }

    @Override
    public void agreeNewFriend(FriendInfo friendInfo, int position) {
        model.friendOperate(friendInfo, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode()) && view.get() instanceof ConversationContract.NewFriendListUI) {
                ConversationContract.NewFriendListUI ui = (ConversationContract.NewFriendListUI) view.get();
                ui.agreeNewFriend(position);
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void getFriendDetailInfo(String targetId) {
        model.getTargetUserInfo(targetId, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode()) && view.get() instanceof ConversationContract.FriendDetailInfoUI) {
                ConversationContract.FriendDetailInfoUI ui = (ConversationContract.FriendDetailInfoUI) view.get();
                ui.friendInfo(data.getRes());
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void deleteFriend(String id) {
        model.deleteFriend(id, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode()) && view.get() instanceof ConversationContract.FriendDetailInfoUI) {
                ConversationContract.FriendDetailInfoUI ui = (ConversationContract.FriendDetailInfoUI) view.get();
                ui.deleteFriendSuccess();
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void updateFriendMemo(String targetId, String name) {
        model.updateFriendMemo(targetId, name, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode()) && view.get() instanceof ConversationContract.FriendDetailInfoUI) {
                ConversationContract.FriendDetailInfoUI ui = (ConversationContract.FriendDetailInfoUI) view.get();
                ui.updateFriendSuccess(name);
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void groupList() {
        model.groupList(listData -> {
            if (ResponseStatusUtils.isNormalSuccess(listData.getCode()) && view.get() instanceof ConversationContract.GroupListUI) {
                ConversationContract.GroupListUI ui = (ConversationContract.GroupListUI) view.get();
                ui.listData(listData.getRes(), false);
            } else view.get().showMsg(listData.getMsg());
        });
    }

    @Override
    public void getGroupMemberList(String groupId) {
        model.getGroupMemberList(groupId, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode())) {
                if (view.get() instanceof ConversationContract.GroupDetailUI) {
                    ConversationContract.GroupDetailUI ui = (ConversationContract.GroupDetailUI) view.get();
                    ui.successGroupInfoList(data.getRes());
                    return;
                }
                if (view.get() instanceof IBaseListView) {
                    IBaseListView ui = (IBaseListView) view.get();
                    ui.listData(data.getRes(), true);
                    return;
                }
            } else view.get().showMsg(data.getMsg());

        });
    }

    @Override
    public void dismissOrQuitGroup(String groupId, boolean isDismiss) {
        model.dismissOrQuitGroup(groupId, isDismiss, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode())) {
                Intent intent = new Intent(model.getMyBaseModel().getMyActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                model.getMyBaseModel().getMyActivity().startActivity(intent);
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void updateGroupName(String groupId, String groupName) {
        model.updateGroupName(groupId, groupName, data -> {
            if (view.get() instanceof ConversationContract.GroupDetailUI) {
                ConversationContract.GroupDetailUI ui = (ConversationContract.GroupDetailUI) view.get();
                boolean isSuccess = ResponseStatusUtils.isNormalSuccess(data.getCode());
                ui.updateGroupNameStatus(groupName, isSuccess, isSuccess ? "" : data.getMsg());
            } else view.get().showMsg("");
        }, ex -> {
            if (view.get() instanceof ConversationContract.GroupDetailUI) {
                ConversationContract.GroupDetailUI ui = (ConversationContract.GroupDetailUI) view.get();
                ui.updateGroupNameStatus(groupName, false, ex.getMessage());
            }
        });
    }

    @Override
    public void getGroupMemberInfo(String groupId, String targetId) {
        model.getGroupMemberInfo(groupId, targetId, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode()) && view.get() instanceof ConversationContract.GroupMemberUI) {
                ConversationContract.GroupMemberUI ui = (ConversationContract.GroupMemberUI) view.get();
                ui.groupMemberInfo(data.getRes());
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void searchFriend(String search) {
        model.searchFriend(search, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode()) && view.get() instanceof ConversationContract.SearchUI) {
                ConversationContract.SearchUI ui = (ConversationContract.SearchUI) view.get();
                ui.searchResult(data.getRes());
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void transferGroupAdministrator(String groupId, String targetId, Dialog dialog) {
        model.transferGroupAdministrator(groupId, targetId, data -> {
            String msg = ResponseStatusUtils.isNormalSuccess(data.getCode()) ?
                    model.getMyBaseModel().getMyActivity().getResources().getString(R.string.act_transfer_group_toast_msg)
                    : data.getMsg();
            view.get().showMsg(msg);
            dialog.dismiss();
        }, ex -> dialog.dismiss());
    }


    private void newFriendList() {
        model.newFriendList(data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode()) && view.get() instanceof ConversationContract.NewFriendListUI) {
                ConversationContract.NewFriendListUI ui = (ConversationContract.NewFriendListUI) view.get();
                ui.listData(data.getRes(), model.getPageIndex() == PAGEINDEX ? true : false);
            } else view.get().showMsg(data.getMsg());
        });
    }

    private void getUserFriendPageList() {
        model.getUserFriendPageList(data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode()) && view.get() instanceof ConversationContract.ContactListUI) {
                ConversationContract.ContactListUI ui = (ConversationContract.ContactListUI) view.get();
                ui.listData(data.getRes(), model.getPageIndex() == Constants.PAGEINDEX ? true : false);
            } else view.get().showMsg(data.getMsg());
        });
    }
}
