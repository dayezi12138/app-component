package core.app.zh.com.core.listener.observable;

public interface ObservableListener {

    interface SuccessListener<T> {
        void onSuccess(T t);
    }

    interface FailListener {
        void onFail(Throwable ex);
    }
}