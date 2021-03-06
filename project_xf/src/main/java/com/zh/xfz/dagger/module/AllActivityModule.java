package com.zh.xfz.dagger.module;


import com.zh.xfz.business.activity.AccountLoginActivity;
import com.zh.xfz.business.activity.AccountSecurityActivity;
import com.zh.xfz.business.activity.AddFriendActivity;
import com.zh.xfz.business.activity.AddGroupMembersActivity;
import com.zh.xfz.business.activity.AddPasswordActivity;
import com.zh.xfz.business.activity.BusinessListActivity;
import com.zh.xfz.business.activity.CompanyActivity;
import com.zh.xfz.business.activity.CompanyOperationActivity;
import com.zh.xfz.business.activity.CreateBusinessActivity;
import com.zh.xfz.business.activity.CsrActivity;
import com.zh.xfz.business.activity.ForgetPasswordActivity;
import com.zh.xfz.business.activity.FriendDetailActivity;
import com.zh.xfz.business.activity.GroupActivity;
import com.zh.xfz.business.activity.GroupDetailActivity;
import com.zh.xfz.business.activity.GroupMemberInfoActivity;
import com.zh.xfz.business.activity.GroupMemberListActivity;
import com.zh.xfz.business.activity.HelpActivity;
import com.zh.xfz.business.activity.HelpDetailActivity;
import com.zh.xfz.business.activity.HelpItemActivity;
import com.zh.xfz.business.activity.InputPasswordActivity;
import com.zh.xfz.business.activity.LoginActivity;
import com.zh.xfz.business.activity.MainActivity;
import com.zh.xfz.business.activity.MyCompanyActivity;
import com.zh.xfz.business.activity.NewFriendActivity;
import com.zh.xfz.business.activity.PersonCardActivity;
import com.zh.xfz.business.activity.PersonDetailInfoActivity;
import com.zh.xfz.business.activity.SearchFriendActivity;
import com.zh.xfz.business.activity.SelectCompanyActivity;
import com.zh.xfz.business.activity.SettingActivity;
import com.zh.xfz.business.activity.SplashActivity;
import com.zh.xfz.business.activity.TestActivity;
import com.zh.xfz.business.activity.UpLoadPortraitActivity;
import com.zh.xfz.business.activity.UpdatePasswordActivity;
import com.zh.xfz.business.activity.UpdatePersonNameActivity;
import com.zh.xfz.business.activity.UpdatePhoneActivity;
import com.zh.xfz.business.activity.UpdatePhoneSmsActivity;
import com.zh.xfz.business.activity.ValidNoteActivity;
import com.zh.xfz.business.activity.WXLoginActivity;
import com.zh.xfz.business.activity.im.ConversationActivity;
import com.zh.xfz.business.activity.im.ConversationListActivity;
import com.zh.xfz.business.activity.im.SubConversationListActivity;
import com.zh.xfz.dagger.module.activity.AccountLoginModule;
import com.zh.xfz.dagger.module.activity.AccountSecurityModule;
import com.zh.xfz.dagger.module.activity.AddFriendModule;
import com.zh.xfz.dagger.module.activity.AddGroupMembersModule;
import com.zh.xfz.dagger.module.activity.AddPasswordModule;
import com.zh.xfz.dagger.module.activity.CompanyModule;
import com.zh.xfz.dagger.module.activity.CompanyOperationModule;
import com.zh.xfz.dagger.module.activity.CreateBusinessModule;
import com.zh.xfz.dagger.module.activity.FriendDetailModule;
import com.zh.xfz.dagger.module.activity.GroupDetailModule;
import com.zh.xfz.dagger.module.activity.GroupMemberInfoModule;
import com.zh.xfz.dagger.module.activity.GroupMemberListModule;
import com.zh.xfz.dagger.module.activity.GroupModule;
import com.zh.xfz.dagger.module.activity.HelpItemModule;
import com.zh.xfz.dagger.module.activity.HelpModule;
import com.zh.xfz.dagger.module.activity.InputPasswordModule;
import com.zh.xfz.dagger.module.activity.LoginModule;
import com.zh.xfz.dagger.module.activity.MainModule;
import com.zh.xfz.dagger.module.activity.MyCompanyModule;
import com.zh.xfz.dagger.module.activity.NewFriendModule;
import com.zh.xfz.dagger.module.activity.PersonCardModule;
import com.zh.xfz.dagger.module.activity.PersonDetailInfoModule;
import com.zh.xfz.dagger.module.activity.SearchFriendModule;
import com.zh.xfz.dagger.module.activity.SettingModule;
import com.zh.xfz.dagger.module.activity.UpLoadPortraitModule;
import com.zh.xfz.dagger.module.activity.UpdatePasswordModule;
import com.zh.xfz.dagger.module.activity.UpdatePersonNameModule;
import com.zh.xfz.dagger.module.activity.UpdatePhoneSmsModule;
import com.zh.xfz.dagger.module.activity.ValidNoteModule;
import com.zh.xfz.dagger.module.activity.WXLoginModule;

