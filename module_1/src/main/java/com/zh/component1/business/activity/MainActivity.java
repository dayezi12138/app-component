package com.zh.component1.business.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.OnNavigationClick;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.api.MyInject;
import com.zh.component1.R;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BasePresenter;

@Route(path = MainActivity.AROUTER_PATH)
@ToolbarNavigation
@ToolbarTitle(title = "HELLO WORLD")
public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.text)
    TextView text;
    public static final String AROUTER_PATH = "/main/mainLogin/";


    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        MyInject.inject(this, toolbar);
        String pac = AppUtils.getAppPackageName();
        LogUtils.e(pac);
    }

    @OnNavigationClick
    public void ola(View view) {
        LogUtils.e(view);
    }

    @OnMenuOnclick
    public void onMenu(MenuItem menuItem) {

    }
}
