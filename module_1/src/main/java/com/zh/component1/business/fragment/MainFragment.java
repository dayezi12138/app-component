package com.zh.component1.business.fragment;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;

import com.victor.loading.rotate.RotateLoading;
import com.zh.annatation.loading.LoadingPoint;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.component1.R;

import core.app.zh.com.core.annotation.LoadingHide;
import core.app.zh.com.core.annotation.LoadingShow;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.listener.StatusViewListener;

/**
 * author : dayezi
 * data :2019/7/4
 * description:
 */
@ToolbarNavigation
@ToolbarTitle(title = "HELLO FRAGMENT")
@LoadingPoint
public class MainFragment extends BaseFragment implements StatusViewListener {
    private View view;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.fragment_main;
    }

//    @Override
//    public void beforeInitTemp() {
//        view = LayoutInflater.from(getContext()).inflate(R.layout.loading, getRootLy(), false);
//        rotateLoading = view.findViewById(R.id.rotateloading);
//        setLoadingListener(this);
//        super.beforeInitTemp();
//    }

    @Override
    public void init() {
        request();
    }

    @LoadingHide
    public void success() {

    }

    @LoadingShow
    public void request() {
        new Handler().postDelayed(() -> {
            success();
        }, 3000);
    }

    private RotateLoading rotateLoading;

    @Override
    public View loadingView() {
        return view;
    }

    @Override
    public void showLoading() {
        rotateLoading.start();
    }

    @Override
    public void hideLoading() {
        new Handler().postDelayed(() -> {
            myContentView().setVisibility(View.VISIBLE);
            rotateLoading.stop();
        }, 3000);
    }

    @Override
    public boolean handlerContentView() {
        return true;
    }
}
