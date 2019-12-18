package com.zh.xfz.mvp.contract;

import com.zh.xfz.bean.activity.Industry;
import com.zh.xfz.bean.fragment.BusinessBean;

import java.util.List;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IBaseListPresenter;
import core.app.zh.com.core.base.IBaseListView;

/**
 * author : dayezi
 * data :2019/12/9
 * description:公司模块
 */
public interface TenantContract {
    interface BusinessListUI extends IBaseListView<BusinessBean> {
        void clickItemSuccess();
    }

    interface CreateBusinessUI extends BaseView {
        void industryList(List<Industry> dataList);

        void createSuccess();
    }

    interface Presenter extends IBaseListPresenter {
        void applyTenant(String tenantid, int type);

        void createTenant(String tenantName, String industryIDs);

        void existTenantName(String tenantName);

        void getIndustry();
    }
}
