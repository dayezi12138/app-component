package core.app.zh.com.core.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

import core.app.zh.com.core.R;


public class ClearEditTextView extends android.support.v7.widget.AppCompatEditText implements View.OnFocusChangeListener, TextWatcher {

    //右边的删除按钮
    private Drawable mClearDrawable;

    public ClearEditTextView(Context context) {
        this(context, null);
    }

    public ClearEditTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray attr = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ClearEditTextView, defStyleAttr, 0);
        Drawable drawable = attr.getDrawable(R.styleable.ClearEditTextView_clearImage);
        mClearDrawable = drawable != null ? drawable : getResources().getDrawable(R.drawable.ic_clear);
        //设置删除按钮的边界
        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        //默认隐藏删除按钮
        setClearIcon(false);

        //监听EditText焦点变化，以根据text长度控制删除按钮的显示、隐藏
        setOnFocusChangeListener(this);
        //监听文本内容变化
        addTextChangedListener(this);
    }

    /**
     * 控制EditText右边制删除按钮的显示、隐藏
     */
    private void setClearIcon(boolean isShow) {
        Drawable rightDrawable = isShow ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1],
                rightDrawable, getCompoundDrawables()[3]);
    }

    /**
     * 有焦点，并文本长度大于0则显示删除按钮
     *
     * @param v
     * @param hasFocus
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setClearIcon(getText().length() > 0);
        } else {
            setClearIcon(false);
        }
    }

    /**
     * 文本内容变化时回调
     * 当文本长度大于0时显示删除按钮， 否则隐藏
     *
     * @param text
     * @param start
     * @param lengthBefore
     * @param lengthAfter
     */
    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        setClearIcon(text.length() > 0);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (onChangeEditextListener != null)
            onChangeEditextListener.onChangeValue(s.toString());
    }

    /**
     * 通过手指的触摸位置模式删除按钮的点击事件
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getCompoundDrawables()[2] != null) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                boolean xTouchable = event.getX() > (getWidth() - getPaddingRight() - mClearDrawable.getIntrinsicWidth())
                        && (event.getX() < (getWidth() - getPaddingRight()));

                boolean yTouchable = event.getY() > (getHeight() - mClearDrawable.getIntrinsicHeight()) / 2
                        && event.getY() < (getHeight() + mClearDrawable.getIntrinsicHeight()) / 2;

                //清除文本
                if (xTouchable && yTouchable) {
                    setText("");
                    if (onClickImageListener != null) onClickImageListener.clear();
                }
            }
        }
        return super.onTouchEvent(event);
    }


    /**
     * EditText抖动
     *
     * @param counts
     * @return
     */
    public void startShake(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(500);
        startAnimation(translateAnimation);
    }

    /**
     * @param recourseId
     */
    public void setmClearDrawableRecourse(@DrawableRes int recourseId) {
        mClearDrawable = getResources().getDrawable(recourseId);
        //设置删除按钮的边界
        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        //默认隐藏删除按钮
        setClearIcon(false);
        postInvalidate();
    }

    private OnChangeEditextListener onChangeEditextListener;
    private OnClickImageListener onClickImageListener;

    public void setOnClickImageListener(OnClickImageListener onClickImageListener) {
        this.onClickImageListener = onClickImageListener;
    }

    public void setOnChangeEditextListener(OnChangeEditextListener onChangeEditextListener) {
        this.onChangeEditextListener = onChangeEditextListener;
    }

    public interface OnChangeEditextListener {
        void onChangeValue(String value);
    }

    public interface OnClickImageListener {
        void clear();
    }
}