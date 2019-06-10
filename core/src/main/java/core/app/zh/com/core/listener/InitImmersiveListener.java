package core.app.zh.com.core.listener;

import android.support.annotation.ColorInt;
import android.view.View;

/**
 * author : dayezi
 * data :2019/6/5
 * description:
 */
public interface InitImmersiveListener {

    void init(boolean isDark, View view, @ColorInt int ColorId);
}
