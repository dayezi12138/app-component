package zh.com.jyu.dagger.module.fragment;

import android.app.Application;

import core.app.zh.com.core.annotation.FragmentScope;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.MyBaseModel;
import dagger.Module;
import dagger.Provides;
import zh.com.jyu.application.MyApplication;
import zh.com.jyu.business.fragment.board.BulletinBoardFragment;

/**
 * author : dayezi
 * data :2019/9/5
 * description:
 */
@Module
public class BulletinBoardModule {
    @FragmentScope
    @Provides
    public BaseFragment fragment(BulletinBoardFragment fragment) {
        return fragment;
    }

    @FragmentScope
    @Provides
    public MyBaseModel baseModel(BulletinBoardFragment fragment, MyApplication application) {
        return new MyBaseModel(application) {
            @Override
            public BaseView getBaseView() {
                return (fragment != null && fragment instanceof BaseView) ? fragment : null;
            }

            @Override
            public BaseActivity getMyActivity() {
                return fragment.getMyActivity();
            }
        };
    }
}
