package zh.com.jyu.dagger.module.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import core.app.zh.com.core.annotation.ActivityScope;
import dagger.Module;
import dagger.Provides;
import zh.com.jyu.R;
import zh.com.jyu.business.activity.GoodListActivity;
import zh.com.jyu.business.activity.PickingActivity;
import zh.com.jyu.business.fragment.BaseGoodListFragment;
import zh.com.jyu.business.fragment.leader.StartingFragment;

/**
 * author : dayezi
 * data :2019/6/13
 * description:
 */
@Module
public class GoodListModule {

    @ActivityScope
    @Provides
    public String[] tiltes(GoodListActivity activity) {
        String[] titles = activity.getResources().getStringArray(R.array.fragment_home_start_status_strs);
        return titles;
    }

    @ActivityScope
    @Provides
    public FragmentManager getFragmentManager(GoodListActivity activity) {
        return activity.getSupportFragmentManager();
    }

    @ActivityScope
    @Provides
    public List<Fragment> fragmentList(GoodListActivity activity) {
        List<Fragment> fragmentList = new ArrayList<>();
        int planId = activity.getIntent().getIntExtra(PickingActivity.PARAM_ID, -1);
        fragmentList.add(BaseGoodListFragment.newInstance(0, planId));
        fragmentList.add(new StartingFragment());
        return fragmentList;
    }

}
