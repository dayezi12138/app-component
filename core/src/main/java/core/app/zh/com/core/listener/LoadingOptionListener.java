package core.app.zh.com.core.listener;

import android.view.View;

/**
 * author : dayezi
 * data :2019/10/25
 * description:
 */
public interface LoadingOptionListener<T extends View> {

    T getLoadingView();

    default void addContentView(View contentView) {
        if (getLoadingView() instanceof OptionContentViewListener) {
            OptionContentViewListener listener = (OptionContentViewListener) getLoadingView();
            listener.optionContentView(contentView);
        }
    }

    interface OptionContentViewListener {
        void optionContentView(View contentView);
    }
}
