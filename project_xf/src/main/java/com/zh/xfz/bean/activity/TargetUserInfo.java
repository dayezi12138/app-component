package com.zh.xfz.bean.activity;

/**
 * author : dayezi
 * data :2019/9/26
 * description:
 */
public class TargetUserInfo {
    /**
     * ID : 16
     * Status : true
     * Mobile : 18867938354
     * UserIcon : null
     */

    private int ID;
    private boolean Status;
    private String Mobile;
    private String UserIcon;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getUserIcon() {
        return UserIcon;
    }

    public void setUserIcon(String userIcon) {
        UserIcon = userIcon;
    }
}
