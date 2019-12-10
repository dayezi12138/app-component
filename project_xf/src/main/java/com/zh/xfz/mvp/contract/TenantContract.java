package com.zh.xfz.mvp.contract;

import com.zh.xfz.bean.fragment.BusinessBean;

import core.app.zh.com.core.base.IBaseListPresenter;
import core.app.zh.com.core.base.IBaseListView;

/**
 * author : dayezi
 * data :2019/12/9
 * description:
 */
public interface TenantContract {
    interface BusinessListUI extends IBaseListView<BusinessBean> {
        void clickItemSuccess();
    }

    interface Presenter extends IBaseListPresenter {
        void applyTenant(String tenantid, int type);
//        void getMyTenantListPage(String search);
    }
}
