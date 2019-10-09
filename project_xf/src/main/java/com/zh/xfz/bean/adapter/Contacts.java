package com.zh.xfz.bean.adapter;

import android.text.TextUtils;

import com.zh.xfz.R;

public class Contacts {

    public int resId;
    public String name;
    public String pinyin;
    public String header;
    public boolean top = false;


    public String getHeader() {
        if (this.header != null) return this.header;
        if (TextUtils.isEmpty(this.pinyin) || Character.isDigit(this.pinyin.charAt(0))) {
            this.header = null;
        } else {
            this.header = this.pinyin.substring(0, 1).toUpperCase();
            char temp = this.header.charAt(0);
            if (temp < 'A' || temp > 'Z') {
                this.header = "#";
            }
        }
        return this.header;
    }
}