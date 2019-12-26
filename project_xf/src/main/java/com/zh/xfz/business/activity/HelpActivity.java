package com.zh.xfz.business.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.LocalApplication;
import com.zh.xfz.business.adapter.HelpAdapter;
import com.zh.xfz.business.fragment.HelpListFragment;
import com.zh.xfz.mvp.contract.HelpContract;
import com.zh.xfz.mvp.presenter.HelpPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;

import static com.zh.xfz.business.activity.BusinessListActivity.FRAGMENT_CLASS_BUNDLE_KEY;

/**
 * author : dayezi
 * data :2019/10/30
 * description:
 */
@Route(path = HelpActivity.AROUTER_PATH)
@ToolbarLeft(menuId = R.menu.menu_contact_me)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_light_blue)
@ToolbarTitle(backGroundColorId = R.color.white, title = "帮助", textColorId = R.color.textColorPrimary, statusBarColorId = R.color.translucent)
public class HelpActivity extends BaseActivity implements HelpContract.HelpApplicationUI {
    public final static String AROUTER_PATH = "/main/HelpActivity/";
    @Inject
    List<String> stringList;

    @Inject
    HelpAdapter adapter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Inject
    HelpPresenter helpPresenter;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_help;
    }

    @Override
    public void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString(HelpListFragment.APP_ID_KEY, HelpActivity.this.adapter.getData().get(position).getID());
            bundle.putString(HelpListFragment.TOOLBAR_TITLE_KEY, HelpActivity.this.adapter.getData().get(position).getName());
            ARouter.getInstance().build(BusinessListActivity.AROUTER_PATH)
                    .withBundle(FRAGMENT_CLASS_BUNDLE_KEY, bundle)
                    .withString(BusinessListActivity.FRAGMENT_CLASS_KEY, HelpListFragment.class.getName())
                    .navigation();
        });
        helpPresenter.getApps();
    }

    @OnMenuOnclick
    public void menuClick() {
        ARouter.getInstance().build(CsrActivity.AROUTER_PATH).navigation();
    }

    @Override
    public void successAppList(List<LocalApplication> applications) {
        adapter.setNewData(applications);
    }
}
