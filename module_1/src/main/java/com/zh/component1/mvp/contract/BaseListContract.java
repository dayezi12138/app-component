package com.zh.component1.mvp.contract;

import java.util.List;
import java.util.Map;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;

/**
 * author : dayezi
 * data :2019/7/8
 * description:
 */
public interface BaseListContract {

    interface BaseListView<T> extends BaseView {
        void success(List<T> dataList);
    }

    interface BaseListPresenter extends IPresenter {
        void onRefresh(Map<String, Object> requestMap);

        void onLoadMore(Map<String, Object> requestMap);
    }
}
