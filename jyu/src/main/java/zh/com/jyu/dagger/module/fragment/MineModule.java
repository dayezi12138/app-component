package zh.com.jyu.dagger.module.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Environment;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;

import java.io.File;

import core.app.zh.com.core.annotation.FragmentScope;
import core.app.zh.com.core.listener.impl.ActivityLifecycleCallbackListener;
import dagger.Module;
import dagger.Provides;
import zh.com.jyu.business.activity.LoginActivity;
import zh.com.jyu.business.fragment.mine.MineFragment;
import zh.com.jyu.dagger.module.AppModule;

/**
 * author : dayezi
 * data :2019/6/18
 * description:
 */
@Module
public class MineModule {
    public static final String DOWN_LOAD_URL = "http://app.sunmen.cn";
    public static final String APP_NAME = "JYu.apk";
    public static final String APK_PATH;

    static {
        APK_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + AppModule.DB_NAME + File.separator + "downLoad" + File.separator + APP_NAME;
    }

    @SuppressLint("WrongConstant")
    @FragmentScope
    @Provides
    public MaterialDialog dialog(MineFragment fragment) {
        return new MaterialDialog.Builder(fragment.getContext()).title("提示").content("确定退出该账号?").positiveText("确定").neutralText("取消")
                .onPositive((dialog, which) -> {
                    SPUtils.getInstance().clear();
                    ARouter.getInstance().build(LoginActivity.AROUTE_PATH).withFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK).navigation();
                    for (Activity activity : ActivityLifecycleCallbackListener.sActivityList) {
                        activity.finish();
                    }
                    dialog.dismiss();
                })
                .build();
    }

    //    @FragmentScope
//    @Provides
//    public void download(MineFragment fragment, Handler handler) {
//        View view = LayoutInflater.from(fragment.getContext()).inflate(R.layout.view_proccess, null);
//        ProgressBar progressBar = view.findViewById(R.id.progressBar);
//        TextView tv = view.findViewById(R.id.tv);
//        Dialog up = new MaterialDialog.Builder(fragment.getContext()).title("提示").customView(view, false).build();
//        Download download = new Download(DOWN_LOAD_URL, new JsDownloadListener() {
//            @Override
//            public void onStartDownload(long length) {
//                LogUtils.e(length);
//            }
//
//            @Override
//            public void onProgress(int progress) {
//                Message message = Message.obtain();
//                message.what = 1;
//                message.obj = progress;
//                handler.sendMessage(message);
//                if (progress == 100) up.dismiss();
//            }
//
//            @Override
//            public void onFail(String errorInfo) {
//                LogUtils.e(errorInfo);
//                ToastUtils.showShort(errorInfo);
//            }
//        });
//        File parentFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + AppModule.DB_NAME + File.separator + "downLoad");
//        if (!parentFile.exists()) parentFile.mkdirs();
//        File t = new File(parentFile.getAbsolutePath() + File.separator + "test.apk");
//        download.download("download/soa.ywjy/2019.apk", t);
//        up.show();
//    }

    @FragmentScope
    @Provides
    public MaterialDialog.Builder tip(MineFragment fragment) {
        return new MaterialDialog.Builder(fragment.getContext()).title("提示").content("已有新版本，是否更新?").positiveText("确定").neutralText("取消");
//                .onPositive(new MaterialDialog.SingleButtonCallback() {
//            @Override
//            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////                download(fragment, handler);
//            }
//        })
    }

//    @FragmentScope
//    @Provides
//    public View view(MineFragment fragment) {
//        View view = LayoutInflater.from(fragment.getContext()).inflate(R.layout.view_proccess, null);
//        return view;
//    }

//    @FragmentScope
//    @Provides
//    public Handler handler(View view) {
//        ProgressBar progressBar = view.findViewById(R.id.progressBar);
//        TextView tv = view.findViewById(R.id.tv);
//        return new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                if (msg.what == 1) {
//                    int proccess = (Integer) msg.obj;
//                    progressBar.setProgress(proccess);
//                    if (proccess != 0)
//                        tv.setText(proccess + "%");
//                }
//            }
//        };
//    }

}
