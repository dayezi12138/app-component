package zh.com.jyu.dagger.module.activity;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.listener.AppExitListener;
import core.app.zh.com.core.listener.impl.BaseAppExitListener;
import dagger.Module;
import dagger.Provides;
import zh.com.jyu.business.activity.LoginActivity;

/**
 * author : dayezi
 * data :2019/6/10
 * description:
 */
@Module
public class LoginModule {
    @ActivityScope
    @Provides
    AppExitListener baseAppExitListener(LoginActivity loginActivity) {
        return new BaseAppExitListener(loginActivity);
    }
}
