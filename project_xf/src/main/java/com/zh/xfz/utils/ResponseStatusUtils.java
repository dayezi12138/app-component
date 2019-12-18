package com.zh.xfz.utils;

import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.zh.xfz.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author : dayezi
 * data :2019/12/13
 * description:
 */
public class ResponseStatusUtils {

    /*** =====此处状态都认为是成功 begin===== ***/
    private final static int STATUS_SUCCESS_NORMAL = 0;//一般成功返回code
    private final static int STATUS_SUCCESS_CAN_REGISTER = 40003;//注册
    /*** =====end===== ***/

    /*** =====此处状态都认为是成功 begin===== ***/
    private final static int STATUS_FAIL_PASSWORD = 40004;
    /*** =====end===== ***/
    private static final ArrayList<Integer> statusSuccessList;
    private static final ArrayList<Integer> statusFailList;

    static {
        statusSuccessList = new ArrayList<>();
        statusFailList = new ArrayList<>();

        statusSuccessList.add(STATUS_SUCCESS_CAN_REGISTER);

//        statusFailList.add(STATUS_FAIL_PASSWORD);
    }

    public static boolean isNormalSuccess(int code) {
        return code == STATUS_SUCCESS_NORMAL;
    }

    public static boolean isSpecialSuccess(int code) {
        return temp(statusSuccessList, code);
    }

    public static boolean fail(int code, String toastMsg) {
        boolean isFail = temp(statusFailList, code);
        String msg = isFail ? toastMsg : Utils.getApp().getResources().getString(R.string.act_unknown_response_toast_msg);
        ToastUtils.showShort(msg);
        return isFail;
    }

    public static boolean notMsgFail(int code) {
        return temp(statusFailList, code);
    }

    private static boolean temp(List<Integer> list, int code) {
        boolean is = false;
        for (Integer status : list) {
            if (status == code) {
                is = true;
                break;
            }
        }
        return is;
    }
}
