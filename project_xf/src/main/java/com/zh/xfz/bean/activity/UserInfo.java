package com.zh.xfz.bean.activity;

/**
 * author : dayezi
 * data :2019/9/9
 * description:
 */
public class UserInfo {
    private int ID;
    private String Mobile;
    private String UserIcon;
    private String VIPLevel;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getUserIcon() {
        return UserIcon;
    }

    public void setUserIcon(String userIcon) {
        UserIcon = userIcon;
    }

    public String getVIPLevel() {
        return VIPLevel;
    }

    public void setVIPLevel(String VIPLevel) {
        this.VIPLevel = VIPLevel;
    }

    public String getPoints() {
        return Points;
    }

    public void setPoints(String points) {
        Points = points;
    }

    private String Points;
}
