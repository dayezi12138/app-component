package zh.com.jyu.dagger.module.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import core.app.zh.com.core.annotation.ActivityScope;
import dagger.Module;
import dagger.Provides;
import zh.com.jyu.R;
import zh.com.jyu.application.MyApplication;
import zh.com.jyu.business.activity.PlanActivity;
import zh.com.jyu.business.fragment.plan.NPlanFragment;

/**
 * author : dayezi
 * data :2019/6/27
 * description:
 */
@Module
public class PlanModule {
    @ActivityScope
    @Provides
    public String[] tiltes(MyApplication application) {
        String[] titles = application.getResources().getStringArray(R.array.fragment_home_plan_status_strs);
        return titles;
    }

    @ActivityScope
    @Provides
    public FragmentManager getFragmentManager(PlanActivity activity) {
        return activity.getSupportFragmentManager();
    }

    @ActivityScope
    @Provides
    public List<Fragment> fragmentList() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(NPlanFragment.newInstance(0));
        fragmentList.add(NPlanFragment.newInstance(1));
        fragmentList.add(NPlanFragment.newInstance(2));
        return fragmentList;
    }
}
