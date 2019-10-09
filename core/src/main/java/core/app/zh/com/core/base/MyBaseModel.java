package core.app.zh.com.core.base;

import android.app.Application;
import android.support.annotation.NonNull;

import com.rxjava.rxlife.ScopeViewModel;

import core.app.zh.com.core.listener.GetActivityListener;

/**
 * author : dayezi
 * data :2019/7/18
 * description:
 */
public abstract class MyBaseModel<T extends BaseView> extends ScopeViewModel implements GetActivityListener {

    public MyBaseModel(@NonNull Application application) {
        super(application);
    }

    public abstract T getBaseView();

}
