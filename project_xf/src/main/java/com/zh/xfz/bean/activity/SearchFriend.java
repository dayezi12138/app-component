package com.zh.xfz.bean.activity;

import java.io.Serializable;

/**
 * author : dayezi
 * data :2019/9/23
 * description:
 */
public class SearchFriend implements Serializable {
    /**
     * Mobile : 15958450429
     * ID : 15.0
     * UserIcon : null
     * ChineseName : null
     * Name : 15958450429
     */

    private String Mobile;
    private Integer ID;
    private String UserIcon;
    private String ChineseName;
    private String Name;

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUserIcon() {
        return UserIcon;
    }

    public void setUserIcon(String userIcon) {
        UserIcon = userIcon;
    }

    public String getChineseName() {
        return ChineseName;
    }

    public void setChineseName(String chineseName) {
        ChineseName = chineseName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
