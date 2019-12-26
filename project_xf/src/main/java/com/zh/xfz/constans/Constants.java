package com.zh.xfz.constans;

/**
 * author : dayezi
 * data :2019/9/9
 * description:通用常量封裝
 */
public class Constants {

    public final static String FLAG_STR = ",";
    public final static String PREF_KEY_UUID = "android.uuid.me";
    public final static String WX_CODE = "wx.code";

    public final static String[] SECTION_STR = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(",");

    public static final int REQUEST_PICK_IMAGE = 11101;//相册中选择

    public final static int CORP_CAMERA_IMAGE = 1002;//裁剪

    public final static int CONSULT_DOC_CAMERA = 1001;//拍照

    public final static String FILE_ROOT_PATH = "com.zh.xfz";
    public final static String FILE_IMAGE_TEMP_PATH = "temp";
    public final static String FILE_CRIP_IMAGE_NAME = "imageTemp.jpg";
    public final static String CRIP_IMAGE_SUFFIX = ".jpg";
    public final static String FILE_PROVIDER = "com.zh.xfz.FileProvider";
    public final static int PAGESIZE = 25;
    public final static int PAGEINDEX = 1;
    /**
     * ===== 计时begin =====
     */
    public final static int MILLIS_IN_FUTURE = 60000;
    public final static int COUNT_DOWN_INTERVAL = 1000;
    /** ===== 计时end =====*/

    /**
     * ===== 发送验证码状态 1.注册 2.找回 3.微信绑定 4.手机号换绑 begin =====
     **/
    public final static int SMS_STATUS_REGISTER_CODE = 1;
    public final static int SMS_STATUS_RETRIEVE_CODE = 2;
    public final static int SMS_STATUS_WX_BIND_CODE = 3;
    public final static int SMS_STATUS_REPLACE_PHONE_CODE = 4;
    /** ===== 发送验证码状态end =====*/

    /**
     * ===== 公司邀请用户begin =====
     **/
    public final static int COMPANY_STATUS_INVITATION_CODE = 1;//邀请
    public final static int COMPANY_STATUS_APPLY_CODE = 2;//申请
    /**
     * ===== 公司邀请用户公司end =====
     */

    /**
     * ==== 申请/邀请列表begin =====
     */
    public final static int APPLY_TYPE_BUSINESS = 1;
    public final static int APPLY_TYPE_USER = 2;
    /**
     * ==== 申请/邀请列表end =====
     */
    /**
     * 操作申请/邀请 加入公司
     */
    public final static int APPLY_TYPE_AGREE = 1;
    public final static int APPLY_TYPE_REFUSE = 2;

}