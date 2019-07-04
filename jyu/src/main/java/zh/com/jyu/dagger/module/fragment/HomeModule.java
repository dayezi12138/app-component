package zh.com.jyu.dagger.module.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import core.app.zh.com.core.annotation.FragmentScope;
import dagger.Module;
import dagger.Provides;
import zh.com.jyu.R;
import zh.com.jyu.business.fragment.HomeFragment;
import zh.com.jyu.business.fragment.leader.LeaderFragment;

/**
 * author : dayezi
 * data :2019/6/11
 * description:
 */
@Module
public class HomeModule {

    @FragmentScope
    @Provides
    public String[] tiltes(HomeFragment fragment) {
        String[] titles = fragment.getActivity().getResources().getStringArray(R.array.fragment_home_start_status_strs);
        return titles;
    }

    @FragmentScope
    @Provides
    public FragmentManager getFragmentManager(HomeFragment fragment) {
        return fragment.getActivity().getSupportFragmentManager();
    }

    @FragmentScope
    @Provides
    public List<Fragment> fragmentList() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(LeaderFragment.newInstance(0));
        fragmentList.add(LeaderFragment.newInstance(1));
        fragmentList.add(LeaderFragment.newInstance(2));
        return fragmentList;
    }


}
