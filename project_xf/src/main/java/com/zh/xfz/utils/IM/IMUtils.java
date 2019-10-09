package com.zh.xfz.utils.IM;

import android.annotation.SuppressLint;

import com.blankj.utilcode.util.ProcessUtils;
import com.blankj.utilcode.util.Utils;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * author : dayezi
 * data :2019/9/9
 * description:
 */
public class IMUtils {

    public static void connect(String token, IMConnectCallBack connectCallBack) {
//        LoginUtils.clearLoginInfo();
        if (Utils.getApp().getApplicationInfo().packageName.equals(ProcessUtils.getForegroundProcessName())) {
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {

                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @SuppressLint("WrongConstant")
                @Override
                public void onSuccess(String userid) {
                    connectCallBack.success( userid);
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    connectCallBack.fail(errorCode.getMessage());
                }
            });
        }
    }

}