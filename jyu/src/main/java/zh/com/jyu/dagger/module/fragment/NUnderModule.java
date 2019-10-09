package zh.com.jyu.dagger.module.fragment;

import core.app.zh.com.core.annotation.FragmentScope;
import core.app.zh.com.core.base.BaseFragment;
import dagger.Module;
import dagger.Provides;
import zh.com.jyu.business.fragment.leader.NUnderFragment;
import zh.com.jyu.business.fragment.leader.UnderFragment;

/**
 * author : dayezi
 * data :2019/9/4
 * description:
 */
@Module
public class NUnderModule {
    @FragmentScope
    @Provides
    public BaseFragment baseFragment(NUnderFragment fragment) {
        return fragment;
    }
}
