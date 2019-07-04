package zh.com.jyu.dagger.module.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import core.app.zh.com.core.annotation.FragmentScope;
import dagger.Module;
import dagger.Provides;
import zh.com.jyu.R;
import zh.com.jyu.business.fragment.produce.ProduceFragment;
import zh.com.jyu.business.fragment.produce.ProduceListFragment;

/**
 * author : dayezi
 * data :2019/6/27
 * description:
 */
@Module
public class ProduceModule {

    @FragmentScope
    @Provides
    public String[] tiltes(ProduceFragment fragment) {
        String[] titles = fragment.getActivity().getResources().getStringArray(R.array.act_produce_list_strs);
        return titles;
    }

    @FragmentScope
    @Provides
    public FragmentManager getFragmentManager(ProduceFragment fragment) {
        return fragment.getChildFragmentManager();
    }

    @FragmentScope
    @Provides
    public List<Fragment> fragmentList() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(ProduceListFragment.newInstance(0));
        fragmentList.add(ProduceListFragment.newInstance(1));
        return fragmentList;
    }
}
