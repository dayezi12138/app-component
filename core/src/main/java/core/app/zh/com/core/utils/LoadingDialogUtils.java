package core.app.zh.com.core.utils;

import android.app.Dialog;
import android.content.Context;

import com.android.tu.loadingdialog.LoadingDailog;
import com.blankj.utilcode.util.LogUtils;

import java.util.HashMap;
import java.util.Map;

import core.app.zh.com.core.R;

/**
 * author : dayezi
 * data :2019/7/1
 * description:
 */
public class LoadingDialogUtils {

    private static Map<String, Dialog> dialogMap = new HashMap<>();

    public static Dialog getDialog(Context context) {
        LogUtils.e(context.getClass().getName());
        Dialog dialog;
        if (dialogMap.containsKey(context.getClass().getName())) {
            dialog = dialogMap.get(context.getClass().getName());
        } else {
            dialog = defaultDialog(context);
            dialogMap.put(context.getClass().getName(), dialog);
        }
        return dialog;
    }

    private static Dialog defaultDialog(Context context) {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(context)
                .setMessage(context.getResources().getString(R.string.loading_msg))
                .setCancelable(false)
                .setCancelOutside(false);
        return loadBuilder.create();
    }
}
