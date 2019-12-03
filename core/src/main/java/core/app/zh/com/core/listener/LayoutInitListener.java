package core.app.zh.com.core.listener;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @auther create by Administrator
 * DATE:2019/3/26 0026 11
 */
public interface LayoutInitListener {
    @NonNull
    int layoutId();

    void init();

    View myContentView();


    View beforeInit(LayoutInflater inflater, ViewGroup container);

    @Deprecated
    default boolean addToolbar() {
        return false;
    }

    default boolean useDefaultOption() {
        return true;
    }

}
