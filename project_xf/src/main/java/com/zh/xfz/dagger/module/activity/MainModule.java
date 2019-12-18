package com.zh.xfz.dagger.module.activity;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.zh.xfz.R;
import com.zh.xfz.business.activity.LoginActivity;
import com.zh.xfz.business.activity.MainActivity;
import com.zh.xfz.business.activity.im.fragment.MyConversationFragment;
import com.zh.xfz.business.fragment.ContactFragment;
import com.zh.xfz.business.fragment.MainFragment;
import com.zh.xfz.business.fragment.MineFragment;
import com.zh.xfz.business.fragment.WorkControlCenterFragment;
import com.zh.xfz.dagger.module.CommonActivityModule;
import com.zh.xfz.emun.MessageEnum;
import com.zh.xfz.strategy.conversation.MsgStrategy;
import com.zh.xfz.strategy.conversation.impl.ApplyFriendStrategyImpl;
import com.zh.xfz.strategy.conversation.impl.FriendDeleteStrategyImpl;
import com.zh.xfz.strategy.conversation.impl.FriendOperateStrategyImpl;
import com.zh.xfz.utils.LoginHandler;

import java.util.ArrayList;
import java.util.List;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.listener.AppExitListener;
import core.app.zh.com.core.listener.impl.BaseAppExitListener;
import dagger.Module;
import dagger.Provides;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.message.CommandMessage;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * author : dayezi
 * data :2019/8/1
 * description:
 */
@Module(includes = {CommonActivityModule.class})
public class MainModule {


    @ActivityScope
    @Provides
    List<Fragment> fragmentList(MyConversationFragment conversationListFragment) {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(conversationListFragment);
        fragmentList.add(ContactFragment.getInstance());
        fragmentList.add(new MainFragment());
        fragmentList.add(new WorkControlCenterFragment());
        fragmentList.add(new MineFragment());
        return fragmentList;
    }

    @Provides
    @ActivityScope
    public FragmentManager fragmentManager(MainActivity activity) {
        return activity.getSupportFragmentManager();
    }

    @ActivityScope
    @Provides
    MyConversationFragment conversationListFragment(MainActivity mainActivity) {
        MyConversationFragment fragment = new MyConversationFragment();
        Uri uri = Uri.parse("rong://" + mainActivity.getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")
                .build();
        fragment.setUri(uri);
        return fragment;
    }

    @ActivityScope
    @Provides
    public AppExitListener baseAppExitListener(MainActivity activity) {
        return new BaseAppExitListener(activity);
    }

    @ActivityScope
    @Provides
    public BaseActivity activity(MainActivity activity) {
        return activity;
    }

    @ActivityScope
    @Provides
    public RongIMClient.OnReceiveMessageListener onReceiveMessageListener(BaseActivity activity) {
        MsgStrategy.put(MessageEnum.FRIEND_OPERATE.getName(), new FriendOperateStrategyImpl());
        MsgStrategy.put(MessageEnum.FRIEND_DELETE.getName(), new FriendDeleteStrategyImpl());
        MsgStrategy.put(MessageEnum.APPLY_FRIEND.getName(), new ApplyFriendStrategyImpl(activity));
        return (message, i) -> {
            boolean isTrue = message.getContent() instanceof CommandMessage;
            if (isTrue) {
                CommandMessage commandMessage = (CommandMessage) message.getContent();
                return MsgStrategy.get(commandMessage.getName()).operate(message);
            }
            return false;
        };
    }

    @ActivityScope
    @Provides
    public NotificationManager notificationManager(MainActivity activity) {
        return (NotificationManager) activity.getSystemService(NOTIFICATION_SERVICE);
    }

    @SuppressLint("WrongConstant")
    @ActivityScope
    @Provides
    public RongIMClient.ConnectionStatusListener connectionStatusListener(MainActivity activity, LoginHandler loginHandler) {
        return status -> {
            if (status == RongIMClient.ConnectionStatusListener.ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT) {
                loginHandler.clearLogin();
                ARouter.getInstance().build(LoginActivity.AROUTER_PATH).withFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK).navigation();
                ToastUtils.showShort(activity.getResources().getString(R.string.rong_tip_multiple_allocation_login_msg));
            }
        };
    }
}
