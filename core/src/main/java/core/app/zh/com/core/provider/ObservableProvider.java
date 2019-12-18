package core.app.zh.com.core.provider;

import android.app.Dialog;
import android.text.TextUtils;

import com.android.tu.loadingdialog.LoadingDailog;
import com.zh.api.loading.LoadingInJect;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import core.app.zh.com.core.R;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.bean.BaseObservable;
import core.app.zh.com.core.interceptor.ObservableInterceptor;
import core.app.zh.com.core.listener.observable.ObservableListener;
import core.app.zh.com.core.listener.observable.OptionObservableListener;

/**
 * author : dayezi
 * data :2019/7/24
 * description:
 */
public class ObservableProvider {
    private BaseActivity context;
    private ObservableListener.FailListener failListener;
    private ObservableListener.SuccessListener successListener;
    private ObservableInterceptor observableInterceptor;
    private boolean isAddDefaultOptionListener = true;
    private List<OptionObservableListener> optionObservableListener = new ArrayList<>();
    private boolean showDialog = true;
    private String title;

    public ObservableProvider(BaseActivity context, ObservableListener.SuccessListener successListener) {
        this.context = context;
        this.successListener = successListener;
    }

    @Inject
    public ObservableProvider(BaseActivity context) {
        this.context = context;
    }

    public ObservableProvider successListener(ObservableListener.SuccessListener successListener) {
        this.successListener = successListener;
        return this;
    }

    public ObservableProvider failListener(ObservableListener.FailListener failListener) {
        this.failListener = failListener;
        return this;
    }

    public ObservableProvider observableInterceptor(ObservableInterceptor observableInterceptor) {
        this.observableInterceptor = observableInterceptor;
        return this;
    }

    public ObservableProvider isAddDefaultOptionListener(boolean isAddDefaultOptionListener) {
        this.isAddDefaultOptionListener = isAddDefaultOptionListener;
        return this;
    }

    public ObservableProvider optionObservableListener(OptionObservableListener optionObservableListener) {
        this.optionObservableListener.add(optionObservableListener);
        return this;
    }

    public ObservableProvider showDialog(boolean showDialog) {
        this.showDialog = showDialog;
        return this;
    }

    public ObservableProvider title(String title) {
        this.title = title;
        return this;
    }


    public <T extends BaseObservable> T build(Class<T> tClass) {
        try {
            Constructor constructor = Class.forName(tClass.getName()).getDeclaredConstructor(this.getClass());
            return (T) constructor.newInstance(this);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public BaseActivity getContext() {
        return context;
    }

    public ObservableListener.FailListener getFailListener() {
        return failListener;
    }

    public ObservableListener.SuccessListener getSuccessListener() {
        return successListener;
    }

    public ObservableInterceptor getObservableInterceptor() {
        return observableInterceptor;
    }

    public List<OptionObservableListener> getOptionObservableListener() {
        return optionObservableListener;
    }

    public boolean isAddDefaultOptionListener() {
        return isAddDefaultOptionListener;
    }

    public OptionObservableListener getDefaultObservableListener() {
        return new DEFAULT_OPTION_LISTENER(showDialog, title);
    }

    private static class DEFAULT_OPTION_LISTENER implements OptionObservableListener {
        private boolean showDialog;
        private String title;

        public DEFAULT_OPTION_LISTENER(boolean showDialog, String title) {
            this.showDialog = showDialog;
            this.title = title;
        }

        @Override
        public void init(BaseActivity activity) {
            boolean valid = LoadingInJect.valided(activity);
            if (!valid && showDialog) {
                dialog = defaultDialog(activity);
                dialog.show();
            }
        }

        @Override
        public void onSubscribe(BaseActivity activity) {
        }

        @Override
        public void onNext(BaseActivity activity) {
            close();
        }

        @Override
        public void onError(BaseActivity activity) {
            close();
        }

        @Override
        public void onComplete(BaseActivity activity) {
            close();
        }

        private Dialog dialog;

        private Dialog defaultDialog(BaseActivity activity) {
            LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(activity)
                    .setMessage(TextUtils.isEmpty(title) ? activity.getResources().getString(R.string.loading_msg) : title)
                    .setCancelable(false)
                    .setCancelOutside(false);
            return loadBuilder.create();
        }

        private void close() {
            if (dialog != null && dialog.isShowing())
                dialog.dismiss();
        }
    }
}
