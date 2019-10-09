package com.zh.xfz.utils;

import com.blankj.utilcode.util.SPUtils;
import com.zh.xfz.bean.activity.Account;

import static com.zh.xfz.constans.Constans.FLAG_STR;
import static com.zh.xfz.constans.Constans.IM_TOKEN;
import static com.zh.xfz.constans.Constans.USER_INFO;

/**
 * author : dayezi
 * data :2019/9/9
 * description:
 */
public class LoginUtils {
    public static Account ACCOUNT;

    public static void saveLoginInfo(Account account) {
        SPUtils.getInstance().put(USER_INFO, account.getID() + FLAG_STR + account.getMobile());
        SPUtils.getInstance().put(IM_TOKEN, account.getToken());
    }

    public static void clearLoginInfo() {
        SPUtils.getInstance().remove(USER_INFO);
        SPUtils.getInstance().remove(IM_TOKEN);
    }

    public static String getUserId() {
        return SPUtils.getInstance().getString(USER_INFO).split(FLAG_STR)[0];
    }
}
