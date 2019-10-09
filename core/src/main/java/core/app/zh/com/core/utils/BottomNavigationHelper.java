package core.app.zh.com.core.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

import javax.inject.Inject;

import core.app.zh.com.core.R;

/**
 * author : dayezi
 * data :2019/8/1
 * description:
 */
public class BottomNavigationHelper {
    private List<Fragment> fragmentList;
    private FragmentManager fragmentManager;

    @Inject
    public BottomNavigationHelper(FragmentManager fragmentManager, List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
        this.fragmentManager = fragmentManager;
    }

    public void addFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (Fragment fragment : fragmentList) {
            transaction.add(R.id.fragment_, fragment);
            transaction.hide(fragment);
        }
        transaction.show(fragmentList.get(0));
        transaction.commit();
    }

    public void setTabPosition(int index) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragmentList.size(); i++) {
            if (i != index) transaction.hide(fragmentList.get(i));
        }
        transaction.show(fragmentList.get(index));
        transaction.commit();
    }
}
