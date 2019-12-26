package com.zh.xfz.mvp.presenter;

import com.zh.xfz.mvp.contract.TenantContract;
import com.zh.xfz.mvp.model.TenantModel;
import com.zh.xfz.utils.LoginHandler;
import com.zh.xfz.utils.ResponseStatusUtils;

import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IBaseListView;
import core.app.zh.com.core.listener.observable.ObservableListener;

import static com.zh.xfz.constans.Constants.PAGEINDEX;

/**
 * author : dayezi
 * data :2019/12/9
 * description:
 */
public class TenantPresenter extends BasePresenter<BaseView> implements TenantContract.Presenter {

    private TenantModel model;
    @Inject
    LoginHandler loginHandler;

    @Inject
    public TenantPresenter(TenantModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void onRefresh(Map<String, Object> params) {
        model.setPageIndex(PAGEINDEX);
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
            if (ResponseStatusUtils.isNormalSuccess(data.getCode())) {
                loginHandler.saveTenant(data.getRes());
                model.getMyBaseModel().getMyActivity().finish();

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

    private void getTenantPageList(String search) {
        model.getTenantPageList(search, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode()) && view.get() instanceof TenantContract.BusinessListUI) {
                TenantContract.BusinessListUI ui = (TenantContract.BusinessListUI) view.get();
                ui.listData(data.getRes(), model.getPageIndex() == PAGEINDEX ? true : false);
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void onRefreshAllTenant(String search) {
        model.setPageIndex(PAGEINDEX);
        getTenantPageList(search);
    }

    @Override
    public void onLoadMoreAllTenant(String search) {
        model.setPageIndex(model.getPageIndex() + 1);
        getTenantPageList(search);
    }

    @Override
    public void onRefreshApplyList(String id, String type) {
        model.setPageIndex(PAGEINDEX);
        getApplyList(id, type);

    }

    @Override
    public void onLoadMoreApplyList(String id, String type) {
        model.setPageIndex(model.getPageIndex() + 1);
        getApplyList(id, type);
    }

    @Override
    public void getTenantUser(String tenantid) {
        model.getTenantUser(tenantid, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode()) && view.get() instanceof IBaseListView) {
                IBaseListView iBaseListView = (IBaseListView) view.get();
                iBaseListView.listData(data.getRes(), true);
            } else view.get().showMsg(data.getMsg());

        });
    }

    @Override
    public void applyOperate(int type, String id) {
        model.applyOperate(String.valueOf(type), id, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode()) && view.get() instanceof TenantContract.ApplyTenantUI) {
                TenantContract.ApplyTenantUI applyTenantUI = (TenantContract.ApplyTenantUI) view.get();
                applyTenantUI.applyStatus(type);
            } else view.get().showMsg(data.getMsg());
        });
    }


    private void getApplyList(String id, String type) {
        model.getApplyList(id, type, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode()) && view.get() instanceof IBaseListView) {
                IBaseListView iBaseListView = (IBaseListView) view.get();
                iBaseListView.listData(data.getRes(), model.getPageIndex() == PAGEINDEX ? true : false);
            } else view.get().showMsg(data.getMsg());
        });
    }
}
