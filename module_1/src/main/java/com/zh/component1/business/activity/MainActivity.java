package com.zh.component1.business.activity;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.victor.loading.rotate.RotateLoading;
import com.zh.annatation.loading.LoadingPoint;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.OnNavigationClick;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.api.loading.LoadingInJect;
import com.zh.component1.R;
import com.zh.component1.business.adapter.TestAdapter;
import com.zh.component1.mvp.contract.activity.MainContract;
import com.zh.component1.mvp.presenter.activity.MainPresenter;

import javax.inject.Inject;

import core.app.zh.com.core.annotation.LoadingHide;
import core.app.zh.com.core.annotation.LoadingShow;
import core.app.zh.com.core.annotation.RecyclerParam;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.listener.StatusViewListener;

@Route(path = MainActivity.AROUTER_PATH)
@ToolbarNavigation
@ToolbarTitle(title = "HELLO WORLD")
@LoadingPoint
public class MainActivity extends BaseActivity implements MainContract.MainUI, StatusViewListener {

    public static final String AROUTER_PATH = "/main/mainLogin/";

    @Inject
    MainPresenter presenter;

    private View view;

    @RecyclerParam
    SwipeRefreshLayout swipeRefreshLayout;

    @RecyclerParam
    RecyclerView recycler;

    @RecyclerParam
    TestAdapter adapter;

    @RecyclerParam
    RecyclerView.LayoutManager layoutManager;


    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        recycler = findViewById(R.id.recycler);
        LoadingInJect.init(this.getApplication());
        request();
    }

//    @RecyclerInit
//    private void test() {
//
//    }

//    @Override
//    public View beforeInit(LayoutInflater inflater, ViewGroup container) {
//        view = inflater.inflate(R.layout.loading, container, false);
//        setLoadingListener(this);
//        return super.beforeInit(inflater, container);
//    }

    @LoadingShow
    public void request() {
        presenter.test();
    }

    @OnNavigationClick
    public void ola(View view) {
        LogUtils.e(view);
    }

    @OnMenuOnclick
    public void onMenu(MenuItem menuItem) {

    }

    @LoadingHide
    @Override
    public void success() {

    }

    private RotateLoading rotateLoading;

    @Override
    public View loadingView() {
        rotateLoading = view.findViewById(R.id.rotateloading);
        rotateLoading.setLoadingColor(getResources().getColor(R.color.colorPrimary));
        return view;
    }

    @Override
    public void showLoading() {
        myContentView().setVisibility(View.GONE);
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
