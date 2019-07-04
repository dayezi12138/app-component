package com.zh.component1.business.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zh.api.loading.LoadingInJect;
import com.zh.component1.R;
import com.zh.component1.business.fragment.MainFragment;

import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BasePresenter;

/**
 * author : dayezi
 * data :2019/7/4
 * description:
 */
@Route(path = FragActivity.AROUTER_PATH)
public class FragActivity extends BaseActivity {
    public static final String AROUTER_PATH = "/main/mainLogin/";

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_f;
    }

    @Override
    public void init() {
        LoadingInJect.init(this.getApplication());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.md_titleFrame, new MainFragment());
        transaction.commit();
    }
}
