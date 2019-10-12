package core.app.zh.com.core.listener;

import android.support.annotation.LayoutRes;

/**
 * author : dayezi
 * data :2019/9/12
 * description:
 */
public interface LoadingOptionListener {
    default @LayoutRes
    int loadingLayoutResId() {
        return -1;
    }


//    default boolean isAdd() {
//        return false;
//    }
}
