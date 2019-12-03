package core.app.zh.com.core.view;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * author : dayezi
 * data :2019/4/16
 * description:
 */
public class MyPopupWindow extends PopupWindow {
    private final int position;
    private final int offsetX;
    private final int offsetY;
    private final float alpha;
    private final Activity activity;

    private MyPopupWindow(MyPopupWindow.Builder builder) {
        super(builder.view, builder.width, builder.height);
        this.position = builder.position;
        this.offsetX = builder.offsetX;
        this.offsetY = builder.offsetY;
        this.activity = builder.activity;
        this.alpha = builder.alpha;
        this.setFocusable(true);
        this.setOutsideTouchable(builder.outsideTouchable);
        setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        ColorDrawable dw = new ColorDrawable();
        setBackgroundDrawable(dw);
//        this.setBackgroundAlpha();//设置屏幕透明度
        if (builder.animationStyle != -1)
            this.setAnimationStyle(builder.animationStyle);
        // 在dismiss中恢复透明度
        setOnDismissListener(() -> {
            if (!builder.cancleDismissColor) {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1f;
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                activity.getWindow().setAttributes(lp);
            }
        });
    }

    /**
     * 设置添加屏幕的背景透明度
     * <p>
     * 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha() {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        lp.alpha = alpha;
        activity.getWindow().setAttributes(lp);
    }

    @Deprecated
    public void show() {
        this.showAtLocation(this.getContentView(), position, offsetX, offsetY);
    }

    public void show(View view) {
        this.showAtLocation(view, position, offsetX, offsetY);
    }

    public static class Builder {
        private View view;
        private Activity activity;
        private int width = ViewGroup.LayoutParams.MATCH_PARENT;
        private int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        private boolean outsideTouchable = true;
        private int position = Gravity.TOP;
        private int offsetX;
        private int offsetY;
        private int animationStyle = -1;
        private float alpha = 0.4f;
        private boolean cancleDismissColor = false;

        public Builder(View view, Activity activity) {
            this.view = view;
            this.activity = activity;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder cancleDismissColor(boolean cancleDismissColor) {
            this.cancleDismissColor = cancleDismissColor;
            return this;
        }

        public Builder outsideTouchable(boolean outsideTouchable) {
            this.outsideTouchable = outsideTouchable;
            return this;
        }

        public Builder position(int position) {
            this.position = position;
            return this;
        }

        public Builder offsetX(int offsetX) {
            this.offsetX = offsetX;
            return this;
        }

        public Builder offsetY(int offsetY) {
            this.offsetY = offsetY;
            return this;
        }

        public Builder animationStyle(int animationStyle) {
            this.animationStyle = animationStyle;
            return this;
        }

        public Builder alpha(float alpha) {
            this.alpha = alpha;
            return this;
        }

        public MyPopupWindow build() {
            return new MyPopupWindow(this);
        }
    }
}
