package com.zh.xfz.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * author : dayezi
 * data :2019/12/17
 * description:
 */
@Entity
public class Tenant implements Serializable {
    private static final long serialVersionUID = 4359709211352400087L;
    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "userId")
    private Integer userId;
    @Property(nameInDb = "tenantId")
    private int tenantId;

    @Property(nameInDb = "tenantName")
    private String tenantName;
    @Property(nameInDb = "isFirst")
    private boolean isFirst;

    @Generated(hash = 1206600387)
    public Tenant(Long id, Integer userId, int tenantId, String tenantName,
                  boolean isFirst) {
        this.id = id;
        this.userId = userId;
        this.tenantId = tenantId;
        this.tenantName = tenantName;
        this.isFirst = isFirst;
    }

    @Generated(hash = 1915547482)
    public Tenant() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }


    public boolean getIsFirst() {
        return this.isFirst;
    }

    public void setIsFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
