package zh.com.jyu.dagger.module.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import core.app.zh.com.core.annotation.FragmentScope;
import dagger.Module;
import dagger.Provides;
import zh.com.jyu.R;
import zh.com.jyu.business.fragment.PlanFragment;
import zh.com.jyu.business.fragment.plan.PlanDoneFragment;
import zh.com.jyu.business.fragment.plan.PlanPartDoneFragment;
import zh.com.jyu.business.fragment.plan.PlanWaitFragment;

/**
 * author : dayezi
 * data :2019/6/11
 * description:
 */
@Module
public class PlanModule {

    @FragmentScope
    @Provides
    public String[] tiltes(PlanFragment fragment) {
        String[] titles = fragment.getActivity().getResources().getStringArray(R.array.fragment_home_plan_status_strs);
        return titles;
    }

    @FragmentScope
    @Provides
    public FragmentManager getFragmentManager(PlanFragment fragment) {
        return fragment.getChildFragmentManager();
    }

    @FragmentScope
    @Provides
    public List<Fragment> fragmentList() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new PlanWaitFragment());
        fragmentList.add(new PlanPartDoneFragment());
        fragmentList.add(new PlanDoneFragment());
        return fragmentList;
    }
}
