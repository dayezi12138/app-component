package zh.com.jyu.dagger.module.activity;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.MyBaseModel;
import dagger.Module;
import dagger.Provides;
import zh.com.jyu.application.MyApplication;
import zh.com.jyu.business.activity.BulletinBoardActivity;

/**
 * author : dayezi
 * data :2019/9/5
 * description:
 */
@Module
public class ABulletinBoardModule {

    @ActivityScope
    @Provides
    public MyBaseModel myBaseModel(MyApplication application, BulletinBoardActivity activity) {
        return new MyBaseModel(application) {
            @Override
            public BaseView getBaseView() {
                return (activity != null && activity instanceof BaseView) ? activity : null;
            }

            @Override
            public BaseActivity getMyActivity() {
                return activity;
            }
        };
    }
}
