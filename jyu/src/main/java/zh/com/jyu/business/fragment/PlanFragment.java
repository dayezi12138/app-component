package zh.com.jyu.business.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import zh.com.jyu.business.adapter.HomePagerAdapter;

/**
 * author : dayezi
 * data :2019/6/11
 * description:
 */
public class PlanFragment extends CommonTabFragment {
    @Override
    public String[] titles() {
        return titles;
    }

    @Override
    public FragmentPagerAdapter adapter() {
        return homePagerAdapter;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Inject
    HomePagerAdapter homePagerAdapter;

    @Inject
    String[] titles;

    @Inject
    List<Fragment> fragmentList;

    @Override
    public void onResume() {
        super.onResume();
    }

    private int position = 0;

    public Fragment getFragment() {
        return fragmentList.get(position);
    }

    @Override
    public void onTabSelected(int position) {
        if (fragmentList.get(position) instanceof StatusFragment) {
            ((StatusFragment) fragmentList.get(position)).start();
        }
    }

}
