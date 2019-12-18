package com.zh.xfz.business.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ImageUtils;
import com.shehuan.niv.NiceImageView;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.constans.Constants;
import com.zh.xfz.mvp.presenter.UserPresenter;
import com.zh.xfz.utils.CamaraUtils;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.annotation.NeedPermission;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.view.MyPopupWindow;

import static com.zh.xfz.constans.Constants.CRIP_IMAGE_SUFFIX;
import static com.zh.xfz.constans.Constants.FILE_CRIP_IMAGE_NAME;
import static com.zh.xfz.constans.Constants.FILE_IMAGE_TEMP_PATH;
import static com.zh.xfz.constans.Constants.FILE_PROVIDER;
import static com.zh.xfz.constans.Constants.FILE_ROOT_PATH;

/**
 * author : dayezi
 * data :2019/10/29
 * description:
 */
@Route(path = UpLoadPortraitActivity.AROUTER_PATH)
@ToolbarLeft(menuId = R.menu.menu_sure)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "编辑头像")
public class UpLoadPortraitActivity extends BaseActivity implements View.OnClickListener {
    public final static String AROUTER_PATH = "/main/UpLoadPortraitActivity/";

    @Inject
    MyPopupWindow popupWindow;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.img_iv)
    NiceImageView imageView;

    @Inject
    UserPresenter userPresenter;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_upload_portrait;
    }

    @Override
    public void init() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qcrode_tv:
                popupWindow.dismiss();
                CamaraUtils.openCamara(this);
                break;
            case R.id.pic_camera_tv:
                popupWindow.dismiss();
                getPicFromCamera();
                break;
            case R.id.cancel_tv:
                popupWindow.dismiss();
                break;
        }
    }

    //调用照相机返回图片文件
    private File tempFile;

    private File updateHeadFile;

    private void getPicFromCamera() {
        //用于保存调用相机拍照后所生成的文件
        tempFile = new File(Environment.getExternalStorageDirectory().getPath(), System.currentTimeMillis() + CRIP_IMAGE_SUFFIX);
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this, FILE_PROVIDER, tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {    //否则使用Uri.fromFile(file)方法获取Uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, Constants.CONSULT_DOC_CAMERA);
    }

    @NeedPermission(next = false, value = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE})
    @OnClick(R.id.select_type_tv)
    public void selectPic() {
        if (popupWindow.isShowing()) return;
        popupWindow.setBackgroundAlpha();
        popupWindow.showAtLocation(toolbar, Gravity.BOTTOM, 0, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case Constants.REQUEST_PICK_IMAGE:
                    CamaraUtils.cripPic(data.getData(), this);
                    break;
                case Constants.CORP_CAMERA_IMAGE:
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        File rootFile = new File(Environment.getExternalStorageDirectory().getPath()
                                + File.separator + FILE_ROOT_PATH + File.separator + FILE_IMAGE_TEMP_PATH);
                        if (!rootFile.exists()) {
                            rootFile.mkdirs();
                        }
                        Bitmap image = bundle.getParcelable("data");
                        imageView.setImageBitmap(image);
                        updateHeadFile = new File(rootFile, FILE_CRIP_IMAGE_NAME);
                        ImageUtils.save(image, updateHeadFile, Bitmap.CompressFormat.JPEG);
                    }
                    break;
            }
        } else if (resultCode == RESULT_OK && requestCode == Constants.CONSULT_DOC_CAMERA) {
            //用相机返回的照片去调用剪裁也需要对Uri进行处理
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Uri contentUri = FileProvider.getUriForFile(this, FILE_PROVIDER, tempFile);
                CamaraUtils.cripPic(contentUri, this);
            } else {
                CamaraUtils.cripPic(Uri.fromFile(tempFile), this);
            }
        }
    }

    @OnMenuOnclick
    public void menuClick() {
        if (updateHeadFile != null) {
            userPresenter.uploadImg(updateHeadFile);
        }
    }

}
