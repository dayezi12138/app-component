package core.app.zh.com.core.bean;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;

/**
 * author : dayezi
 * data :2019/12/9
 * description:
 */
public class FragmentInfo {
    private Fragment fragment;
    private @IdRes
    int fragmentLayoutId;
    private int width = ViewGroup.LayoutParams.MATCH_PARENT;
    private int height = ViewGroup.LayoutParams.MATCH_PARENT;

    public int getFragmentLayoutId() {
        return fragmentLayoutId;
    }

    public void setFragmentLayoutId(int fragmentLayoutId) {
        this.fragmentLayoutId = fragmentLayoutId;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
