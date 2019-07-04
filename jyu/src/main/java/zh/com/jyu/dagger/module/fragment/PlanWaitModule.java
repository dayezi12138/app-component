package zh.com.jyu.dagger.module.fragment;

import core.app.zh.com.core.annotation.FragmentScope;
import core.app.zh.com.core.base.BaseFragment;
import dagger.Module;
import dagger.Provides;
import zh.com.jyu.business.fragment.plan.PlanWaitFragment;

/**
 * author : dayezi
 * data :2019/6/12
 * description:
 */
@Module
public class PlanWaitModule {

    @FragmentScope
    @Provides
    public BaseFragment fragment(PlanWaitFragment fragment) {
        return fragment;
    }
}
