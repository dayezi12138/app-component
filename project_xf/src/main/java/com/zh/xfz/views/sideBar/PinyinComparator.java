package com.zh.xfz.views.sideBar;

import com.zh.xfz.bean.fragment.FriendInfo;

import java.util.Comparator;

public class PinyinComparator implements Comparator<FriendInfo> {

    public int compare(FriendInfo o1, FriendInfo o2) {
        if (o1.getLetters().equals("@")
                || o2.getLetters().equals("#")) {
            return 1;
        } else if (o1.getLetters().equals("#")
                || o2.getLetters().equals("@")) {
            return -1;
        } else {
            return o1.getLetters().compareTo(o2.getLetters());
        }
    }

}