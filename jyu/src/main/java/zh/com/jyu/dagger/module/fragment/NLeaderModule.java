package zh.com.jyu.dagger.module.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import core.app.zh.com.core.annotation.FragmentScope;
import dagger.Module;
import dagger.Provides;
import zh.com.jyu.R;
import zh.com.jyu.business.fragment.leader.NLeaderFragment;
import zh.com.jyu.business.fragment.leader.NUnderFragment;

/**
 * author : dayezi
 * data :2019/6/21
 * description:
 */
@Module
public class NLeaderModule {

    @FragmentScope
    @Provides
    public String[] tiltes(NLeaderFragment fragment) {
        String[] titles = fragment.getActivity().getResources().getStringArray(R.array.fragment_home_start_status_strs);
        return titles;
    }

    @FragmentScope
    @Provides
    public FragmentManager getFragmentManager(NLeaderFragment fragment) {
        return fragment.getChildFragmentManager();
    }

    @FragmentScope
    @Provides
    public List<Fragment> fragmentList() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(NUnderFragment.newInstance(0));
        fragmentList.add(NUnderFragment.newInstance(1));
        fragmentList.add(NUnderFragment.newInstance(2));
        return fragmentList;
    }
}
