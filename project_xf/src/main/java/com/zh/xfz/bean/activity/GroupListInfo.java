package com.zh.xfz.bean.activity;

/**
 * author : dayezi
 * data :2019/9/25
 * description:
 */
public class GroupListInfo {
    /**
     * CreateTime : 2019-09-24T16:37:17.257
     * AdminUserId : 16
     * Type : 1
     * TargetId : null
     * ID : 10001
     * GroupName : 18867938354、15958450429
     */

    private String CreateTime;
    private int AdminUserId;
    private int Type;
    private String TargetId;
    private int ID;
    private String GroupName;

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public int getAdminUserId() {
        return AdminUserId;
    }

    public void setAdminUserId(int AdminUserId) {
        this.AdminUserId = AdminUserId;
    }

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    public String getTargetId() {
        return TargetId;
    }

    public void setTargetId(String targetId) {
        TargetId = targetId;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String GroupName) {
        this.GroupName = GroupName;
    }
}
