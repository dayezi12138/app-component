package core.app.zh.com.core.bean;

import android.app.Dialog;
import android.text.TextUtils;

import com.android.tu.loadingdialog.LoadingDailog;
import com.zh.api.loading.LoadingInJect;

import core.app.zh.com.core.R;
import core.app.zh.com.core.base.BaseActivity;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @auther create by Administrator
 * DATE:2019/3/27 0027 11
 */
public class MyObservable<T> implements Observer<T> {
    private final BaseActivity context;
    private Dialog dialog;
    private final boolean showDialog;
    private final String title;
    private final OnSuccessListener<T> listener;

    private MyObservable(Builder<T> builder) {
        this.context = builder.context;
        this.listener = builder.listener;
        this.showDialog = builder.showDialog;
        this.title = builder.title;
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

    @Override
    public void onNext(T t) {
        if (listener != null)
            listener.onSuccess(t);
        close();
    }

    @Override
    public void onError(Throwable e) {
        if (listener != null)
            listener.onFail(e.getMessage());
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

    public static class Builder<T> {
        private BaseActivity context;
        private OnSuccessListener<T> listener;
        private boolean showDialog = true;
        private String title;

        public Builder(OnSuccessListener<T> listener, BaseActivity context) {
            this.context = context;
            this.listener = listener;
        }

        public Builder showDialog(boolean showDialog) {
            this.showDialog = showDialog;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public MyObservable<T> build() {
            return new MyObservable<>(this);
        }

    }

    public interface OnSuccessListener<T> {
        void onSuccess(T t);

        void onFail(String msg);
    }
}
