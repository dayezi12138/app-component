package com.zh.xfz.strategy.conversation.impl;

import com.blankj.utilcode.util.Utils;
import com.zh.xfz.R;
import com.zh.xfz.strategy.conversation.i.MsgOperateService;

import org.greenrobot.eventbus.EventBus;

import core.app.zh.com.core.bean.MessageEvent;
import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;

import static com.zh.xfz.business.fragment.ContactFragment.CONTACT_EVENT_KEY;

/**
 * author : dayezi
 * data :2019/12/16
 * description:
 */
public class FriendOperateStrategyImpl implements MsgOperateService {

    @Override
    public boolean operate(Message commandMessage) {
        TextMessage informationNotificationMessage = TextMessage.obtain(Utils.getApp().getResources().getString(R.string.rong_tip_agree_apply_friend_msg));
        Message myMessage = Message.obtain(commandMessage.getTargetId(), Conversation.ConversationType.PRIVATE, informationNotificationMessage);
        RongIM.getInstance().sendMessage(myMessage, null, null, (IRongCallback.ISendMessageCallback) null);
        MessageEvent messageEvent = new MessageEvent(CONTACT_EVENT_KEY, "");
        EventBus.getDefault().post(messageEvent);
        return false;
    }
}
