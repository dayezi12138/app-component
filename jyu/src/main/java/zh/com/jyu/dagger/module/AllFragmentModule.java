package zh.com.jyu.dagger.module;

import core.app.zh.com.core.annotation.FragmentScope;
import core.app.zh.com.core.dagger.component.FragmentComponent;
import core.app.zh.com.core.dagger.module.EmptyModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import zh.com.jyu.business.fragment.BaseGoodListFragment;
import zh.com.jyu.business.fragment.HomeFragment;
import zh.com.jyu.business.fragment.PlanFragment;
import zh.com.jyu.business.fragment.board.BulletinBoardFragment;
import zh.com.jyu.business.fragment.leader.LeaderFragment;
import zh.com.jyu.business.fragment.leader.NLeaderFragment;
import zh.com.jyu.business.fragment.leader.StartingFragment;
import zh.com.jyu.business.fragment.leader.UnStartFragment;
import zh.com.jyu.business.fragment.leader.UnderFragment;
import zh.com.jyu.business.fragment.mine.MineFragment;
import zh.com.jyu.business.fragment.plan.GoodsListFragment;
import zh.com.jyu.business.fragment.plan.NPlanFragment;
import zh.com.jyu.business.fragment.plan.PlanDoneFragment;
import zh.com.jyu.business.fragment.plan.PlanPartDoneFragment;
import zh.com.jyu.business.fragment.plan.PlanWaitFragment;
import zh.com.jyu.business.fragment.produce.ProduceFragment;
import zh.com.jyu.business.fragment.produce.ProduceListFragment;
import zh.com.jyu.dagger.module.fragment.HomeModule;
import zh.com.jyu.dagger.module.fragment.MineModule;
import zh.com.jyu.dagger.module.fragment.NLeaderModule;
import zh.com.jyu.dagger.module.fragment.PlanDoneModule;
import zh.com.jyu.dagger.module.fragment.PlanModule;
import zh.com.jyu.dagger.module.fragment.PlanPartDoneModule;
import zh.com.jyu.dagger.module.fragment.PlanWaitModule;
import zh.com.jyu.dagger.module.fragment.ProduceModule;

/**
 * @auther create by Administrator
 * DATE:2019/3/26 0026 17
 */
@Module(subcomponents = FragmentComponent.class)
public abstract class AllFragmentModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeFragment contributeHomeFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = PlanModule.class)
    abstract PlanFragment contributeOrderFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = EmptyModule.class)
    abstract UnStartFragment contributeUnStartFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = EmptyModule.class)
    abstract StartingFragment contributeStartingFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = PlanWaitModule.class)
    abstract PlanWaitFragment contributePlanWaitFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = PlanPartDoneModule.class)
    abstract PlanPartDoneFragment contributePlanPartDoneInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = PlanDoneModule.class)
    abstract PlanDoneFragment contributePlanDoneFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = EmptyModule.class)
    abstract BaseGoodListFragment contributeBaseGoodListFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = EmptyModule.class)
    abstract BulletinBoardFragment contributeBulletinBoardFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = MineModule.class)
    abstract MineFragment contributeMineFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = EmptyModule.class)
    abstract LeaderFragment contributeLeaderFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = EmptyModule.class)
    abstract GoodsListFragment contributeGoodsListFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = NLeaderModule.class)
    abstract NLeaderFragment contributeNLeaderFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = EmptyModule.class)
    abstract NPlanFragment contributeNPlanFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = EmptyModule.class)
    abstract UnderFragment contributeUnderFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = ProduceModule.class)
    abstract ProduceFragment contributeProduceFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = EmptyModule.class)
    abstract ProduceListFragment contributeProduceListFragmentInjector();
//    @FragmentScope
//    @ContributesAndroidInjector(modules = EmptyModule.class)
//    abstract PlanFragment contributePlanFragmentInjector();
}