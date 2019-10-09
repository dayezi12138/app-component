package com.zh.xfz.bean.activity;

/**
 * author : dayezi
 * data :2019/7/24
 * description:
 */
public class ValidAccount {

    /**
     * Msg : 手机号不存在,可以注册
     * Code : 40003
     * Account : 13758920517
     */

    private String Msg;
    private int Code;
    private String Account;
    private boolean Flag;

    public boolean isFlag() {
        return Flag;
    }

    public void setFlag(boolean flag) {
        Flag = flag;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String Account) {
        this.Account = Account;
    }
}
