package com.zh.component1.mvp.presenter.activity;

import com.blankj.utilcode.util.LogUtils;
import com.zh.component1.mvp.contract.activity.MainContract;
import com.zh.component1.mvp.model.activity.MainModel;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MyObservable;

/**
 * author : dayezi
 * data :2019/7/1
 * description:
 */
public class MainPresenter extends BasePresenter<MainContract.MainUI> implements MainContract.Presenter {
    private MainModel model;

    @Inject
    public MainPresenter(MainModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void test() {
        model.test(new MyObservable.OnSuccessListener<Object>() {
            @Override
            public void onSuccess(Object object) {
                LogUtils.e(object);
                view.get().success();
            }

            @Override
            public void onFail(String msg) {
                LogUtils.e(msg);
            }
        });
    }
}
