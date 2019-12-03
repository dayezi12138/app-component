package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.business.adapter.MyCompanyAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/10/30
 * description:
 */
@Route(path = MyCompanyActivity.AROUTER_PATH)
@ToolbarLeft(menuId = R.menu.menu_sure)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white, title = "我")
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "我的企业")
public class MyCompanyActivity extends BaseActivity {
    public final static String AROUTER_PATH = "/main/MyCompanyActivity/";

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Inject
    MyCompanyAdapter adapter;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_my_company;
    }

    @Override
    public void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
