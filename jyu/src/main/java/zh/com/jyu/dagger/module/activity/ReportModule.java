package zh.com.jyu.dagger.module.activity;

import com.afollestad.materialdialogs.MaterialDialog;

import core.app.zh.com.core.annotation.ActivityScope;
import dagger.Module;
import dagger.Provides;
import zh.com.jyu.business.activity.ReportActivity;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
@Module
public class ReportModule {


    @ActivityScope
    @Provides
    public MaterialDialog.Builder dialog(ReportActivity activity) {
        return new MaterialDialog.Builder(activity).title("提示").content("是否删除该条数据?").positiveText("确定").neutralText("取消");
    }
}
