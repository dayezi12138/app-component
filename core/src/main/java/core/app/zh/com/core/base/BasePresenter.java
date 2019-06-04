package core.app.zh.com.core.base;

import java.lang.ref.WeakReference;

import core.app.zh.com.core.listener.GetActivityListener;

/**
 * @auther create by Administrator
 * DATE:2019/3/27 0027 15
 */
public abstract class BasePresenter<T extends BaseView> implements IPresenter, GetActivityListener {
    protected WeakReference<T> view;
    private BaseActivity activity;

    public BasePresenter(GetActivityListener listener) {
        view = new WeakReference<>((T) listener.getMyActivity());
        this.activity = listener.getMyActivity();
    }

    public BasePresenter(BaseActivity activity) {
        view = new WeakReference<>((T) activity);
        this.activity = activity;
    }

    public BasePresenter(BaseFragment fragment) {
        view = new WeakReference<>((T) fragment);
        this.activity = fragment.getMyActivity();
    }

    @Override
    public BaseActivity getMyActivity() {
        return activity;
    }

    @Override
    public void detachView() {
//        if (view != null) {
//            view.clear();
//            view = null;
//        }

    }
}
