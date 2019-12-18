package com.zh.xfz.utils;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;

/**
 * author : dayezi
 * data :2019/12/13
 * description:
 */
public class NotEmptyUtil {

    public static boolean isEmpty(String data, String toastMsg) {
        boolean isEmpty = StringUtils.isEmpty(data);
        if (isEmpty) ToastUtils.showShort(toastMsg);
        return isEmpty;
    }
}
