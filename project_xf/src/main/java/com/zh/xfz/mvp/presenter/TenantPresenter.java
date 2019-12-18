package com.zh.xfz.mvp.presenter;

import com.zh.xfz.mvp.contract.TenantContract;
import com.zh.xfz.mvp.model.TenantModel;
import com.zh.xfz.utils.ResponseStatusUtils;

import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.listener.observable.ObservableListener;

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
            if (ResponseStatusUtils.isNormalSuccess(data.getCode()) && view.get() instanceof TenantContract.BusinessListUI) {
                TenantContract.BusinessListUI ui = (TenantContract.BusinessListUI) view.get();
                ui.listData(data.getRes(), refresh);
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void applyTenant(String tenantid, int type) {
        model.applyTenant(tenantid, type, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode()) && view.get() instanceof TenantContract.BusinessListUI) {
                TenantContract.BusinessListUI ui = (TenantContract.BusinessListUI) view.get();
                ui.clickItemSuccess();
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void createTenant(String tenantName, String industryIDs) {
        model.createTenant(tenantName, industryIDs, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode()) && view.get() instanceof TenantContract.CreateBusinessUI) {
                TenantContract.CreateBusinessUI ui = (TenantContract.CreateBusinessUI) view.get();
                ui.createSuccess();
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void existTenantName(String tenantName) {
        model.existTenantName(tenantName, new ObservableListener.SuccessListener() {
            @Override
            public void onSuccess(Object o) {

            }
        });
    }

    @Override
    public void getIndustry() {
        model.getIndustry(data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode()) && view.get() instanceof TenantContract.CreateBusinessUI) {
                TenantContract.CreateBusinessUI ui = (TenantContract.CreateBusinessUI) view.get();
                ui.industryList(data.getRes());
            } else view.get().showMsg(data.getMsg());
        });
    }
}
