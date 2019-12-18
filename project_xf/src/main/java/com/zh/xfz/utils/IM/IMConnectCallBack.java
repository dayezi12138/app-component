package com.zh.xfz.utils.IM;

/**
 * author : dayezi
 * data :2019/9/9
 * description:融云登录回调接口
 */
public interface IMConnectCallBack {

    void success(String userid);

    void fail(String msg);
}
