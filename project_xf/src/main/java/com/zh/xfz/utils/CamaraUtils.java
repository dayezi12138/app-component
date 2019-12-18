package com.zh.xfz.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import com.zh.xfz.constans.Constants;

/**
 * author : dayezi
 * data :2019/12/17
 * description:相机相关
 */
public class CamaraUtils {
    private final static String OPEN_CAMARA_TYPE = "image/*";//打开相机

    /**
     * 打开相机
     *
     * @param activity
     */
    public static void openCamara(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            activity.startActivityForResult(new Intent(Intent.ACTION_GET_CONTENT).setType(OPEN_CAMARA_TYPE), Constants.REQUEST_PICK_IMAGE);
        } else {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType(OPEN_CAMARA_TYPE);
            activity.startActivityForResult(intent, Constants.REQUEST_PICK_IMAGE);
        }
    }

    /**
     * 裁剪图片
     *
     * @param uri
     * @param activity
     */
    public static void cripPic(Uri uri, Activity activity) {
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, OPEN_CAMARA_TYPE);
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, Constants.CORP_CAMERA_IMAGE);
    }
}
