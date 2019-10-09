package core.app.zh.com.core.bean;

import android.app.Dialog;
import android.text.TextUtils;

import com.android.tu.loadingdialog.LoadingDailog;
import com.zh.api.loading.LoadingInJect;

import core.app.zh.com.core.R;
import core.app.zh.com.core.annotation.ObservableState;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.enu.ObservableEnum;
import core.app.zh.com.core.interceptor.ObservableInterceptor;
import core.app.zh.com.core.listener.OptionObservableInterceptorListener;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @auther create by Administrator
 * DATE:2019/3/27 0027 11
 */
@Deprecated
public class MyObservable<T> implements Observer<T>, OptionObservableInterceptorListener {
    private final BaseActivity context;
    private Dialog dialog;
    private final boolean showDialog;
    private final String title;
    private final OnSuccessListener<T> listener;
    private final SuccessListener<T> successListener;
    private ObservableInterceptor observableInterceptor;

    private MyObservable(Builder<T> builder) {
        this.context = builder.context;
        this.listener = builder.listener;
        this.showDialog = builder.showDialog;
        this.title = builder.title;
        this.successListener = builder.successListener;
        this.observableInterceptor = builder.observableInterceptor;
        boolean valid = LoadingInJect.valided(context);
        if (!valid) dialog = defaultDialog();
    }

    private Dialog defaultDialog() {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(context)
                .setMessage(TextUtils.isEmpty(title) ? context.getResources().getString(R.string.loading_msg) : title)
                .setCancelable(false)
                .setCancelOutside(false);
        return loadBuilder.create();
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (dialog != null && showDialog) dialog.show();
        context.getCompositeDisposable().add(d);
    }

    @ObservableState(state = ObservableEnum.SUCCESS)
    @Override
    public void onNext(T t) {
        if (listener != null)
            listener.onSuccess(t);
        if (successListener != null)
            successListener.onSuccess(t);
        close();
    }

    @ObservableState(state = ObservableEnum.FAIL)
    @Override
    public void onError(Throwable e) {
        if (listener != null) listener.onFail(e.getMessage());
        close();
    }

    @Override
    public void onComplete() {
        close();
    }

    private void close() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    @Override
    public ObservableInterceptor observableInterceptor() {
        return observableInterceptor;
    }

    public static class Builder<T> {
        private BaseActivity context;
        private OnSuccessListener<T> listener;
        private boolean showDialog = true;
        private String title;
        private SuccessListener<T> successListener;
        private ObservableInterceptor observableInterceptor;

        public Builder(OnSuccessListener<T> listener, BaseActivity context) {
            this.context = context;
            this.listener = listener;
        }

        public Builder(SuccessListener<T> successListener, BaseActivity context) {
            this.context = context;
            this.successListener = successListener;
        }

        public Builder showDialog(boolean showDialog) {
            this.showDialog = showDialog;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder observableInterceptor(ObservableInterceptor observableInterceptor) {
            this.observableInterceptor = observableInterceptor;
            return this;
        }

        public MyObservable<T> build() {
            return new MyObservable<>(this);
        }

    }

    public interface OnSuccessListener<T> extends SuccessListener<T> {

        void onFail(String msg);
    }

    public interface SuccessListener<T> {
        void onSuccess(T t);
    }
}
