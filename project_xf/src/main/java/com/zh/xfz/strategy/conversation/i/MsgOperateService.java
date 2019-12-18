package com.zh.xfz.strategy.conversation.i;

import io.rong.imlib.model.Message;

/**
 * author : dayezi
 * data :2019/12/16
 * description:
 */
public interface MsgOperateService {
    boolean operate(Message message);
}
