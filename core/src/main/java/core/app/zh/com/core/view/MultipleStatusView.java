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
import core.app.zh.com.core.listener.LoadingOptionListener;


public class MultipleStatusView extends FrameLayout implements LoadingOptionListener.OptionContentViewListener {

    private int mEmptyViewId, mErrorViewId, mLoadingViewId, contentViewId = NULL_RESOURCE_ID;
    private View mEmptyView, mErrorView, mLoadingView, contentView;
    private OnClickListener onErrorClickListener;
    private OnClickListener onEmptyClickListener;
    private LayoutInflater mLayoutInflater;
    private final FrameLayout.LayoutParams DEFAULT_LAYOUT_PARAMS = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    private final ArrayList<Integer> mOtherIds = new ArrayList<>();
    private static final int NULL_RESOURCE_ID = -1;
    public static final int STATUS_CONTENT = 0x00;
    public static final int STATUS_LOADING = 0x01;
    public static final int STATUS_EMPTY = 0x02;
    public static final int STATUS_ERROR = 0x03;
    public static final int STATUS_NO_NETWORK = 0x04;
    private int mViewStatus = -1;

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
        contentViewId = a.getResourceId(R.styleable.MultipleStatusView_contentView, NULL_RESOURCE_ID);
        a.recycle();
        mLayoutInflater = LayoutInflater.from(getContext());
    }

//    @Override
//    protected void onFinishInflate() {
//        super.onFinishInflate();
//    }

    private void showContent() {
        changeViewStatus(STATUS_CONTENT);
        addView(contentView, 0, DEFAULT_LAYOUT_PARAMS);
//        for (int i = 0; i < ((ViewGroup) contentView).getChildCount(); i++) {
//            LogUtils.e(((ViewGroup) contentView).getChildAt(i));
//        }
//        showContentView();
    }

    private void showContentView() {
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            view.setVisibility(mOtherIds.contains(view.getId()) ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void optionContentView(View contentView) {
        this.contentView = contentView;
        if (this.contentView != null) showContent();
    }

    /**
     * 改变视图状态
     *
     * @param newViewStatus 新的视图状态
     */
    private void changeViewStatus(int newViewStatus) {
        if (mViewStatus == newViewStatus) {
            return;
        }
//        if (null != mViewStatusListener) {
//            mViewStatusListener.onChange(mViewStatus, newViewStatus);
//        }
        mViewStatus = newViewStatus;
    }
}