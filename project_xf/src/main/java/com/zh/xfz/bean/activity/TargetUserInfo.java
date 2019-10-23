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
    private String ChineseName;
    private String RemarkName;
    private int TargetId;

    public String getChineseName() {
        return ChineseName;
    }

    public void setChineseName(String chineseName) {
        ChineseName = chineseName;
    }

    public String getRemarkName() {
        return RemarkName;
    }

    public void setRemarkName(String remarkName) {
        RemarkName = remarkName;
    }

    public int getTargetId() {
        return TargetId;
    }

    public void setTargetId(int targetId) {
        TargetId = targetId;
    }

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
