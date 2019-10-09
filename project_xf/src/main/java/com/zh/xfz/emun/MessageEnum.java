package com.zh.xfz.emun;

/**
 * author : dayezi
 * data :2019/9/24
 * description:
 */
public enum MessageEnum {
    FRIEND_OPERATE("FriendOperate"),
    FRIEND_DELETE("FriendDelete"),
    APPLY_FRIEND("ApplyFriend");
    private String name;

    MessageEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
