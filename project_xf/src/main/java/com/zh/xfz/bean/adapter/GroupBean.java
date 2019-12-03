package com.zh.xfz.bean.adapter;

import com.hgdendi.expandablerecycleradapter.BaseExpandableRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author : dayezi
 * data :2019/10/30
 * description:
 */
public class GroupBean implements BaseExpandableRecyclerViewAdapter.BaseGroupBean<ChildBean> {

    private String groupName;
    private List<ChildBean> childBeans = new ArrayList<>();

    @Override
    public int getChildCount() {
        return childBeans.size();
    }

    @Override
    public ChildBean getChildAt(int childIndex) {
        return (childBeans == null || childBeans.size() <= childIndex) ? null : childBeans.get(childIndex);
    }

    @Override
    public boolean isExpandable() {
        return true;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<ChildBean> getChildBeans() {
        return childBeans;
    }

    public void setChildBeans(List<ChildBean> childBeans) {
        this.childBeans = childBeans;
    }
}
