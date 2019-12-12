package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.Account;
import com.zh.xfz.business.adapter.MyCompanyAdapter;
import com.zh.xfz.utils.LoginUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/10/30
 * description:
 */
@Route(path = MyCompanyActivity.AROUTER_PATH)
//@ToolbarLeft(menuId = R.menu.menu_sure)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "我的企业")
public class MyCompanyActivity extends BaseActivity {
    public final static String AROUTER_PATH = "/main/MyCompanyActivity/";

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Inject
    MyCompanyAdapter adapter;

    @BindView(R.id.company_tv)
    TextView companyTv;

    @BindView(R.id.end_tv)
    TextView endTv;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_my_company;
    }

    @Override
    public void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        if (LoginUtils.getTenant() != null) companyTv.setText(LoginUtils.TENANT.getTenantName());
        Account account = LoginUtils.ACCOUNT;
        if (account != null && account.getTenant() != null && account.getTenant().size() > 0) {
            adapter.setNewData(account.getTenant());
            adapter.setOnItemClickListener((adapter, view, position) -> {
                Account.TenantBean tenantBean = LoginUtils.ACCOUNT.getTenant().get(position);
                LoginUtils.setTenant(tenantBean);
                companyTv.setText(LoginUtils.TENANT.getTenantName());
            });
        } else endTv.setVisibility(View.GONE);
    }

    @OnClick(R.id.company_ly)
    public void clickCompany() {
        ARouter.getInstance().build(SelectCompanyActivity.AROUTER_PATH).navigation();
    }
}
