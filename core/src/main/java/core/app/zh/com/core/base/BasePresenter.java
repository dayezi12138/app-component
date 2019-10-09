package core.app.zh.com.core.base;

import java.lang.ref.WeakReference;

import core.app.zh.com.core.listener.GetActivityListener;
import core.app.zh.com.core.listener.GetMyBaseModelListener;

/**
 * @auther create by Administrator
 * DATE:2019/3/27 0027 15
 */
public abstract class BasePresenter<T extends BaseView> implements IPresenter, GetActivityListener {
    protected WeakReference<T> view;
    private BaseActivity activity;

    public BasePresenter(BaseModel model) {
        if (model.getBean() instanceof BaseView) {
            view = new WeakReference<>((T) model.getBean());
        }
        if (model.getBean() instanceof BaseActivity) {
            activity = (BaseActivity) model.getBean();
        } else if (model.getBean() instanceof BaseFragment) {
            activity = ((BaseFragment) model.getBean()).getMyActivity();
        }
    }

    public BasePresenter(Object object) {
        if (object instanceof GetMyBaseModelListener) {
            GetMyBaseModelListener listener = (GetMyBaseModelListener) object;
            view = new WeakReference<>((T) listener.getMyBaseModel().getBaseView());
            activity = listener.getMyBaseModel().getMyActivity();
        }
//        else throw new Exception("GetMyBaseModelListener not found !");
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
