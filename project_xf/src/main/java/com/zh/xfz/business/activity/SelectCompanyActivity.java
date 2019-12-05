package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.Account;
import com.zh.xfz.business.adapter.MyCompanyAdapter;
import com.zh.xfz.utils.LoginUtils;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/12/4
 * description:
 */
@Route(path = SelectCompanyActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white, title = "切换公司")
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "姓名")
public class SelectCompanyActivity extends BaseActivity {
    public final static String AROUTER_PATH = "/main/SelectCompanyActivity/";

    private MyCompanyAdapter adapter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.recycler_;
    }

    @Override
    public void init() {
        adapter = new MyCompanyAdapter(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        if (LoginUtils.ACCOUNT != null && LoginUtils.ACCOUNT.getTenant() != null && LoginUtils.ACCOUNT.getTenant().size() > 0) {
            adapter.setNewData(LoginUtils.ACCOUNT.getTenant());
            adapter.setOnItemClickListener((adapter, view, position) -> {
                Account.TenantBean tenantBean = LoginUtils.ACCOUNT.getTenant().get(position);
                LoginUtils.setTenant(tenantBean);
                finish();
            });
        }
    }
}
