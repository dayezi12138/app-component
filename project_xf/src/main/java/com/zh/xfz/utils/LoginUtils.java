package com.zh.xfz.utils;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.zh.xfz.bean.activity.Account;

import static com.zh.xfz.constans.Constans.BIND_WX;
import static com.zh.xfz.constans.Constans.FLAG_STR;
import static com.zh.xfz.constans.Constans.IM_TOKEN;
import static com.zh.xfz.constans.Constans.USER_COMPANY;
import static com.zh.xfz.constans.Constans.USER_INFO;
import static com.zh.xfz.constans.Constans.USER_INFO_JSON_DATA_KEY;

/**
 * author : dayezi
 * data :2019/9/9
 * description:
 */
public class LoginUtils {
    public static Account ACCOUNT;
    public static Account.TenantBean TENANT;

    public static void saveLoginInfo(Account account) {
        SPUtils.getInstance().put(USER_INFO, account.getID() + FLAG_STR + account.getMobile());
        SPUtils.getInstance().put(IM_TOKEN, account.getToken());
        SPUtils.getInstance().put(USER_INFO_JSON_DATA_KEY, new Gson().toJson(account));
        if (account.getTenant() != null && !account.getTenant().isEmpty()) {
            SPUtils.getInstance().put(USER_COMPANY, new Gson().toJson(account.getTenant().get(0)));
            TENANT = account.getTenant().get(0);
        }
        SPUtils.getInstance().put(BIND_WX, StringUtils.isEmpty(account.getWXOpenID()) ? false : true);
    }

    public static void clearLoginInfo() {
        SPUtils.getInstance().remove(USER_INFO);
        SPUtils.getInstance().remove(IM_TOKEN);
        SPUtils.getInstance().remove(USER_INFO_JSON_DATA_KEY);
        SPUtils.getInstance().remove(USER_COMPANY);
        SPUtils.getInstance().remove(BIND_WX);
    }

    public static String getUserId() {
        return SPUtils.getInstance().getString(USER_INFO).split(FLAG_STR)[0];
    }

    public static Account getUserInfo() {
        if (ACCOUNT != null) return ACCOUNT;
        if (SPUtils.getInstance().contains(USER_INFO_JSON_DATA_KEY)) {
            Account userInfo = new Gson().fromJson(SPUtils.getInstance().getString(USER_INFO_JSON_DATA_KEY), Account.class);
            ACCOUNT = userInfo;
            return userInfo;
        }
        return null;
    }

    public static Account.TenantBean getTenant() {
        if (TENANT != null) {
            return TENANT;
        } else if (SPUtils.getInstance().contains(USER_COMPANY)) {
            TENANT = new Gson().fromJson(SPUtils.getInstance().getString(USER_COMPANY), Account.TenantBean.class);
            return TENANT;
        }
        return null;
    }

    public static void setTenant(Account.TenantBean tenantBean) {
        SPUtils.getInstance().put(USER_COMPANY, new Gson().toJson(tenantBean));
        TENANT = tenantBean;
    }

    public static boolean isIsBindWX() {
        return SPUtils.getInstance().contains(BIND_WX) ? SPUtils.getInstance().getBoolean(BIND_WX) : false;
    }

    public static void setBindWX(boolean isBind) {
        SPUtils.getInstance().put(BIND_WX, isBind);
    }
}
