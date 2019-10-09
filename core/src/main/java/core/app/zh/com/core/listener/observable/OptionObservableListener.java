package core.app.zh.com.core.listener.observable;

import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/7/24
 * description:
 */
public interface OptionObservableListener {

    void init(BaseActivity activity);

    void onSubscribe(BaseActivity activity);

    void onNext(BaseActivity activity);

    void onError(BaseActivity activity);

    void onComplete(BaseActivity activity);
}
