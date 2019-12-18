package com.zh.xfz.utils.IM;

import android.annotation.SuppressLint;

import com.blankj.utilcode.util.ProcessUtils;
import com.blankj.utilcode.util.Utils;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * 融云工具类
 * author : dayezi
 * data :2019/9/9
 * description:
 */
public class IMUtils {

    /**
     * 连接
     * @param token 用户唯一标识
     * @param connectCallBack  结果回调接口
     */
    public static void connect(String token, IMConnectCallBack connectCallBack) {
        if (Utils.getApp().getApplicationInfo().packageName.equals(ProcessUtils.getForegroundProcessName())) {
            Observable.create((ObservableOnSubscribe<String>) emitter -> RongIM.connect(token, new RongIMClient.ConnectCallback() {

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
                    emitter.onNext(userid);
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    emitter.onError(new Throwable(errorCode.getMessage()));
                }
            })).subscribe(new Observer<String>() {
                @Override
                public void onSubscribe(Disposable d) {
                    if (d.isDisposed())
                        d.dispose();
                }

                @Override
                public void onNext(String s) {
                    connectCallBack.success(s);
                }

                @Override
                public void onError(Throwable e) {
                    connectCallBack.fail(e.getMessage());
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }

}
