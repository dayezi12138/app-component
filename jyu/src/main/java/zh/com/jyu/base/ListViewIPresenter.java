package zh.com.jyu.base;

import java.util.Map;

import core.app.zh.com.core.base.IPresenter;

/**
 * author : dayezi
 * data :2019/6/12
 * description:
 */
public interface ListViewIPresenter extends IPresenter {

    void onRefresh(Map<String, Object> params);

    void onloadMored(Map<String, Object> params);
}
