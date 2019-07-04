package zh.com.jyu.dagger.module.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import core.app.zh.com.core.annotation.ActivityScope;
import dagger.Module;
import dagger.Provides;
import zh.com.jyu.R;
import zh.com.jyu.business.activity.GoodsListActivity;
import zh.com.jyu.business.fragment.plan.GoodsListFragment;

/**
 * author : dayezi
 * data :2019/6/13
 * description:
 */
@Module
public class GoodsListModule {

    @ActivityScope
    @Provides
    public String[] tiltes(GoodsListActivity activity) {
        String[] titles = activity.getResources().getStringArray(R.array.act_goods_list_strs);
        return titles;
    }

    @ActivityScope
    @Provides
    public FragmentManager getFragmentManager(GoodsListActivity activity) {
        return activity.getSupportFragmentManager();
    }

    @ActivityScope
    @Provides
    public List<Fragment> fragmentList() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new GoodsListFragment());
        fragmentList.add(new GoodsListFragment());
        fragmentList.add(new GoodsListFragment());
        fragmentList.add(new GoodsListFragment());
        return fragmentList;
    }

}
