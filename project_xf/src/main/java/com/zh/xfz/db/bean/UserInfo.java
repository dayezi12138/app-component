package com.zh.xfz.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;

/**
 * author : dayezi
 * data :2019/12/17
 * description:
 */
@Entity
public class UserInfo {
    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "userId")
    private Integer userId;

    @Property(nameInDb = "chineseName")
    private String chineseName;

    @Property(nameInDb = "mobile")
    private String mobile;

    @Property(nameInDb = "userIcon")
    private String userIcon;

    @Property(nameInDb = "wXOpenID")
    private String wXOpenID;

    @Property(nameInDb = "token")
    private String token;

    @Property(nameInDb = "openid")
    private String openid;

    @Property(nameInDb = "unionid")
    private String unionid;

    @Property(nameInDb = "accessToken")
    private String accessToken;

    @Property(nameInDb = "createTime")
    private Date createTime;

    @Generated(hash = 1577948553)
    public UserInfo(Long id, Integer userId, String chineseName, String mobile,
            String userIcon, String wXOpenID, String token, String openid,
            String unionid, String accessToken, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.chineseName = chineseName;
        this.mobile = mobile;
        this.userIcon = userIcon;
        this.wXOpenID = wXOpenID;
        this.token = token;
        this.openid = openid;
        this.unionid = unionid;
        this.accessToken = accessToken;
        this.createTime = createTime;
    }

    @Generated(hash = 1279772520)
    public UserInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getWXOpenID() {
        return this.wXOpenID;
    }

    public void setWXOpenID(String wXOpenID) {
        this.wXOpenID = wXOpenID;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
