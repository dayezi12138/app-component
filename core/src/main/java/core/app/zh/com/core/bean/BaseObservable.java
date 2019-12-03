package core.app.zh.com.core.bean;

import com.blankj.utilcode.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import core.app.zh.com.core.annotation.ObservableState;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.enu.ObservableEnum;
import core.app.zh.com.core.interceptor.ObservableInterceptor;
import core.app.zh.com.core.listener.OptionObservableInterceptorListener;
import core.app.zh.com.core.listener.observable.ObservableListener;
import core.app.zh.com.core.listener.observable.OptionObservableListener;
import core.app.zh.com.core.provider.ObservableProvider;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * author : dayezi
 * data :2019/7/24
 * description:
 */
public class BaseObservable<T> implements Observer<T>, OptionObservableInterceptorListener {
    private final BaseActivity context;
    private final ObservableListener.FailListener failListener;
    private final ObservableListener.SuccessListener successListener;
    private final ObservableInterceptor observableInterceptor;
    private List<OptionObservableListener> observableListeners = new ArrayList<>();

    public BaseObservable(ObservableProvider builder) {
        context = builder.getContext();
        failListener = builder.getFailListener();
        successListener = builder.getSuccessListener();
        observableInterceptor = builder.getObservableInterceptor();
        if (builder.isAddDefaultOptionListener())
            observableListeners.add(builder.getDefaultObservableListener());
        observableListeners.addAll(builder.getOptionObservableListener());
        for (OptionObservableListener observableListener : observableListeners) {
            observableListener.init(context);
        }
    }

    @ObservableState(state = ObservableEnum.SUBSCRIBE)
    @Override
    public void onSubscribe(Disposable d) {
        LogUtils.e(d);
        for (OptionObservableListener observableListener : observableListeners) {
            observableListener.onSubscribe(context);
        }
    }

    @ObservableState(state = ObservableEnum.SUCCESS)
    @Override
    public void onNext(T t) {
        for (OptionObservableListener observableListener : observableListeners) {
            observableListener.onNext(context);
        }
        if (successListener != null) successListener.onSuccess(t);

    }

    @ObservableState(state = ObservableEnum.FAIL)
    @Override
    public void onError(Throwable e) {
        for (OptionObservableListener observableListener : observableListeners) {
            observableListener.onError(context);
        }
        if (failListener != null) failListener.onFail(e);
    }

    @ObservableState(state = ObservableEnum.COMPLETE)
    @Override
    public void onComplete() {
        for (OptionObservableListener observableListener : observableListeners) {
            observableListener.onComplete(context);
        }
    }

    @Override
    public ObservableInterceptor observableInterceptor() {
        return observableInterceptor;
    }

    public List<OptionObservableListener> getObservableListeners() {
        return observableListeners;
    }

    public BaseActivity getContext() {
        return context;
    }
}
