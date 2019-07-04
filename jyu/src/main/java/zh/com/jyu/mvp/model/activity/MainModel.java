package zh.com.jyu.mvp.model.activity;

import android.support.v4.app.FragmentManager;

import java.util.List;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.BaseModel;
import zh.com.jyu.business.activity.MainActivity;

/**
 * author : dayezi
 * data :2019/6/11
 * description:
 */
public class MainModel implements BaseModel<MainActivity> {
    private MainActivity activity;
    private FragmentManager fragmentManager;
    private List<BaseFragment> fragmentList;

    @Inject
    public MainModel(MainActivity activity, FragmentManager fragmentManager, List<BaseFragment> fragmentList) {
        this.fragmentManager = fragmentManager;
        this.fragmentList = fragmentList;
        this.activity = activity;
    }

    @Override
    public MainActivity getBean() {
        return activity;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public List<BaseFragment> getFragmentList() {
        return fragmentList;
    }
}
