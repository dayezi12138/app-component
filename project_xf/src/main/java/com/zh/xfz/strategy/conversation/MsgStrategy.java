package com.zh.xfz.strategy.conversation;

import com.zh.xfz.strategy.conversation.i.MsgOperateService;

import java.util.Hashtable;
import java.util.Map;

import io.rong.imlib.model.Message;

/**
 * author : dayezi
 * data :2019/12/16
 * description:
 */
public class MsgStrategy {
    private static Map<String, MsgOperateService> punishMap = new Hashtable<>();
    private static final MsgOperateService DEFAULT = new DEFAULT_SERVICE_IMPL();

    public static void put(String key, MsgOperateService msgOperateService) {
        punishMap.put(key, msgOperateService);
    }

    public static MsgOperateService get(String key) {
        if (!punishMap.containsKey(key)) return DEFAULT;
        return punishMap.get(key);
    }

    private static class DEFAULT_SERVICE_IMPL implements MsgOperateService {

        @Override
        public boolean operate(Message commandMessage) {
            return false;
        }
    }
}
