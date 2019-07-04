package zh.com.jyu.utils;

import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;

/**
 * author : dayezi
 * data :2019/5/9
 * description:
 */
public class MyTextUtils {

    /**
     * 多个字符串验证非空
     *
     * @param res
     * @return
     */
    public static boolean isEmpty(String... res) {
        boolean isEmpty = false;
        for (String str : res) {
            if (TextUtils.isEmpty(str)) {
                isEmpty = true;
                break;
            }
        }
        return isEmpty;
    }

    /**
     * 多个字符串验证非空，并且显示错误信息
     *
     * @param res
     * @return
     */
    public static boolean showValidMsg(int resId, String... res) {
        boolean valid = isEmpty(res);
        if (valid) ToastUtils.showShort(resId);
        return valid;
    }
}
