package com.zh.xfz.bean.other;

import java.util.List;

/**
 * author : dayezi
 * data :2019/12/25
 * description:
 */
public class HelpData<T> {
    private int Total;
    private List<T> Data;

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public List<T> getData() {
        return Data;
    }

    public void setData(List<T> data) {
        Data = data;
    }
}
