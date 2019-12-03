package com.zh.xfz.dagger.module.activity;

import com.zh.xfz.bean.adapter.ChildBean;
import com.zh.xfz.bean.adapter.GroupBean;

import java.util.ArrayList;
import java.util.List;

import core.app.zh.com.core.annotation.ActivityScope;
import dagger.Module;
import dagger.Provides;

/**
 * author : dayezi
 * data :2019/10/30
 * description:
 */
@Module
public class HelpItemModule {


    @ActivityScope
    @Provides
    public List<GroupBean> groupBeans() {
        List<GroupBean> groupBeans = new ArrayList<>();

        GroupBean groupBean = new GroupBean();
        groupBean.setGroupName("第1条规则");
        groupBeans.add(groupBean);

        GroupBean groupBean1 = new GroupBean();
        groupBean1.setGroupName("第2条规则");
        List<ChildBean> childBeans = new ArrayList<>();
        ChildBean bean1 = new ChildBean();
        bean1.setPosition(1);
        bean1.setName("这是第一条");
        childBeans.add(bean1);
        ChildBean bean2 = new ChildBean();
        bean2.setPosition(2);
        bean2.setName("这是第二条");
        childBeans.add(bean2);
        groupBean1.setChildBeans(childBeans);
        groupBeans.add(groupBean1);
        return groupBeans;
    }
}
