package com.zh.xfz.api;

import core.app.zh.com.core.annotation.ApiAnnotation;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * author : dayezi
 * data :2019/12/10
 * description:
 */
public interface UpLoadService {

    @ApiAnnotation("上传图片")
    @POST("UpLoad/UploadImg")
    @Multipart
    Call<ResponseBody> uploadImg(@Part MultipartBody.Part part);
}
