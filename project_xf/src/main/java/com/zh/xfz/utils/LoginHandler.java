package com.zh.xfz.utils;

import com.blankj.utilcode.util.StringUtils;
import com.zh.xfz.bean.activity.Account;
import com.zh.xfz.db.BindWXDao;
import com.zh.xfz.db.DaoSession;
import com.zh.xfz.db.TenantDao;
import com.zh.xfz.db.bean.BindWX;
import com.zh.xfz.db.bean.Tenant;
import com.zh.xfz.db.bean.UserInfo;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * author : dayezi
 * data :2019/12/17
 * description:登录成功后处理类
 */
public class LoginHandler {
    @Inject
    DaoSession daoSession;

    @Inject
    public LoginHandler() {
    }

    public synchronized void saveLoginInfo(Account account) {
        clearLogin();
        saveUserInfo(account);
        saveTenant(account.getTenant(), account.getID());
        bindWX(account.getID(), account.getWXOpenID());
    }

    public synchronized void clearLogin() {
        daoSession.getBindWXDao().deleteAll();
        daoSession.getTenantDao().deleteAll();
        daoSession.getUserInfoDao().deleteAll();
    }

    public synchronized void saveBindWX(boolean isBind, Integer userId) {
        daoSession.getBindWXDao().deleteAll();
        BindWX bindWX = new BindWX();
        bindWX.setUserId(userId);
        bindWX.setBind(isBind);
        daoSession.getBindWXDao().save(bindWX);
    }

    public synchronized void updateUserInfo(UserInfo userInfo) {
        daoSession.getUserInfoDao().update(userInfo);
        daoSession.clear();
    }

    public synchronized void updateTenant(Tenant first, Tenant second) {
        first.setIsFirst(false);
        second.setIsFirst(true);
        daoSession.getTenantDao().updateInTx(first, second);
        daoSession.clear();
    }

    public boolean isBindWX() {
        List<BindWX> bindWX = daoSession.getBindWXDao().queryBuilder().where(BindWXDao.Properties.UserId.eq(getCurrentUserInfo().getUserId())).build().list();
        if (bindWX == null || bindWX.isEmpty()) throw new RuntimeException("bindWX is empty");
        return bindWX.get(0).isBind();
    }

    public List<Tenant> getTenantList() {
        return daoSession.getTenantDao().queryBuilder().where(TenantDao.Properties.UserId.eq(getCurrentUserInfo().getUserId())).list();
    }

    public Tenant getCurrentTenant() {
        List<Tenant> list = daoSession.getTenantDao().queryBuilder().where(daoSession.getTenantDao().queryBuilder()
                .and(TenantDao.Properties.UserId.eq(getCurrentUserInfo().getUserId()), TenantDao.Properties.IsFirst.eq(true))).list();
        if (list == null || list.isEmpty()) return null;
        return list.get(0);
    }

    public UserInfo getCurrentUserInfo() {
        List<UserInfo> userInfoList = daoSession.getUserInfoDao().loadAll();
        if (userInfoList == null || userInfoList.size() == 0)
            throw new RuntimeException("user is empty");
        return userInfoList.get(0);
    }

    public Integer getCurrentUserId() {
        List<UserInfo> userInfoList = daoSession.getUserInfoDao().loadAll();
        if (userInfoList == null || userInfoList.size() == 0)
            throw new RuntimeException("user is empty");
        return userInfoList.get(0).getUserId();
    }

    private void bindWX(Integer userId, String wxOpenId) {
        daoSession.getBindWXDao().deleteAll();
        BindWX bindWX = new BindWX();
        bindWX.setBind(StringUtils.isEmpty(wxOpenId) ? false : true);
        bindWX.setUserId(userId);
        daoSession.getBindWXDao().save(bindWX);
    }

    private void saveUserInfo(Account account) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(account.getID());
        userInfo.setChineseName(account.getChineseName());
        userInfo.setMobile(account.getMobile());
        userInfo.setUserIcon(account.getUserIcon());
        userInfo.setWXOpenID(account.getWXOpenID());
        userInfo.setToken(account.getToken());
        userInfo.setOpenid(account.getOpenid());
        userInfo.setUnionid(account.getUnionid());
        userInfo.setAccessToken(account.getAccess_token());
        userInfo.setCreateTime(new Date());
        daoSession.getUserInfoDao().save(userInfo);
    }

    private void saveTenant(List<Account.TenantBean> tenantList, Integer userId) {
        for (int i = 0, j = tenantList.size(); i < j; i++) {
            Account.TenantBean tenantBean = tenantList.get(i);
            Tenant tenant = new Tenant();
            tenant.setUserId(userId);
            tenant.setTenantId(tenantBean.getID());
            tenant.setTenantName(tenantBean.getTenantName());
            if (i == 0) tenant.setIsFirst(true);
            daoSession.getTenantDao().save(tenant);
        }
    }
}
