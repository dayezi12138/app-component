package com.zh.xfz.bean.adapter;

/**
 * author : dayezi
 * data :2019/10/30
 * description:
 */
public class ChildBean implements Comparable<ChildBean> {
    private int position;
    private String name;

    @Override
    public int compareTo(ChildBean o) {
        return this.position - o.getPosition();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
