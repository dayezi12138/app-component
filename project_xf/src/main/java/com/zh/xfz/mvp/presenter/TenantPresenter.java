package com.zh.xfz.mvp.presenter;

import com.blankj.utilcode.util.LogUtils;
import com.zh.xfz.mvp.contract.TenantContract;
import com.zh.xfz.mvp.model.TenantModel;

import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.base.BaseView;

/**
 * author : dayezi
 * data :2019/12/9
 * description:
 */
public class TenantPresenter extends BasePresenter<BaseView> implements TenantContract.Presenter {

    private TenantModel model;

    @Inject
    public TenantPresenter(TenantModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void onRefresh(Map<String, Object> params) {
        model.setPageIndex(1);
        getMyTenantListPage(params, true);
    }

    @Override
    public void onLoad(Map<String, Object> params) {
        model.setPageIndex(model.getPageIndex() + 1);
        getMyTenantListPage(params, false);
    }

    private void getMyTenantListPage(Map<String, Object> params, boolean refresh) {
        model.getMyTenantListPage(params, data -> {
            LogUtils.e(view.get() instanceof TenantContract.BusinessListUI);
            if (data.getCode() == 0 && view.get() instanceof TenantContract.BusinessListUI) {
                TenantContract.BusinessListUI ui = (TenantContract.BusinessListUI) view.get();
                ui.listData(data.getRes(), refresh);
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void applyTenant(String tenantid, int type) {
        model.applyTenant(tenantid, type, data -> {
            if (data.getCode() == 0 && view.get() instanceof TenantContract.BusinessListUI) {
                TenantContract.BusinessListUI ui = (TenantContract.BusinessListUI) view.get();
                ui.clickItemSuccess();
            } else view.get().showMsg(data.getMsg());
        });
    }
}
