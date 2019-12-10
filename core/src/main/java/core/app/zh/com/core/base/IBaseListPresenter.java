package core.app.zh.com.core.base;

import java.util.Map;

/**
 * author : dayezi
 * data :2019/12/9
 * description:
 */
public interface IBaseListPresenter extends IPresenter {

    void onRefresh(Map<String, Object> params);

    void onLoad(Map<String, Object> params);
}
