package zh.com.jyu.dagger.module.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import core.app.zh.com.core.annotation.FragmentScope;
import core.app.zh.com.core.base.BaseFragment;
import dagger.Module;
import dagger.Provides;
import zh.com.jyu.R;
import zh.com.jyu.application.MyApplication;
import zh.com.jyu.business.fragment.plan.MPlanFragment;
import zh.com.jyu.business.fragment.plan.NPlanFragment;

/**
 * author : dayezi
 * data :2019/8/14
 * description:
 */
@Module
public class MPlanModule extends NPlanModule {
    @FragmentScope
    @Provides
    public BaseFragment baseFragment(MPlanFragment fragment) {
        return fragment;
    }

    @FragmentScope
    @Provides
    public String[] tiltes(MyApplication application) {
        String[] titles = application.getResources().getStringArray(R.array.fragment_home_plan_status_strs);
        return titles;
    }

    @FragmentScope
    @Provides
    public FragmentManager getFragmentManager(MPlanFragment fragment) {
        return fragment.getChildFragmentManager();
    }

    @FragmentScope
    @Provides
    public List<Fragment> fragmentList() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(NPlanFragment.newInstance(0));
        fragmentList.add(NPlanFragment.newInstance(1));
        fragmentList.add(NPlanFragment.newInstance(2));
        return fragmentList;
    }
}
