package core.app.zh.com.core.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.LogUtils;

import java.util.ArrayList;

import core.app.zh.com.core.R;


public class MultipleStatusView extends FrameLayout {

    private int mEmptyViewId, mErrorViewId, mLoadingViewId, contentViewId = NULL_RESOURCE_ID;
    private View mEmptyView, mErrorView, mLoadingView, contentView;
    private OnClickListener onErrorClickListener;
    private OnClickListener onEmptyClickListener;
    private LayoutInflater mLayoutInflater;
    private final FrameLayout.LayoutParams DEFAULT_LAYOUT_PARAMS = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    private final ArrayList<Integer> mOtherIds = new ArrayList<>();
    private static final int NULL_RESOURCE_ID = -1;

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
//        contentViewId = a.getResourceId(R.styleable.MultipleStatusView_contentView, -1);
        a.recycle();
        mLayoutInflater = LayoutInflater.from(getContext());
    }

    /**
     * 显示加载中视图
     */
    public void showLoading() {
        showLoading(mLoadingViewId, DEFAULT_LAYOUT_PARAMS);
    }

    /**
     * 显示加载中视图
     *
     * @param layoutId     自定义布局文件
     * @param layoutParams 布局参数
     */
    public final void showLoading(int layoutId, ViewGroup.LayoutParams layoutParams) {
        showLoading(null == mLoadingView ? inflateView(layoutId) : mLoadingView, layoutParams);
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
            addView(mLoadingView, 1, layoutParams);
        }
        showViewById(mLoadingView.getId());
    }

    private void showViewById(int viewId) {
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            LogUtils.e(view.getId() == viewId);
            view.setVisibility(view.getId() == viewId ? View.VISIBLE : View.GONE);
        }
    }

    private View inflateView(int layoutId) {
        return mLayoutInflater.inflate(layoutId, null);
    }

    /**
     * 显示内容视图
     */
    public void showContent() {
//        if (null == contentView && contentViewId != NULL_RESOURCE_ID) {
//            contentView = mLayoutInflater.inflate(contentViewId, null);
//            addView(contentView, 0, DEFAULT_LAYOUT_PARAMS);
//        }
        showContentView();
    }

//    public void contentViewId(@LayoutRes int layoutId) {
//        contentViewId = layoutId;
//        if (null == contentView && contentViewId != NULL_RESOURCE_ID) {
//            contentView = mLayoutInflater.inflate(contentViewId, null);
//            addView(contentView, 0, DEFAULT_LAYOUT_PARAMS);
//        }
//    }

    public void contentView(View view) {
        this.contentView = view;
        contentView.setId(R.id.content_view_id_x);
        addView(contentView, 0, DEFAULT_LAYOUT_PARAMS);
//        if (null == contentView) {
//            contentView = mLayoutInflater.inflate(contentViewId, null);
//            addView(contentView, 0, DEFAULT_LAYOUT_PARAMS);
//        }
    }

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

    private void showContentView() {
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            view.setVisibility(mOtherIds.contains(view.getId()) ? View.GONE : View.VISIBLE);
        }
    }
}