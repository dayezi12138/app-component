package zh.com.jyu.dagger.module.activity;

import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.listener.AppExitListener;
import core.app.zh.com.core.listener.impl.BaseAppExitListener;
import dagger.Module;
import dagger.Provides;
import zh.com.jyu.business.activity.MainActivity;
import zh.com.jyu.business.fragment.board.BulletinBoardFragment;
import zh.com.jyu.business.fragment.leader.NLeaderFragment;
import zh.com.jyu.business.fragment.mine.MineFragment;
import zh.com.jyu.business.fragment.produce.ProduceFragment;

/**
 * author : dayezi
 * data :2019/6/11
 * description:
 */
@Module
public class MainModule {

    @Provides
    @ActivityScope
    public List<BaseFragment> fragmentList() {
        List<BaseFragment> list = new ArrayList<>();
        list.add(new NLeaderFragment());
        list.add(new BulletinBoardFragment());
        list.add(new ProduceFragment());
        list.add(new MineFragment());
        return list;
    }

    @ActivityScope
    @Provides
    AppExitListener baseAppExitListener(MainActivity loginActivity) {
        return new BaseAppExitListener(loginActivity);
    }

    @Provides
    @ActivityScope
    public FragmentManager fragmentManager(MainActivity activity) {
        return activity.getSupportFragmentManager();
    }
}
