package com.zh.xfz.utils;

import android.text.TextUtils;

import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.SPUtils;

import java.util.UUID;

import static com.zh.xfz.constans.Constans.PREF_KEY_UUID;

/**
 * author : dayezi
 * data :2019/9/9
 * description:
 */
public class AndroidUtils {

    private static String getDeviceUUid() {
        String androidId = DeviceUtils.getAndroidID();
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) androidId.hashCode() << 32));
        return deviceUuid.toString();
    }

    private static String getAppUUid() {
        String uuid = SPUtils.getInstance().getString(PREF_KEY_UUID);
        if (TextUtils.isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
        }
        return uuid;
    }

    public static String getUUID() {
        String uuid = getDeviceUUid();
        if (TextUtils.isEmpty(uuid)) {
            uuid = getAppUUid();
        }
        return uuid;
    }
}
