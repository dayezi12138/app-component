package com.zh.xfz.bean.other;

/**
 * author : dayezi
 * data :2019/7/24
 * description:
 */
public class Data<T> {
    private String Msg;
    private int Code;
    private int ID;
    private T Res;

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public T getRes() {
        return Res;
    }

    public void setRes(T res) {
        Res = res;
    }
}
