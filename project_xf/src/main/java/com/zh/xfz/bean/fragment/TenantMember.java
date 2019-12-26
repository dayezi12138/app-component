package com.zh.xfz.bean.fragment;

/**
 * author : dayezi
 * data :2019/12/24
 * description:
 */
public class TenantMember {
    /**
     * UserId : 10015.0
     * UserIcon : http://47.103.75.23:8031/UpLoad/201912/56f08c4e-c7ae-4625-a01b-79adedfa030e.jpg
     * ChineseName : 15958450429
     * Mobile : 15958450429
     */

    private double UserId;
    private String UserIcon;
    private String ChineseName;
    private String Mobile;

    public double getUserId() {
        return UserId;
    }

    public void setUserId(double UserId) {
        this.UserId = UserId;
    }

    public String getUserIcon() {
        return UserIcon;
    }

    public void setUserIcon(String UserIcon) {
        this.UserIcon = UserIcon;
    }

    public String getChineseName() {
        return ChineseName;
    }

    public void setChineseName(String ChineseName) {
        this.ChineseName = ChineseName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }
}
