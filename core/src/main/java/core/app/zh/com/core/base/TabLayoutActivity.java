package core.app.zh.com.core.base;

import android.support.annotation.NonNull;

import core.app.zh.com.core.R;

/**
 * author : dayezi
 * data :2019/6/26
 * description:
 */
public abstract class TabLayoutActivity extends BaseActivity {

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.tablayout_view;
    }

    @Override
    public void init() {

    }
}
