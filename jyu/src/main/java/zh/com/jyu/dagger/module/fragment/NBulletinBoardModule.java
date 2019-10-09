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
import zh.com.jyu.business.fragment.board.NBulletinBoardFragment;

@Module
public class NBulletinBoardModule {
    @FragmentScope
    @Provides
    public BaseFragment fragment(NBulletinBoardFragment fragment) {
        return fragment;
    }

    @FragmentScope
    @Provides
    public MyBaseModel baseModel(NBulletinBoardFragment fragment, MyApplication application) {
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