package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.business.adapter.MyCompanyAdapter;
import com.zh.xfz.business.fragment.BusinessListFragment;
import com.zh.xfz.db.bean.Tenant;
import com.zh.xfz.utils.LoginHandler;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.view.MyPopupWindow;

/**
 * author : dayezi
 * data :2019/10/30
 * description:
 */
@Route(path = MyCompanyActivity.AROUTER_PATH)
@ToolbarLeft(menuId = R.menu.menu_my_company)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "我的企业")
public class MyCompanyActivity extends BaseActivity implements View.OnClickListener {
    public final static String AROUTER_PATH = "/main/MyCompanyActivity/";

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Inject
    MyCompanyAdapter adapter;

    @Inject
    LoginHandler loginHandler;

    @BindView(R.id.company_tv)
    TextView companyTv;

    @BindView(R.id.end_tv)
    TextView endTv;

    @Inject
    MyPopupWindow popupWindow;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

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

    @Override
    protected void onResume() {
        super.onResume();
        List<Tenant> tenantList = loginHandler.getTenantList();
        if (tenantList != null && !tenantList.isEmpty()) {
            companyTv.setText(loginHandler.getCurrentTenant().getTenantName());
            adapter.setNewData(tenantList);
            adapter.setOnItemClickListener((adapter, view, position) -> {
                Tenant firstTenant = loginHandler.getCurrentTenant();
                if (firstTenant.getId() != tenantList.get(position).getId()) {
                    loginHandler.updateTenant(firstTenant, tenantList.get(position));
                    companyTv.setText(tenantList.get(position).getTenantName());
                }
            });
        } else endTv.setVisibility(View.GONE);
    }

    @OnClick(R.id.company_ly)
    public void clickCompany() {
        ARouter.getInstance().build(SelectCompanyActivity.AROUTER_PATH).navigation();
    }

    @OnMenuOnclick
    public void menuClick(MenuItem item) {
        if (popupWindow.isShowing()) return;
        popupWindow.setBackgroundAlpha();
        popupWindow.showAtLocation(toolbar, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onClick(View v) {
        if (popupWindow.isShowing()) popupWindow.dismiss();
        switch (v.getId()) {
            case R.id.create_btn:
                ARouter.getInstance().build(CreateBusinessActivity.AROUTER_PATH).navigation();
                break;
            case R.id.company_list_btn:
                ARouter.getInstance().build(BusinessListActivity.AROUTER_PATH)
                        .withString(BusinessListActivity.FRAGMENT_CLASS_KEY, BusinessListFragment.class.getName())
                        .navigation();
                break;
        }
    }
}
