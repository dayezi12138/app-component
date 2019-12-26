package com.zh.xfz.mvp.presenter;

import com.zh.xfz.mvp.contract.HelpContract;
import com.zh.xfz.mvp.model.HelpModel;
import com.zh.xfz.utils.ResponseStatusUtils;

import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IBaseListView;

import static com.zh.xfz.constans.Constants.PAGEINDEX;
import static com.zh.xfz.constans.RequestParamsConstant.APP_ID;

/**
 * author : dayezi
 * data :2019/12/25
 * description:
 */
public class HelpPresenter extends BasePresenter<BaseView> implements HelpContract.Presenter {
    private HelpModel model;

    @Inject
    public HelpPresenter(HelpModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void onRefresh(Map<String, Object> params) {
        model.setPageIndex(PAGEINDEX);
        getArticlesByAppID(params);
    }

    @Override
    public void onLoad(Map<String, Object> params) {
        model.setPageIndex(model.getPageIndex() + 1);
        getArticlesByAppID(params);
    }


    private void getArticlesByAppID(Map<String, Object> params) {
        params.put(APP_ID, "123");
        model.getArticlesByAppID(params, data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode()) && view.get() instanceof IBaseListView) {
                IBaseListView baseListView = (IBaseListView) view.get();
                baseListView.listData(data.getRes(), model.getPageIndex() == PAGEINDEX ? true : false);
            } else view.get().showMsg(data.getMsg());
        });
    }

    @Override
    public void getApps() {
        model.getApps(data -> {
            if (ResponseStatusUtils.isNormalSuccess(data.getCode()) && view.get() instanceof HelpContract.HelpApplicationUI) {
                HelpContract.HelpApplicationUI ui = (HelpContract.HelpApplicationUI) view.get();
                ui.successAppList(data.getRes());
            } else view.get().showMsg(data.getMsg());

        });
    }
}
