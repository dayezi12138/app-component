package com.zh.xfz.business.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.business.fragment.ApplyTenantFragment;
import com.zh.xfz.business.fragment.CompanyMemberListFragment;
import com.zh.xfz.db.bean.Tenant;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;

import static com.zh.xfz.business.fragment.CompanyMemberListFragment.TENANT_KEY;
import static com.zh.xfz.business.fragment.CompanyMemberListFragment.TENANT_NAME_KEY;
import static com.zh.xfz.constans.Constants.APPLY_TYPE_USER;

/**
 * author : dayezi
 * data :2019/12/23
 * description:
 */
@Route(path = CompanyOperationActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color)
public class CompanyOperationActivity extends BaseActivity {
    public final static String AROUTER_PATH = "/main/CompanyOperationActivity/";
    public final static String TOOLBAR_TITLE_KEY = "TOOLBAR_TITLE_KEY";
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Autowired(name = TOOLBAR_TITLE_KEY)
    Tenant tenant;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_company_operation;
    }

    @Override
    public void init() {
        TextView textView = toolbar.findViewById(com.zh.api.R.id.tool_bar_text_view_id_x);
        textView.setText(tenant.getTenantName());
    }

    @OnClick(R.id.company_member_ly)
    public void clickCompanyMember() {
        Bundle bundle = new Bundle();
        bundle.putString(TENANT_KEY, String.valueOf(tenant.getTenantId()));
        bundle.putString(TENANT_NAME_KEY, tenant.getTenantName());
        ARouter.getInstance().build(BusinessListActivity.AROUTER_PATH)
                .withString(BusinessListActivity.FRAGMENT_CLASS_KEY, CompanyMemberListFragment.class.getName())
                .withBundle(BusinessListActivity.FRAGMENT_CLASS_BUNDLE_KEY, bundle)
                .navigation();
    }

    @OnClick(R.id.apply_member_ly)
    public void clickApplyMember() {
        Bundle bundle = new Bundle();
        bundle.putString(ApplyTenantFragment.TYPE_KEY, String.valueOf(APPLY_TYPE_USER));
        bundle.putString(ApplyTenantFragment.TYPE_ID_KEY, String.valueOf(tenant.getTenantId()));

        ARouter.getInstance().build(BusinessListActivity.AROUTER_PATH)
                .withString(BusinessListActivity.FRAGMENT_CLASS_KEY, ApplyTenantFragment.class.getName())
                .withBundle(BusinessListActivity.FRAGMENT_CLASS_BUNDLE_KEY, bundle)
                .navigation();
    }

}
