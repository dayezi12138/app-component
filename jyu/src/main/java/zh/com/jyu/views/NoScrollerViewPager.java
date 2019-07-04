package zh.com.jyu.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * author : dayezi
 * data :2019/4/18
 * description:
 */
public class NoScrollerViewPager extends ViewPager {
    public NoScrollerViewPager(@NonNull Context context) {
        super(context);
    }

    public NoScrollerViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isScroll) {
            return super.onTouchEvent(ev);
        } else {
            return true;// 可行,消费,拦截事件
        }
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isScroll) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }

    public void setScroll(boolean scroll) {
        isScroll = scroll;
    }

    private boolean isScroll = false;
}
