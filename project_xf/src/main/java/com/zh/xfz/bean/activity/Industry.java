package com.zh.xfz.bean.activity;

/**
 * author : dayezi
 * data :2019/9/9
 * description:行业类别
 */
public class Industry implements Comparable<Industry> {
    /**
     * ID : 1
     * Name : 五金机械
     * SortOrder : 1
     */

    private int ID;
    private String Name;
    private int SortOrder;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getSortOrder() {
        return SortOrder;
    }

    public void setSortOrder(int SortOrder) {
        this.SortOrder = SortOrder;
    }

    @Override
    public int compareTo(Industry industry) {
        return this.getSortOrder() - industry.getSortOrder();
    }
}
