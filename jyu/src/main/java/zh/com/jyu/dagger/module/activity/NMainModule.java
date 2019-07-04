package zh.com.jyu.dagger.module.activity;

import java.util.ArrayList;
import java.util.List;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.listener.AppExitListener;
import core.app.zh.com.core.listener.impl.BaseAppExitListener;
import dagger.Module;
import dagger.Provides;
import zh.com.jyu.business.activity.NMainActivity;
import zh.com.jyu.business.fragment.HomeFragment;
import zh.com.jyu.business.fragment.PlanFragment;
import zh.com.jyu.business.fragment.board.BulletinBoardFragment;
import zh.com.jyu.business.fragment.mine.MineFragment;

/**
 * author : dayezi
 * data :2019/6/21
 * description:
 */
@Module
public class NMainModule {

    @Provides
    @ActivityScope
    public List<BaseFragment> fragmentList() {
        List<BaseFragment> list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new BulletinBoardFragment());
        list.add(new PlanFragment());
        list.add(new MineFragment());
        return list;
    }

    @ActivityScope
    @Provides
    AppExitListener baseAppExitListener(NMainActivity loginActivity) {
        return new BaseAppExitListener(loginActivity);
    }

}
