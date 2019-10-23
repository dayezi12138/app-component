package com.zh.xfz.views.sideBar;

import com.zh.xfz.bean.fragment.FriendInfo;

import java.util.Comparator;

public class PinyinComparator implements Comparator<FriendInfo> {

    public int compare(FriendInfo o1, FriendInfo o2) {
        if (o1.getLetters().equals("☆") && o2.getLetters().equals("☆")) {
            return Integer.MAX_VALUE;
        } else if (o1.getLetters().equals("#") && o2.getLetters().equals("#")) {
            return -2;
        } else {
            if (o2.getLetters().equals("☆")) return Integer.MAX_VALUE;
            return letterToNumber(o1.getLetters()) - letterToNumber(o2.getLetters());
        }
    }

    //字母转数字  A-Z ：1-26
    public int letterToNumber(String letter) {
        int length = letter.length();
        int num = 0;
        int number = 0;
        for (int i = 0; i < length; i++) {
            char ch = letter.charAt(length - i - 1);
            num = (int) (ch - 'A' + 1);
            num *= Math.pow(26, i);
            number += num;
        }
        return number;
    }
}