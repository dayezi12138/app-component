package com.zh.xfz.mvp.contract;

import com.zh.xfz.bean.activity.LocalApplication;

import java.util.List;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IBaseListPresenter;

/**
 * author : dayezi
 * data :2019/12/25
 * description:
 */
public interface HelpContract {
    interface HelpApplicationUI extends BaseView {
        void successAppList(List<LocalApplication> applications);
    }

    interface Presenter extends IBaseListPresenter {
        void getApps();
    }
}
