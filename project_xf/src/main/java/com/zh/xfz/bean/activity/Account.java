package com.zh.xfz.bean.activity;

import java.util.List;

/**
 * author : dayezi
 * data :2019/7/25
 * description:
 */
public class Account {
    /**
     * ID : 15
     * ChineseName : null
     * Mobile : 15958450429
     * UserIcon : null
     * VIPLevel : 0
     * KeepLoginDays : 0
     * LastLoginTenantID : null
     * Points : 0
     * TimeStamp : 13232313
     * WXOpenID : null
     * Token : 1LzcQKH99awxLkQSvyIOgE0dtnb7HAC3P8uFDrutnV1zKHnqZMAo/3Xd8/pPgvFkDxlrq5MB7NplA+kdN93+hQ==
     * Tenant : [{"ID":38,"TenantName":"ghh","TenantType":null,"AdminUserID":15,"UserNumber":5,"DBSource":"47.103.75.23","DBCatalog":"B001","DBName":"BizTest001","DBPassword":"Jtb6oQ8HBu","Status":0,"IndustryIDs":null,"CreateTime":"2019-09-10T09:05:41.853"}]
     */

    private int ID;
    private String ChineseName;
    private String Mobile;
    private String UserIcon;
    private int VIPLevel;
    private int KeepLoginDays;
    private Object LastLoginTenantID;
    private int Points;
    private String TimeStamp;
    private Object WXOpenID;
    private String Token;
    private List<TenantBean> Tenant;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getChineseName() {
        return ChineseName;
    }

    public void setChineseName(String chineseName) {
        ChineseName = chineseName;
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

    public int getVIPLevel() {
        return VIPLevel;
    }

    public void setVIPLevel(int VIPLevel) {
        this.VIPLevel = VIPLevel;
    }

    public int getKeepLoginDays() {
        return KeepLoginDays;
    }

    public void setKeepLoginDays(int KeepLoginDays) {
        this.KeepLoginDays = KeepLoginDays;
    }

    public Object getLastLoginTenantID() {
        return LastLoginTenantID;
    }

    public void setLastLoginTenantID(Object LastLoginTenantID) {
        this.LastLoginTenantID = LastLoginTenantID;
    }

    public int getPoints() {
        return Points;
    }

    public void setPoints(int Points) {
        this.Points = Points;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String TimeStamp) {
        this.TimeStamp = TimeStamp;
    }

    public Object getWXOpenID() {
        return WXOpenID;
    }

    public void setWXOpenID(Object WXOpenID) {
        this.WXOpenID = WXOpenID;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public List<TenantBean> getTenant() {
        return Tenant;
    }

    public void setTenant(List<TenantBean> Tenant) {
        this.Tenant = Tenant;
    }

    public static class TenantBean {
        /**
         * ID : 38
         * TenantName : ghh
         * TenantType : null
         * AdminUserID : 15
         * UserNumber : 5
         * DBSource : 47.103.75.23
         * DBCatalog : B001
         * DBName : BizTest001
         * DBPassword : Jtb6oQ8HBu
         * Status : 0
         * IndustryIDs : null
         * CreateTime : 2019-09-10T09:05:41.853
         */

        private int ID;
        private String TenantName;
        private Object TenantType;
        private int AdminUserID;
        private int UserNumber;
        private String DBSource;
        private String DBCatalog;
        private String DBName;
        private String DBPassword;
        private int Status;
        private Object IndustryIDs;
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

        public Object getTenantType() {
            return TenantType;
        }

        public void setTenantType(Object TenantType) {
            this.TenantType = TenantType;
        }

        public int getAdminUserID() {
            return AdminUserID;
        }

        public void setAdminUserID(int AdminUserID) {
            this.AdminUserID = AdminUserID;
        }

        public int getUserNumber() {
            return UserNumber;
        }

        public void setUserNumber(int UserNumber) {
            this.UserNumber = UserNumber;
        }

        public String getDBSource() {
            return DBSource;
        }

        public void setDBSource(String DBSource) {
            this.DBSource = DBSource;
        }

        public String getDBCatalog() {
            return DBCatalog;
        }

        public void setDBCatalog(String DBCatalog) {
            this.DBCatalog = DBCatalog;
        }

        public String getDBName() {
            return DBName;
        }

        public void setDBName(String DBName) {
            this.DBName = DBName;
        }

        public String getDBPassword() {
            return DBPassword;
        }

        public void setDBPassword(String DBPassword) {
            this.DBPassword = DBPassword;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public Object getIndustryIDs() {
            return IndustryIDs;
        }

        public void setIndustryIDs(Object IndustryIDs) {
            this.IndustryIDs = IndustryIDs;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }
    }

}
