package com.zh.xfz.mvp.contract.activity;

import com.zh.xfz.bean.activity.Industry;

import java.util.List;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;

/**
 * author : dayezi
 * data :2019/7/29
 * description:
 */
@Deprecated
public interface BusinessContract {

    interface BusinessUI extends BaseView {
        void success(List<Industry> dataList);

        void successSubmit();
    }

    interface Presenter extends IPresenter {

        void getIndustry();

        void createTenant(String tenantName, String industryIDs);

        void existTenantName(String tenantName, String industryIDs);
    }
}
