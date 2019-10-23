package com.zh.xfz.bean.fragment;

import android.text.TextUtils;

import com.zh.xfz.views.sideBar.PinyinUtils;

import java.io.Serializable;

/**
 * author : dayezi
 * data :2019/9/11
 * description:
 */
public class FriendInfo implements Serializable {
    /**
     * ID : 1.0
     * SourceId : 16.0
     * TargetId : 15.0
     * Mobile : 18867938354
     * Token : f68NY+yx7crk/EYuY9l1usPJvz8eFu2Z3NCyIczRlMAzQrk6Ea6dFZB6IAFJhCcsf2N14zWOKoGp6Eqqlkrj5g==
     * Status : 0.0
     * Remark : 123
     * Name : 18867938354
     */

    private Long ID;
    private Long SourceId;
    private Long TargetId;
    private String Mobile;
    private String Token;
    private int Status;
    private String RemarkName;
    private String Name;
    private String letters;
    private String path;
    private String UserIcon;

    public String getRemarkName() {
        return RemarkName;
    }

    public void setRemarkName(String remarkName) {
        RemarkName = remarkName;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getSourceId() {
        return SourceId;
    }

    public void setSourceId(Long sourceId) {
        SourceId = sourceId;
    }

    public Long getTargetId() {
        return TargetId;
    }

    public void setTargetId(Long targetId) {
        TargetId = targetId;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

//    public String getRemark() {
//        return Remark;
//    }
//
//    public void setRemark(String remark) {
//        Remark = remark;
//    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLetters() {
//        if (!TextUtils.isEmpty(letters)) return letters;
        String pinyin = PinyinUtils.getPingYin(getName());
        String sortString = pinyin.substring(0, 1).toUpperCase();
        // 正则表达式，判断首字母是否是英文字母
        if (sortString.matches("[A-Z]")) {
            return letters = !TextUtils.isEmpty(letters) ? letters : sortString.toUpperCase();
        } else {
            return letters = "#";
        }
    }

    public String getUserIcon() {
        return UserIcon;
    }

    public void setUserIcon(String userIcon) {
        UserIcon = userIcon;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
