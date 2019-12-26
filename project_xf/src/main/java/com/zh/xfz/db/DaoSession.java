package com.zh.xfz.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.zh.xfz.db.bean.BindWX;
import com.zh.xfz.db.bean.Tenant;
import com.zh.xfz.db.bean.UserInfo;

import com.zh.xfz.db.BindWXDao;
import com.zh.xfz.db.TenantDao;
import com.zh.xfz.db.UserInfoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig bindWXDaoConfig;
    private final DaoConfig tenantDaoConfig;
    private final DaoConfig userInfoDaoConfig;

    private final BindWXDao bindWXDao;
    private final TenantDao tenantDao;
    private final UserInfoDao userInfoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        bindWXDaoConfig = daoConfigMap.get(BindWXDao.class).clone();
        bindWXDaoConfig.initIdentityScope(type);

        tenantDaoConfig = daoConfigMap.get(TenantDao.class).clone();
        tenantDaoConfig.initIdentityScope(type);

        userInfoDaoConfig = daoConfigMap.get(UserInfoDao.class).clone();
        userInfoDaoConfig.initIdentityScope(type);

        bindWXDao = new BindWXDao(bindWXDaoConfig, this);
        tenantDao = new TenantDao(tenantDaoConfig, this);
        userInfoDao = new UserInfoDao(userInfoDaoConfig, this);

        registerDao(BindWX.class, bindWXDao);
        registerDao(Tenant.class, tenantDao);
        registerDao(UserInfo.class, userInfoDao);
    }
    
    public void clear() {
        bindWXDaoConfig.clearIdentityScope();
        tenantDaoConfig.clearIdentityScope();
        userInfoDaoConfig.clearIdentityScope();
    }

    public BindWXDao getBindWXDao() {
        return bindWXDao;
    }

    public TenantDao getTenantDao() {
        return tenantDao;
    }

    public UserInfoDao getUserInfoDao() {
        return userInfoDao;
    }

}
