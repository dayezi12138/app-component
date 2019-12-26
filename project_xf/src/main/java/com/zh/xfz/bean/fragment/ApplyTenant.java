package com.zh.xfz.bean.fragment;

/**
 * author : dayezi
 * data :2019/12/24
 * description:
 */
public class ApplyTenant {
    /**
     * UserID : 16
     * Stutus : 1
     * TenantID : 83
     * ChineseName : 郑宇恒
     * TenantName : 阿瑟费有限公司
     * ID : 6
     * CreateTime : 2019-12-23T15:12:02.86
     */

    private int UserID;
    private int Stutus;
    private int TenantID;
    private String ChineseName;
    private String TenantName;
    private int ID;
    private String CreateTime;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getStutus() {
        return Stutus;
    }

    public void setStutus(int Stutus) {
        this.Stutus = Stutus;
    }

    public int getTenantID() {
        return TenantID;
    }

    public void setTenantID(int TenantID) {
        this.TenantID = TenantID;
    }

    public String getChineseName() {
        return ChineseName;
    }

    public void setChineseName(String ChineseName) {
        this.ChineseName = ChineseName;
    }

    public String getTenantName() {
        return TenantName;
    }

    public void setTenantName(String TenantName) {
        this.TenantName = TenantName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }
}
