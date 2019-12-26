package com.zh.xfz.business.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.fragment.ApplyTenant;
import com.zh.xfz.business.adapter.ApplyTenantAdapter;
import com.zh.xfz.mvp.contract.TenantContract.ApplyTenantUI;
import com.zh.xfz.mvp.presenter.TenantPresenter;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;

import static com.zh.xfz.constans.Constants.APPLY_TYPE_AGREE;
import static com.zh.xfz.constans.Constants.APPLY_TYPE_REFUSE;

/**
 * author : dayezi
 * data :2019/12/24
 * description:
 */
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "申请列表")
public class ApplyTenantFragment extends SmartBaseListFragment<ApplyTenant> implements MaterialDialog.SingleButtonCallback, ApplyTenantUI {

    public static final String TYPE_KEY = "TYPE_KEY";
    public static final String TYPE_ID_KEY = "TYPE_ID_KEY";

    @Inject
    ApplyTenantAdapter applyTenantAdapter;
    @Inject
    TenantPresenter tenantPresenter;

    @Inject
    MaterialDialog dialog;

    private String type;
    private String tenantId;


    @SuppressLint("ValidFragment")
    private ApplyTenantFragment() {
    }

    public static ApplyTenantFragment newInstance(Bundle bundle) {
        ApplyTenantFragment fragment = new ApplyTenantFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initRefresh() {
        type = getArguments().getString(TYPE_KEY);
        tenantId = getArguments().getString(TYPE_ID_KEY);
        applyTenantAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (applyTenantAdapter.getData().get(position).getStutus() == ApplyTenantAdapter.ApplyTenantType.WAITING.getStatus()) {
                dialog.show();
                return;
            }
        });
    }

    @Override
    public MyBaseAdapter getMyBaseAdapter() {
        return applyTenantAdapter;
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        tenantPresenter.onLoadMoreApplyList(tenantId, type);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        tenantPresenter.onRefreshApplyList(tenantId, type);
    }

    @Override
    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
        if (which != DialogAction.NEUTRAL) {
            int status = which == DialogAction.POSITIVE ? APPLY_TYPE_AGREE : APPLY_TYPE_REFUSE;
            tenantPresenter.applyOperate(status, tenantId);
        }
        dialog.dismiss();
    }

    @Override
    public void applyStatus(int status) {
        refreshLayout.autoRefresh();
    }
}
