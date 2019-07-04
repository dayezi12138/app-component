package core.app.zh.com.core.listener;

import android.view.View;

/**
 * author : dayezi
 * data :2019/7/4
 * description:
 */
public interface LoadingListener {


    View loadingView();

    void showLoading();

    void hideLoading();

    default boolean handlerContentView() {
        return false;
    }


}
