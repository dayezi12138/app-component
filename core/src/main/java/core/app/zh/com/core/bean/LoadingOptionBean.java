package core.app.zh.com.core.bean;

import android.support.annotation.LayoutRes;

import core.app.zh.com.core.R;
import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/10/9
 * description:
 */
public class LoadingOptionBean {

    @LayoutRes
    private int layoutId = R.layout.loading_view;

    private boolean needLoading = true;
    private BaseActivity activity;

//    public LoadingOptionBean() {
//    }

//    public LoadingOptionBean(boolean needLoading) {
//        this.needLoading = needLoading;
//    }

    public LoadingOptionBean(boolean needLoading, BaseActivity activity) {
        this.needLoading = needLoading;
        this.activity = activity;
    }

    public LoadingOptionBean(int layoutId, boolean needLoading, BaseActivity activity) {
        this.layoutId = layoutId;
        this.needLoading = needLoading;
        this.activity = activity;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public boolean isNeedLoading() {
        return needLoading;
    }

    public void setNeedLoading(boolean needLoading) {
        this.needLoading = needLoading;
    }

    public BaseActivity getActivity() {
        return activity;
    }

    public void setActivity(BaseActivity activity) {
        this.activity = activity;
    }
}
