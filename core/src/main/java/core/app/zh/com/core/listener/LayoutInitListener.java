package core.app.zh.com.core.listener;

import android.support.annotation.NonNull;

/**
 * @auther create by Administrator
 * DATE:2019/3/26 0026 11
 */
public interface LayoutInitListener {
    @NonNull  int layoutId();

    void init() ;
}
