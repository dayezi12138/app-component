package core.app.zh.com.core.listener;

import android.app.Activity;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;

/**
 * author : dayezi
 * data :2019/6/4
 * description:
 */
public interface SteepOptionListener {

    void steepOption(Activity activity, @ColorInt int color, @IntRange(from = 0, to = 255) int alpha);

}
