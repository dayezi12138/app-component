package zh.com.jyu.mvp.presenter.fragment.mine;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.io.File;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MyObservable;
import core.app.zh.com.core.download.Download;
import core.app.zh.com.core.download.JsDownloadListener;
import zh.com.jyu.R;
import zh.com.jyu.bean.fragment.UpdateInfo;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.dagger.module.AppModule;
import zh.com.jyu.dagger.module.fragment.MineModule;
import zh.com.jyu.mvp.contract.fragment.MineContract;
import zh.com.jyu.mvp.model.fragment.MineModel;

/**
 * author : dayezi
 * data :2019/8/7
 * description:
 */
public class MinePresenter extends BasePresenter<MineContract.MineUI> implements MineContract.Presenter {
    private MineModel model;

    @Inject
    public MinePresenter(MineModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void checkUpdate() {
        model.checkUpdate(new MyObservable.OnSuccessListener<Data<UpdateInfo>>() {
            @Override
            public void onFail(String msg) {
                view.get().showMsg(msg);
            }

            @Override
            public void onSuccess(Data<UpdateInfo> updateInfo) {
                if (updateInfo.isState()) {
                    view.get().success(updateInfo.getRes());
                } else view.get().showMsg(updateInfo.getMsg());
            }
        });
    }

    @Override
    public void downLoad(String url) {
        View view = LayoutInflater.from(model.getBean().getContext()).inflate(R.layout.view_proccess, null);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        TextView tv = view.findViewById(R.id.tv);
        Dialog up = new MaterialDialog.Builder(model.getBean().getContext()).title("提示").customView(view, false).build();
        Download download = new Download(MineModule.DOWN_LOAD_URL, new JsDownloadListener() {
            @Override
            public void onStartDownload(long length) {
            }

            @Override
            public void onProgress(int progress) {
                model.getBean().getActivity().runOnUiThread(() -> {
                    progressBar.setProgress(progress);
                    if (progress != 0)
                        tv.setText(progress + "%");
                });
                if (progress == 100) {
                    File file = new File(MineModule.APK_PATH);
                    if (file.exists()) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            Uri contentUri = FileProvider.getUriForFile(model.getBean().getActivity(), "zh.com.jyu.fileProvider", file);
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
                        } else {
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                        }
                        model.getBean().getActivity().startActivity(intent);

//                        Intent install = new Intent(Intent.ACTION_VIEW);
//                        install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
//                        install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        model.getBean().getActivity().startActivity(install);
                    }
                    up.dismiss();
                }
            }

            @Override
            public void onFail(String errorInfo) {
                LogUtils.e(errorInfo);
                ToastUtils.showShort(errorInfo);
            }
        });
        File parentFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + AppModule.DB_NAME + File.separator + "downLoad");
        if (!parentFile.exists()) parentFile.mkdirs();
        File t = new File(parentFile.getAbsolutePath() + File.separator + MineModule.APP_NAME);
        download.download(url, t);
        up.show();
    }
}
