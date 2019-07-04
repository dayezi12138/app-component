package zh.com.jyu.mvp.contract.activity;

import java.util.List;
import java.util.Map;

import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.IPresenter;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
public class BaseListContract {

    public interface BaseListUI<T> extends BaseView {
        void success(List<T> data);
    }

    public interface BaseListPreseneter extends IPresenter {
        void requestData(Map<String, Object> params);
    }
}
