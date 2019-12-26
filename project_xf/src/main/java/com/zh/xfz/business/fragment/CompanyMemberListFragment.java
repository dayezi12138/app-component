package com.zh.xfz.business.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.fragment.TenantMember;
import com.zh.xfz.business.adapter.CompanyMemberListAdapter;
import com.zh.xfz.mvp.presenter.TenantPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.MyBaseAdapter;

/**
 * author : dayezi
 * data :2019/12/23
 * description:
 */
@Route(path = CompanyMemberListFragment.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color)
public class CompanyMemberListFragment extends SmartBaseListFragment<TenantMember> {
    public final static String AROUTER_PATH = "/fragment/CompanyMemberListFragment/";
    public static final String TENANT_KEY = "TENANT_KEY";
    public static final String TENANT_NAME_KEY = "TENANT_NAME_KEY";
    @Inject
    CompanyMemberListAdapter adapter;

    @Inject
    TenantPresenter tenantPresenter;

    String tenantId;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public CompanyMemberListFragment() {

    }

    public static CompanyMemberListFragment newInstance(Bundle bundle) {
        CompanyMemberListFragment fragment = new CompanyMemberListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initRefresh() {
        tenantId = getArguments().getString(TENANT_KEY);
        tenantPresenter.getTenantUser(tenantId);
        TextView textView = toolbar.findViewById(com.zh.api.R.id.tool_bar_text_view_id_x);
        textView.setText(getArguments().getString(TENANT_NAME_KEY));
    }

    @Override
    public MyBaseAdapter getMyBaseAdapter() {
        return adapter;
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public boolean canRefresh() {
        return !super.canRefresh();
    }
}
