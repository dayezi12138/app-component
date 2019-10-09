package zh.com.jyu.dagger.module.fragment;

import core.app.zh.com.core.annotation.FragmentScope;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.MyBaseModel;
import dagger.Module;
import dagger.Provides;
import zh.com.jyu.application.MyApplication;
import zh.com.jyu.business.fragment.plan.NPlanFragment;

/**
 * author : dayezi
 * data :2019/8/14
 * description:
 */
@Module
public class NPlanModule {

    @FragmentScope
    @Provides
    public BaseFragment baseFragment(NPlanFragment fragment) {
        return fragment;
    }

    @Provides
    @FragmentScope
    public MyBaseModel getMyBaseModel(MyApplication application, BaseFragment fragment) {
        return new MyBaseModel(application) {
            @Override
            public BaseView getBaseView() {
                return fragment;
            }

            @Override
            public BaseActivity getMyActivity() {
                return fragment.getMyActivity();
            }
        };
    }
}
