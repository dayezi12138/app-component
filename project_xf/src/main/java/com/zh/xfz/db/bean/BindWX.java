package com.zh.xfz.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author : dayezi
 * data :2019/12/18
 * description:
 */
@Entity
public class BindWX {
    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "userId")
    private Integer userId;
    @Property(nameInDb = "bind")
    private boolean bind;

    @Generated(hash = 1883245471)
    public BindWX(Long id, Integer userId, boolean bind) {
        this.id = id;
        this.userId = userId;
        this.bind = bind;
    }

    @Generated(hash = 88815823)
    public BindWX() {
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

    public boolean isBind() {
        return bind;
    }

    public void setBind(boolean bind) {
        this.bind = bind;
    }

    public boolean getBind() {
        return this.bind;
    }
}
