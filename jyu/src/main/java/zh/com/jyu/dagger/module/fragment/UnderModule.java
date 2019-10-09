package zh.com.jyu.dagger.module.fragment;

import core.app.zh.com.core.annotation.FragmentScope;
import core.app.zh.com.core.base.BaseFragment;
import dagger.Module;
import dagger.Provides;
import zh.com.jyu.business.fragment.leader.UnderFragment;

/**
 * author : dayezi
 * data :2019/9/4
 * description:
 */
@Module
public class UnderModule {

    @FragmentScope
    @Provides
    public BaseFragment baseFragment(UnderFragment fragment) {
        return fragment;
    }
}
