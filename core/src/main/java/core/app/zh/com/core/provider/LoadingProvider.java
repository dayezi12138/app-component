package core.app.zh.com.core.provider;

import android.view.LayoutInflater;
import android.view.View;

import core.app.zh.com.core.bean.LoadingOptionBean;

/**
 * author : dayezi
 * data :2019/10/9
 * description:
 */
public abstract class LoadingProvider {
    private View loadingView;
    private LoadingOptionBean loadingOptionBean;

    public LoadingProvider(LoadingOptionBean loadingOptionBean) {
        this.loadingOptionBean = loadingOptionBean;
        loadingView = LayoutInflater.from(loadingOptionBean.getActivity()).inflate(loadingOptionBean.getLayoutId(), null);
    }

    public View getLoadingView() {
        return loadingView;
    }

    public boolean isNeedInflate() {
        return loadingOptionBean.isNeedLoading();
    }

    public abstract void showLoading();

    public abstract void hideLoading();
}
