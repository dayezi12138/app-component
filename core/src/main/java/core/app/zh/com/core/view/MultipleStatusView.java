package core.app.zh.com.core.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;

import core.app.zh.com.core.R;


public class MultipleStatusView extends FrameLayout {

    private int mEmptyViewId, mErrorViewId, mLoadingViewId, contentViewId;
    private View mEmptyView, mErrorView, mLoadingView, contentView;
    private OnClickListener onErrorClickListener;
    private OnClickListener onEmptyClickListener;
    private LayoutInflater mLayoutInflater;
    private final FrameLayout.LayoutParams DEFAULT_LAYOUT_PARAMS = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    private final ArrayList<Integer> mOtherIds = new ArrayList<>();

    public MultipleStatusView(Context context) {
        this(context, null);
    }

    public MultipleStatusView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultipleStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MultipleStatusView, 0, 0);
        mEmptyViewId = a.getResourceId(R.styleable.MultipleStatusView_emptyView, R.layout.empty_view);
        mErrorViewId = a.getResourceId(R.styleable.MultipleStatusView_errorView, R.layout.error_view);
        mLoadingViewId = a.getResourceId(R.styleable.MultipleStatusView_loadingView, R.layout.loading_view);
        contentViewId = a.getResourceId(R.styleable.MultipleStatusView_contentView, -1);
        a.recycle();
        mLayoutInflater = LayoutInflater.from(getContext());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
//        showContent();
    }

    public void showLoadingView() {
        if (mLoadingViewId != -1) {
            showLoading(null == mLoadingView ? inflateView(mLoadingViewId) : mLoadingView, DEFAULT_LAYOUT_PARAMS);
        }
    }

    /**
     * 显示加载中视图
     *
     * @param layoutId     自定义布局文件
     * @param layoutParams 布局参数
     */
    public void showLoading(int layoutId, ViewGroup.LayoutParams layoutParams) {
        if (layoutId != -1) {
            showLoading(null == mLoadingView ? inflateView(layoutId) : mLoadingView, layoutParams);
        }
    }

    /**
     * 显示加载中视图
     *
     * @param view         自定义视图
     * @param layoutParams 布局参数
     */
    public final void showLoading(View view, ViewGroup.LayoutParams layoutParams) {
        if (null == mLoadingView) {
            mLoadingView = view;
            mLoadingView.setId(R.id.loading_view_id_x);
            mOtherIds.add(mLoadingView.getId());
            addView(mLoadingView, 0, layoutParams);
        }
        showViewById(mLoadingView.getId());
    }

//    public void showContent(View view) {
//        clear(contentView);
//        contentView = view;
//        addView(contentView, 0, DEFAULT_LAYOUT_PARAMS);
//        contentView.setId(R.id.content_view_id_x);
//        showViewById(contentView.getId());
//    }

    private void showViewById(int viewId) {
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            view.setVisibility(view.getId() == viewId ? View.VISIBLE : View.GONE);
        }
    }

//    private void showContent() {
//        if (contentViewId != -1) {
//            contentView = mLayoutInflater.inflate(contentViewId, null);
//            contentView.setId(R.id.content_view_id_x);
//            addView(contentView, 0, DEFAULT_LAYOUT_PARAMS);
//            showContentView(contentView.getId());
//        }
//    }

    private View inflateView(int layoutId) {
        return mLayoutInflater.inflate(layoutId, null);
    }

//    private void showContentView(int viewId) {
//        final int childCount = getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View view = getChildAt(i);
//            view.setVisibility(view.getId() == viewId ? View.GONE : View.VISIBLE);
//        }
//
//    }

    private void clear(View... views) {
        if (null == views) {
            return;
        }
        try {
            for (View view : views) {
                if (null != view) {
                    removeView(view);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clear(mEmptyView, mLoadingView, mErrorView);
        if (!mOtherIds.isEmpty()) {
            mOtherIds.clear();
        }
    }

}