import core.app.zh.com.core.annotation.ActivityScope;
import core.app.zh.com.core.dagger.component.ActivityComponent;
import core.app.zh.com.core.dagger.module.EmptyModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @auther create by Administrator
 * DATE:2019/3/26 0026 11
 */
@Module(subcomponents = ActivityComponent.class)
public abstract class AllActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract SplashActivity contributeSplashActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {LoginModule.class})
    abstract LoginActivity contributeLoginActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {InputPasswordModule.class})
    abstract InputPasswordActivity contributeNormalLoginActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {AccountLoginModule.class})
    abstract AccountLoginActivity contributePhoneLoginActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract ForgetPasswordActivity contributeForgetPasswordActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {ValidNoteModule.class})
    abstract ValidNoteActivity contributeValidNoteActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {CreateBusinessModule.class})
    abstract CreateBusinessActivity contributeCreateBusinessActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainActivity contributeMainActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract ConversationListActivity contributeConversationListActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract ConversationActivity contributeConversationActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract SubConversationListActivity contributeSubConversationListActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {NewFriendModule.class})
    abstract NewFriendActivity contributeNewFriendActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {FriendDetailModule.class})
    abstract FriendDetailActivity contributeFriendDetailActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {SearchFriendModule.class})
    abstract SearchFriendActivity contributeSearchFriendActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {AddFriendModule.class})
    abstract AddFriendActivity contributeAddFriendActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {GroupModule.class})
    abstract GroupActivity contributeGroupActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {AddGroupMembersModule.class})
    abstract AddGroupMembersActivity contributeAddGroupMembersActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {GroupMemberListModule.class})
    abstract GroupMemberListActivity contributeGroupMemberListActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {SettingModule.class})
    abstract SettingActivity contributeSettingActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {UpdatePasswordModule.class})
    abstract UpdatePasswordActivity contributeUpdatePasswordActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {GroupDetailModule.class})
    abstract GroupDetailActivity contributeGroupDetailActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {GroupMemberInfoModule.class})
    abstract GroupMemberInfoActivity contributeGroupMemberInfoActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {AddPasswordModule.class})
    abstract AddPasswordActivity contributeAddPasswordActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract TestActivity contributeTestActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {PersonCardModule.class})
    abstract PersonCardActivity contributePersonCardActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {PersonDetailInfoModule.class})
    abstract PersonDetailInfoActivity contributePersonDetailInfoActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {UpLoadPortraitModule.class})
    abstract UpLoadPortraitActivity contributeUpLoadPortraitActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {UpdatePersonNameModule.class})
    abstract UpdatePersonNameActivity contributeUpdatePersonNameActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {CompanyModule.class})
    abstract CompanyActivity contributeCompanyActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {AccountSecurityModule.class})
    abstract AccountSecurityActivity contributeAccountSecurityActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract UpdatePhoneActivity contributeUpdatePhoneActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {UpdatePhoneSmsModule.class})
    abstract UpdatePhoneSmsActivity contributeUpdatePhoneSmsActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {MyCompanyModule.class})
    abstract MyCompanyActivity contributeMyCompanyActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {HelpModule.class})
    abstract HelpActivity contributeHelpActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {HelpItemModule.class})
    abstract HelpItemActivity contributeHelpItemActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract HelpDetailActivity contributeHelpDetailActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract CsrActivity contributeCsrActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract SelectCompanyActivity contributeSelectCompanyActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {WXLoginModule.class})
    abstract WXLoginActivity contributeWXLoginActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {EmptyModule.class})
    abstract BusinessListActivity contributeBusinessListActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {CompanyOperationModule.class})
    abstract CompanyOperationActivity contributeCompanyOperationActivityInjector();
}