package core.app.zh.com.core.listener.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import core.app.zh.com.core.R;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.listener.AppExitListener;

/**
 * author : dayezi
 * data :2019/4/15
 * description:
 */
public class BaseAppExitListener implements AppExitListener {
    private boolean isExit;
    private BaseActivity activity;

    public BaseAppExitListener(BaseActivity activity) {
        this.activity = activity;
    }

    @Override
    public void exit() {
        if (!isExit) {
            isExit = true;
            activity.showMsg(activity.getResources().getString(R.string.exit_toast_str));
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            for (Activity activity : ActivityLifecycleCallbackListener.sActivityList) {
                activity.finish();
            }
        }
    }

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

}
