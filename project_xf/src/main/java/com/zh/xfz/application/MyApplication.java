package com.zh.xfz.application;

import android.content.Context;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.zh.api.DefaultBean;
import com.zh.api.ToolBarInject;
import com.zh.xfz.bean.fragment.FriendInfo;
import com.zh.xfz.business.activity.FriendDetailActivity;
import com.zh.xfz.dagger.component.DaggerMyAppComponent;
import com.zh.xfz.dagger.component.MyAppComponent;
import com.zh.xfz.dagger.module.AppModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import core.app.zh.com.core.application.BaseApplication;
import core.app.zh.com.core.listener.AddOptionInPageListener;
import core.app.zh.com.core.listener.DaggerOptionListener;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;

/**
 * author : dayezi
 * data :2019/7/17
 * description:
 */
public class MyApplication extends BaseApplication {

    public static final String APP_ID = "wx7ff65b8874d14bec";

    @Inject
    String PGYID;

    @Override
    public void init(BaseApplication application) {
        RongIM.init(this);
        ToolBarInject.init(MyApplication.this, () -> new DefaultBean());
        RongIM.getInstance().setConversationClickListener(new MyConversationClickListener());
//        PgyCrashManager.register(this, PGYID);
    }

    @Override
    public DaggerOptionListener daggerOptionListener() {
        MyAppComponent component = DaggerMyAppComponent.builder().appModule(new AppModule(this)).build();
        component.inject(this);
        return () -> component;
    }

    private class MyConversationClickListener implements RongIM.ConversationClickListener {

        /**
         * 当点击用户头像后执行。
         *
         * @param context          上下文。
         * @param conversationType 会话类型。
         * @param user             被点击的用户的信息。
         * @param targetId         会话 id
         * @return 如果用户自己处理了点击后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式。
         */
        @Override
        public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo user, String targetId) {
            if (conversationType == Conversation.ConversationType.GROUP || conversationType == Conversation.ConversationType.PRIVATE) {
                FriendInfo friendInfo = new FriendInfo();
                friendInfo.setSourceId(Long.valueOf(user.getUserId()));
                friendInfo.setTargetId(Long.valueOf(user.getUserId()));
                ARouter.getInstance().build(FriendDetailActivity.AROUTER_PATH).withSerializable(FriendDetailActivity.FRIEND_DETAIL_KEY, friendInfo).navigation();
//                ARouter.getInstance().build(GroupMemberInfoActivity.AROUTER_PATH).withString(TARGET_KEY, targetId).withString(USERID_KEY, user.getUserId()).navigation();
//            } else if (conversationType == Conversation.ConversationType.PRIVATE) {
//                FriendInfo friendInfo = new FriendInfo();
//                friendInfo.setSourceId(Long.valueOf(user.getUserId()));
//                friendInfo.setTargetId(Long.valueOf(targetId));
//                ARouter.getInstance().build(FriendDetailActivity.AROUTER_PATH).withSerializable(FriendDetailActivity.FRIEND_DETAIL_KEY, friendInfo).navigation();
            }
            return true;
        }

        /**
         * 当长按用户头像后执行。
         *
         * @param context          上下文。
         * @param conversationType 会话类型。
         * @param user             被点击的用户的信息。
         * @param targetId         会话 id
         * @return 如果用户自己处理了点击后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式。
         */
        @Override
        public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType conversationType, UserInfo user, String targetId) {
            LogUtils.e(2222);
            return false;
        }

        /**
         * 当点击消息时执行。
         *
         * @param context 上下文。
         * @param view    触发点击的 View。
         * @param message 被点击的消息的实体信息。
         * @return 如果用户自己处理了点击后的逻辑处理，则返回 true， 否则返回 false, false 走融云默认处理方式。
         */
        @Override
        public boolean onMessageClick(Context context, View view, Message message) {
            LogUtils.e(333);
            return false;
        }

        /**
         * 当点击链接消息时执行。
         *
         * @param context 上下文。
         * @param link    被点击的链接。
         * @param message 被点击的消息的实体信息
         * @return 如果用户自己处理了点击后的逻辑处理，则返回 true， 否则返回 false, false 走融云默认处理方式。
         */
        @Override
        public boolean onMessageLinkClick(Context context, String link, Message message) {
            LogUtils.e(4444);
            return false;
        }

        /**
         * 当长按消息时执行。
         *
         * @param context 上下文。
         * @param view    触发点击的 View。
         * @param message 被长按的消息的实体信息。
         * @return 如果用户自己处理了长按后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式。
         */
        @Override
        public boolean onMessageLongClick(Context context, View view, Message message) {
            LogUtils.e(555);
            return false;
        }
    }

    @Override
    public List<AddOptionInPageListener> optionActivityList() {
        List<AddOptionInPageListener> listeners = new ArrayList<>();
        listeners.add((object, view) -> {
            ButterKnife.bind(object, view);
            ARouter.getInstance().inject(object);
//            if (object instanceof BaseActivity)
//                PgyCrashManager.register((Context) object,PGYID);
        });
        return listeners;
    }

    @Override
    public void unregister() {

    }
}
