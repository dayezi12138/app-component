package com.zh.xfz.bean.activity;

/**
 * author : dayezi
 * data :2019/9/25
 * description:
 */
public class GroupInfo {
    /**
     * ID : 34
     * GroupId : 10001
     * TargetId : 16
     * UserIcon : null
     * ChineseName : 18867938354
     * RemarkName : 18867938354
     */

    private int ID;
    private int GroupId;
    private int TargetId;
    private String UserIcon = "";
    private String ChineseName = "";
    private String RemarkName = "";

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getGroupId() {
        return GroupId;
    }

    public void setGroupId(int GroupId) {
        this.GroupId = GroupId;
    }

    public int getTargetId() {
        return TargetId;
    }

    public void setTargetId(int TargetId) {
        this.TargetId = TargetId;
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

    public void setChineseName(String ChineseName) {
        this.ChineseName = ChineseName;
    }

    public String getRemarkName() {
        return RemarkName;
    }

    public void setRemarkName(String RemarkName) {
        this.RemarkName = RemarkName;
    }
}
