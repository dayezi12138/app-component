package core.app.zh.com.core.listener;

import android.support.annotation.LayoutRes;

import core.app.zh.com.core.R;

/**
 * author : dayezi
 * data :2019/9/12
 * description:
 */
public interface LoadingOptionListener {
    default @LayoutRes
    int loadingLayoutResId() {
        return R.layout.layout_loading;
    }


//    default boolean isAdd() {
//        return false;
//    }
}
