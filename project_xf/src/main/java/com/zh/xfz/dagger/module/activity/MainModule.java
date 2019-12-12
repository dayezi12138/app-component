package com.zh.xfz.dagger.module.activity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.zh.xfz.R;
import com.zh.xfz.application.MyApplication;
import com.zh.xfz.business.activity.LoginActivity;
import com.zh.xfz.business.activity.MainActivity;
import com.zh.xfz.business.activity.NewFriendActivity;
import com.zh.xfz.business.activity.im.fragment.MyConversationFragment;
import com.zh.xfz.business.fragment.ContactFragment;
import com.zh.xfz.business.fragment.MainFragment;
import com.zh.xfz.business.fragment.MineFragment;
import com.zh.xfz.business.fragment.WorkControlCenterFragment;
import com.zh.xfz.emun.MessageEnum;
import com.zh.xfz.utils.LoginUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.MyBaseModel;
import core.app.zh.com.core.bean.MessageEvent;
import core.app.zh.com.core.listener.AppExitListener;
import core.app.zh.com.core.listener.impl.BaseAppExitListener;
import dagger.Module;
import dagger.Provides;
import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.CommandMessage;
import io.rong.message.TextMessage;

import static android.content.Context.NOTIFICATION_SERVICE;
import static com.zh.xfz.business.fragment.ContactFragment.CONTACT_EVENT_KEY;

/**
 * author : dayezi
 * data :2019/8/1
 * description:
 */
@Module
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
    public MyBaseModel baseModel(BaseActivity activity) {
        return new MyBaseModel(activity.getApplication()) {
            @Override
            public BaseView getBaseView() {
                return activity;
            }

            @Override
            public BaseActivity getMyActivity() {
                return activity;
            }
        };
    }

    @ActivityScope
    @Provides
    public RongIMClient.OnReceiveMessageListener onReceiveMessageListener(MainActivity activity, MyApplication application) {

        return (message, i) -> {
            boolean isTrue = message.getContent() instanceof CommandMessage;
            if (isTrue) {
                CommandMessage commandMessage = (CommandMessage) message.getContent();
                if (MessageEnum.FRIEND_OPERATE.getName().equals(commandMessage.getName())) {
                    TextMessage informationNotificationMessage = TextMessage.obtain("已通过好友申请,现在可以开始聊天了");
                    Message myMessage = Message.obtain(message.getTargetId(), Conversation.ConversationType.PRIVATE, informationNotificationMessage);
                    RongIM.getInstance().sendMessage(myMessage, null, null, (IRongCallback.ISendMessageCallback) null);
                    MessageEvent messageEvent = new MessageEvent(CONTACT_EVENT_KEY, "");
                    EventBus.getDefault().post(messageEvent);
                    return false;
                }
                if (MessageEnum.FRIEND_DELETE.getName().equals(commandMessage.getName())) {
                    MessageEvent event = new MessageEvent(ContactFragment.CONTACT_EVENT_KEY, "");
                    EventBus.getDefault().post(event);
                    RongIM.getInstance().removeConversation(Conversation.ConversationType.PRIVATE, message.getTargetId(), null);
                    return false;
                }
                if (MessageEnum.APPLY_FRIEND.getName().equals(commandMessage.getName())) {
                    String channelId = "compose";
                    String title = "通知";
                    String msg = "您有一条好友申请讯息";
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationManager manager = (NotificationManager) activity.getSystemService(NOTIFICATION_SERVICE);
                    NotificationCompat.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        builder = new NotificationCompat.Builder(activity, channelId);
                        createNotificationChannel(channelId, title, importance, activity);
                    } else {
                        builder = new NotificationCompat.Builder(activity);
                    }
                    Notification notification = builder
                            .setContentTitle(title)
                            .setContentText(msg)
                            .setWhen(System.currentTimeMillis())
                            .setSmallIcon(R.mipmap.logo)
                            .setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL, activity))
                            .setLargeIcon(BitmapFactory.decodeResource(activity.getResources(), R.mipmap.logo))
                            .build();
                    manager.notify(101, notification);
                }

            }
            return false;
        }
                ;
    }

    //创建通知渠道
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance, MainActivity activity) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    @ActivityScope
    @Provides
    public NotificationManager notificationManager(MainActivity activity) {
        return (NotificationManager) activity.getSystemService(NOTIFICATION_SERVICE);
    }

    @ActivityScope
    @Provides
    public NotificationCompat.Builder notification(MainActivity activity) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(activity);
        mBuilder.setContentTitle("消息")
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setOngoing(false)
                .setSmallIcon(R.mipmap.logo);
        return mBuilder;
    }

    public PendingIntent getDefalutIntent(int flags, MainActivity activity) {
        PendingIntent pendingIntent = PendingIntent.getActivity(activity, 1, new Intent(activity, NewFriendActivity.class), flags);
        return pendingIntent;
    }

    @SuppressLint("WrongConstant")
    @ActivityScope
    @Provides
    public RongIMClient.ConnectionStatusListener connectionStatusListener(MainActivity activity) {
        return status -> {
            if (status == RongIMClient.ConnectionStatusListener.ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT) {
                LoginUtils.clearLoginInfo();
                ARouter.getInstance().build(LoginActivity.AROUTER_PATH).withFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK).navigation();
                ToastUtils.showShort("该账号已在其他设备上登录");
            }
        };
    }
}
