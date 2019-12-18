package com.zh.xfz.strategy.conversation.impl;

import com.zh.xfz.business.fragment.ContactFragment;
import com.zh.xfz.strategy.conversation.i.MsgOperateService;

import org.greenrobot.eventbus.EventBus;

import core.app.zh.com.core.bean.MessageEvent;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;

/**
 * author : dayezi
 * data :2019/12/16
 * description:
 */
public class FriendDeleteStrategyImpl implements MsgOperateService {
    @Override
    public boolean operate(Message message) {
        MessageEvent event = new MessageEvent(ContactFragment.CONTACT_EVENT_KEY, "");
        EventBus.getDefault().post(event);
        RongIM.getInstance().removeConversation(Conversation.ConversationType.PRIVATE, message.getTargetId(), null);
        return false;
    }
}
