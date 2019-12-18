package com.zh.xfz.strategy.conversation.impl;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.zh.xfz.R;
import com.zh.xfz.business.activity.NewFriendActivity;
import com.zh.xfz.strategy.conversation.i.MsgOperateService;

import core.app.zh.com.core.base.BaseActivity;
import io.rong.imlib.model.Message;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * author : dayezi
 * data :2019/12/16
 * description:
 */
public class ApplyFriendStrategyImpl implements MsgOperateService {
    private BaseActivity activity;

    public ApplyFriendStrategyImpl(BaseActivity baseActivity) {
        this.activity = baseActivity;
    }

    @Override
    public boolean operate(Message message) {
        String channelId = activity.getResources().getString(R.string.rong_notification_channel_id_msg);
        String title = activity.getResources().getString(R.string.rong_notification_title_msg);
        String msg = activity.getResources().getString(R.string.rong_notification_has_apply_friend_msg);
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

        return false;
    }

    //创建通知渠道
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance, BaseActivity activity) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    private PendingIntent getDefalutIntent(int flags, BaseActivity activity) {
        PendingIntent pendingIntent = PendingIntent.getActivity(activity, 1, new Intent(activity, NewFriendActivity.class), flags);
        return pendingIntent;
    }
}
