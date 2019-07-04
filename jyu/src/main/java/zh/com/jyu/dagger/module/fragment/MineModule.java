package zh.com.jyu.dagger.module.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;

import core.app.zh.com.core.annotation.FragmentScope;
import core.app.zh.com.core.listener.impl.ActivityLifecycleCallbackListener;
import dagger.Module;
import dagger.Provides;
import zh.com.jyu.business.activity.LoginActivity;
import zh.com.jyu.business.fragment.mine.MineFragment;

/**
 * author : dayezi
 * data :2019/6/18
 * description:
 */
@Module
public class MineModule {

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
}
