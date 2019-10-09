package zh.com.jyu.dagger.module;


import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.dagger.component.ActivityComponent;
import core.app.zh.com.core.dagger.module.EmptyModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import zh.com.jyu.business.activity.AddGroupActivity;
import zh.com.jyu.business.activity.AddReportActivity;
import zh.com.jyu.business.activity.BulletinBoardActivity;
import zh.com.jyu.business.activity.CraftDetailActivity;
import zh.com.jyu.business.activity.CraftListActivity;
import zh.com.jyu.business.activity.EditGroupActivity;
import zh.com.jyu.business.activity.EditReportActivity;
import zh.com.jyu.business.activity.GoodListActivity;
import zh.com.jyu.business.activity.GoodsDetailActivity;
import zh.com.jyu.business.activity.GoodsListActivity;
import zh.com.jyu.business.activity.GoodsListHActivity;
import zh.com.jyu.business.activity.GroupListActivity;
import zh.com.jyu.business.activity.HtmlActivity;
import zh.com.jyu.business.activity.LoginActivity;
import zh.com.jyu.business.activity.MainActivity;
import zh.com.jyu.business.activity.NMainActivity;
import zh.com.jyu.business.activity.OrderDetailActivity;
import zh.com.jyu.business.activity.PhotoActivity;
import zh.com.jyu.business.activity.PickingActivity;
import zh.com.jyu.business.activity.PlanActivity;
import zh.com.jyu.business.activity.RelationOrderListActivity;
import zh.com.jyu.business.activity.ReportActivity;
import zh.com.jyu.business.activity.SearchResultActivity;
import zh.com.jyu.business.activity.SelectUserActivity;
import zh.com.jyu.business.activity.SplashActivity;
import zh.com.jyu.business.activity.UserListActivity;
import zh.com.jyu.dagger.module.activity.ABulletinBoardModule;
import zh.com.jyu.dagger.module.activity.AddReportModule;
import zh.com.jyu.dagger.module.activity.CraftDetailModule;
import zh.com.jyu.dagger.module.activity.GoodListModule;
import zh.com.jyu.dagger.module.activity.GoodsDetailModule;
import zh.com.jyu.dagger.module.activity.GoodsListModule;
import zh.com.jyu.dagger.module.activity.LoginModule;
import zh.com.jyu.dagger.module.activity.MainModule;
import zh.com.jyu.dagger.module.activity.NMainModule;
import zh.com.jyu.dagger.module.activity.PlanModule;
import zh.com.jyu.dagger.module.activity.ReportModule;

/**
 * @auther create by Administrator
 * DATE:2019/3/26 0026 11
 */
@Module(subcomponents = ActivityComponent.class)
public abstract class AllActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = EmptyModule.class)
    abstract SplashActivity contributeSplashActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {LoginModule.class})
    abstract LoginActivity contributeLoginActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainActivity contributeMainActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract PickingActivity contributePickingActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {GoodListModule.class})
    abstract GoodListActivity contributeGoodListActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {GoodsDetailModule.class})
    abstract GoodsDetailActivity contributeGoodsDetailActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract CraftListActivity contributeCraftListActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {CraftDetailModule.class})
    abstract CraftDetailActivity contributeCraftDetailActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract RelationOrderListActivity contributeRelationOrderListActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {ReportModule.class})
    abstract ReportActivity contributeReportActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract PhotoActivity contributePhotoActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {AddReportModule.class})
    abstract AddReportActivity contributeAddReportActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract EditReportActivity contributeEditReportActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract UserListActivity contributeUserListActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract SelectUserActivity contributeSelectUserActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract GroupListActivity contributeGroupListActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract AddGroupActivity contributeAddGroupActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract EditGroupActivity contributeEditGroupActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract SearchResultActivity contributeSearchResultActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {GoodsListModule.class})
    abstract GoodsListActivity contributeGoodsListActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {NMainModule.class})
    abstract NMainActivity contributeNMainActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract OrderDetailActivity contributeOrderDetailActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract GoodsListHActivity contributeGoodsListHActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {PlanModule.class})
    abstract PlanActivity contributePlanActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract HtmlActivity contributeHtmlActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {ABulletinBoardModule.class})
    abstract BulletinBoardActivity contributeBulletinBoardActivityInjector();
}