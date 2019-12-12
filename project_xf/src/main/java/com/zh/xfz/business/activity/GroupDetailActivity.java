package com.zh.xfz.business.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.suke.widget.SwitchButton;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.GroupInfo;
import com.zh.xfz.bean.activity.GroupListInfo;
import com.zh.xfz.mvp.contract.activity.GroupContract;
import com.zh.xfz.mvp.presenter.activity.GroupPresenter;
import com.zh.xfz.utils.LoginUtils;

import java.text.MessageFormat;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Group;

import static com.zh.xfz.business.activity.GroupMemberListActivity.TRANSER_KEY;

/**
 * author : dayezi
 * data :2019/9/25
 * description:
 */
@Route(path = GroupDetailActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "聊天信息")
public class GroupDetailActivity extends BaseActivity implements GroupContract.GroupUI {
    public final static String AROUTER_PATH = "/main/GroupDetailActivity/";
    public final static String ADD_GROUP = "ADD_GROUP_KEY";

    @Autowired(name = ADD_GROUP)
    String groupId;

    @Inject
    GroupPresenter presenter;

    @BindView(R.id.count_tv)
    TextView countTv;

    @BindView(R.id.group_name_tv)
    TextView groupNameTv;

    @BindView(R.id.notify_status)
    SwitchButton notifyBtn;

    @BindView(R.id.top_status)
    SwitchButton topStatusBtn;

    @BindView(R.id.dissolution_tv)
    TextView dissolutionTv;

    @BindView(R.id.nick_name_tv)
    TextView nickNameTv;

    @Inject
    MaterialDialog.Builder builder;

    @Inject
    @Named("dialog")
    MaterialDialog.Builder builderView;

    private Dialog dialog;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_group_detail;
    }

    @Override
    public void init() {
        presenter.getGroupMemberList(groupId);
        presenter.getGroupInfo(groupId);
        RongIM.getInstance().getConversationNotificationStatus(Conversation.ConversationType.GROUP, groupId, new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
            @Override
            public void onSuccess(Conversation.ConversationNotificationStatus conversationNotificationStatus) {
                boolean check = conversationNotificationStatus == Conversation.ConversationNotificationStatus.NOTIFY ? false : true;
                notifyBtn.setChecked(check);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
        notifyBtn.setOnCheckedChangeListener((view, isChecked) -> {
            Conversation.ConversationNotificationStatus status = isChecked ? Conversation.ConversationNotificationStatus.DO_NOT_DISTURB : Conversation.ConversationNotificationStatus.NOTIFY;
            RongIM.getInstance().setConversationNotificationStatus(Conversation.ConversationType.GROUP, groupId, status, null);
        });
        topStatusBtn.setOnCheckedChangeListener((view, isChecked) -> RongIM.getInstance().setConversationToTop(Conversation.ConversationType.GROUP, groupId, isChecked, null));
        RongIM.getInstance().getConversation(Conversation.ConversationType.GROUP, groupId, new RongIMClient.ResultCallback<Conversation>() {
            @Override
            public void onSuccess(Conversation conversation) {
                if (conversation != null)
                    topStatusBtn.setChecked(conversation.isTop());
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
        dialog = builder.onPositive((dialog, which) -> RongIM.getInstance().clearMessages(Conversation.ConversationType.GROUP, groupId, null)).build();
        toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("title", groupNameTv.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    @Override
    public void successGroupInfoList(List<GroupInfo> data) {
        String format = MessageFormat.format(getResources().getString(R.string.act_group_detail_count_str), data.size());
        countTv.setText(format);
        for (int i = 0, j = data.size(); i < j; i++) {
            GroupInfo info = data.get(i);
            if (String.valueOf(info.getTargetId()).equals(LoginUtils.getUserId())) {
                String nickName = TextUtils.isEmpty(info.getRemarkName()) ? info.getChineseName() : info.getRemarkName();
                nickNameTv.setText(nickName);
                break;
            }
        }
    }

    private GroupListInfo groupListInfo;

    @Override
    public void successGroupDetail(GroupListInfo groupListInfo) {
        this.groupListInfo = groupListInfo;
        groupNameTv.setText(groupListInfo.getGroupName());
        if (String.valueOf(groupListInfo.getAdminUserId()).equals(LoginUtils.getUserId())) {
            dissolutionTv.setText("解散群");
            dissolutionTv.setTag(true);
        }
    }

    @OnClick(R.id.clear)
    public void clear() {
        if (dialog != null && !dialog.isShowing()) dialog.show();
    }

    @OnClick(R.id.group_members_ly)
    public void groupList() {
        ARouter.getInstance().build(GroupMemberListActivity.AROUTER_PATH).withString(GroupMemberListActivity.GROUP_ID_KEY, groupId).navigation();
    }

    @OnClick(R.id.dissolution_ly)
    public void dissolution() {
        String content;
        boolean dissolotion = Boolean.valueOf(dissolutionTv.getTag().toString());
        if (dissolotion) //解散
            content = "确定解散该群吗?";
        else content = "确定退出该群吗?";
        builder.content(content);
        builder.onPositive((dialog, which) -> {
            if (dissolotion) presenter.dismissGroup(groupId);
            else presenter.quitGroup(groupId);
        });
        builder.build().show();
    }

    Dialog groupDialog;

    @OnClick(R.id.group_name_ly)
    public void groupNameClick() {
        if (groupDialog != null && !groupDialog.isShowing()) {
            groupDialog.show();
            return;
        }
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_input, null);
        EditText inputEt = view.findViewById(R.id.memo);
        inputEt.setHint("请输入新的群名称");
        builderView.customView(view, false);
        groupDialog = builderView.build();
        groupDialog.show();
        builderView.onPositive((dialog, which) -> {
            if (TextUtils.isEmpty(inputEt.getText().toString())) return;
            presenter.updateGroupName(groupId, inputEt.getText().toString(), dialog);
        });
    }

    @OnClick(R.id.transfer_ly)
    public void transfer() {
        ARouter.getInstance().build(GroupMemberListActivity.AROUTER_PATH)
                .withString(GroupMemberListActivity.GROUP_ID_KEY, groupId)
                .withBoolean(TRANSER_KEY, true)
                .navigation();
    }

    @Override
    public void successUpdateNickName(String groupName, boolean isTrue) {
        if (isTrue) {
            groupNameTv.setText(groupName);
            RongIM.getInstance().refreshGroupInfoCache(new Group(String.valueOf(groupListInfo.getID()), groupName, null));
        }
    }
}
