package zh.com.jyu.dagger.module.fragment;

import core.app.zh.com.core.annotation.FragmentScope;
import core.app.zh.com.core.base.BaseFragment;
import dagger.Module;
import dagger.Provides;
import zh.com.jyu.business.fragment.plan.PlanDoneFragment;

/**
 * author : dayezi
 * data :2019/6/13
 * description:
 */
@Module
public class PlanDoneModule {

    @FragmentScope
    @Provides
    public BaseFragment fragment(PlanDoneFragment fragment) {
        return fragment;
    }
}
