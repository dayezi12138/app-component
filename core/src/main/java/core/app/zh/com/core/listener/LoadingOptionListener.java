package core.app.zh.com.core.listener;

import android.view.View;

/**
 * author : dayezi
 * data :2019/10/25
 * description:添加加载布局接口
 */
public interface LoadingOptionListener<T extends View> {

    T getLoadingView();

    void showLoading();

    void hideLoading();

    void showEmptyView();

    void showNoNetWork();

    void showError();

    enum LoadingOperation {
        INIT_VIEW, SHOW, HIDE, NO_NET_WORK_ERROR, ERROR,EMPTY
    }
}
