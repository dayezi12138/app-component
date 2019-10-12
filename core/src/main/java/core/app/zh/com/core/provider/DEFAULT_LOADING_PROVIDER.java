package core.app.zh.com.core.provider;

import core.app.zh.com.core.bean.LoadingOptionBean;

/**
 * author : dayezi
 * data :2019/10/9
 * description:
 */
public class DEFAULT_LOADING_PROVIDER extends LoadingProvider {
    public DEFAULT_LOADING_PROVIDER(LoadingOptionBean loadingOptionBean) {
        super(loadingOptionBean);

    }

    @Override
    public void showLoading() {
//        MultipleStatusView statusView = getLoadingView().findViewById(R.id.ll_loading);
//        statusView.showLoadingView();
    }

    @Override
    public void hideLoading() {
//        MultipleStatusView statusView = getLoadingView().findViewById(R.id.ll_loading);
//        statusView.hideLoadingView();
    }
}
