//package com.zh.xfz.business;
//
//import android.content.ContentResolver;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Rect;
//import android.net.Uri;
//import android.os.Build;
//import android.provider.MediaStore;
//import android.support.annotation.NonNull;
//import android.support.v4.content.FileProvider;
//import android.view.MotionEvent;
//import android.view.View;
//
//import com.zh.xfz.R;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//import core.app.zh.com.core.base.BaseActivity;
//import me.pqpo.smartcropperlib.view.CropImageView;
//
//public class CropActivity extends BaseActivity {
//
//    private static final String EXTRA_FROM_ALBUM = "extra_from_album";
//    private static final String EXTRA_CROPPED_FILE = "extra_cropped_file";
//    private static final int REQUEST_CODE_TAKE_PHOTO = 100;
//    private static final int REQUEST_CODE_SELECT_ALBUM = 200;
//
//    @BindView(R.id.iv_crop)
//    CropImageView ivCrop;
//
//    boolean mFromAlbum;
//    File mCroppedFile;
//
//    File tempFile;
//
//    public static Intent getJumpIntent(Context context, boolean fromAlbum, File croppedFile) {
//        Intent intent = new Intent(context, CropActivity.class);
//        intent.putExtra(EXTRA_FROM_ALBUM, fromAlbum);
//        intent.putExtra(EXTRA_CROPPED_FILE, croppedFile);
//        return intent;
//    }
//
//    @OnClick({R.id.btn_ok, R.id.btn_cancel})
//    public void click(View view) {
//        switch (view.getId()) {
//            case R.id.btn_ok:
//                if (ivCrop.canRightCrop()) {
//                    Bitmap crop = ivCrop.crop();
//                    if (crop != null) {
//                        saveImage(crop, mCroppedFile);
//                        setResult(RESULT_OK);
//                    } else {
//                        setResult(RESULT_CANCELED);
//                    }
//                    finish();
//                } else {
//                    showMsg("无法正确裁剪");
//                }
//                break;
//            case R.id.btn_cancel:
//                setResult(RESULT_CANCELED);
//                finish();
//                break;
//        }
//    }
//
//    private void selectPhoto() {
//        if (mFromAlbum) {
//            Intent selectIntent = new Intent(Intent.ACTION_PICK);
//            selectIntent.setType("image/*");
//            if (selectIntent.resolveActivity(getPackageManager()) != null) {
//                startActivityForResult(selectIntent, REQUEST_CODE_SELECT_ALBUM);
//            }
//        } else {
//            Intent startCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            Uri uri;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                uri = FileProvider.getUriForFile(this, "me.pqpo.smartcropper.fileProvider", tempFile);
//            } else {
//                uri = Uri.fromFile(tempFile);
//            }
//            startCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//            if (startCameraIntent.resolveActivity(getPackageManager()) != null) {
//                startActivityForResult(startCameraIntent, REQUEST_CODE_TAKE_PHOTO);
//            }
//        }
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode != RESULT_OK) {
//            setResult(RESULT_CANCELED);
//            finish();
//            return;
//        }
//        Bitmap selectedBitmap = null;
//        if (requestCode == REQUEST_CODE_TAKE_PHOTO && tempFile.exists()) {
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inJustDecodeBounds = true;
//            BitmapFactory.decodeFile(tempFile.getPath(), options);
//            options.inJustDecodeBounds = false;
//            options.inSampleSize = calculateSampleSize(options);
//            selectedBitmap = BitmapFactory.decodeFile(tempFile.getPath(), options);
//        } else if (requestCode == REQUEST_CODE_SELECT_ALBUM && data != null && data.getData() != null) {
//            ContentResolver cr = getContentResolver();
//            Uri bmpUri = data.getData();
//            try {
//                BitmapFactory.Options options = new BitmapFactory.Options();
//                options.inJustDecodeBounds = true;
//                BitmapFactory.decodeStream(cr.openInputStream(bmpUri), new Rect(), options);
//                options.inJustDecodeBounds = false;
//                options.inSampleSize = calculateSampleSize(options);
//                selectedBitmap = BitmapFactory.decodeStream(cr.openInputStream(bmpUri), new Rect(), options);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        if (selectedBitmap != null) {
//            ivCrop.setImageToCrop(selectedBitmap);
//        }
//    }
//
//
//    private void saveImage(Bitmap bitmap, File saveFile) {
//        try {
//            FileOutputStream fos = new FileOutputStream(saveFile);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//            fos.flush();
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private int calculateSampleSize(BitmapFactory.Options options) {
//        int outHeight = options.outHeight;
//        int outWidth = options.outWidth;
//        int sampleSize = 1;
//        int destHeight = 1000;
//        int destWidth = 1000;
//        if (outHeight > destHeight || outWidth > destHeight) {
//            if (outHeight > outWidth) {
//                sampleSize = outHeight / destHeight;
//            } else {
//                sampleSize = outWidth / destWidth;
//            }
//        }
//        if (sampleSize < 1) {
//            sampleSize = 1;
//        }
//        return sampleSize;
//    }
//
//    @NonNull
//    @Override
//    public int layoutId() {
//        return R.layout.act_crop;
//    }
//
//    @Override
//    public void init() {
//        mFromAlbum = getIntent().getBooleanExtra(EXTRA_FROM_ALBUM, true);
//        mCroppedFile = (File) getIntent().getSerializableExtra(EXTRA_CROPPED_FILE);
//        if (mCroppedFile == null) {
//            setResult(RESULT_CANCELED);
//            finish();
//            return;
//        }
//        tempFile = new File(getExternalFilesDir("img"), "temp.jpg");
//        selectPhoto();
//    }
//}