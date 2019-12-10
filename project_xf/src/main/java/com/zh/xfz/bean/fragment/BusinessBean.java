package com.zh.xfz.bean.fragment;

/**
 * author : dayezi
 * data :2019/12/9
 * description:
 */
public class BusinessBean {
    /**
     * ID : 77.0
     * TenantName : Company1
     * AdminUserID : 16.0
     * CreateTime : 2019-12-09T10:39:50.133
     */

    private int ID;
    private String TenantName;
    private int AdminUserID;
    private String CreateTime;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenantName() {
        return TenantName;
    }

    public void setTenantName(String TenantName) {
        this.TenantName = TenantName;
    }

    public int getAdminUserID() {
        return AdminUserID;
    }

    public void setAdminUserID(int AdminUserID) {
        this.AdminUserID = AdminUserID;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }
}
