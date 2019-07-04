package zh.com.jyu.mvp.contract.fragment;

import java.util.Map;

import core.app.zh.com.core.base.IPresenter;
import zh.com.jyu.base.ListViewUI;
import zh.com.jyu.bean.activity.CraftBean;

/**
 * author : dayezi
 * data :2019/6/19
 * description:
 */
public interface LeaderContract {

    interface LeaderUI extends ListViewUI<CraftBean> {
//        void empty();
    }

    interface Presenter extends IPresenter {
        void onRefresh(Map<String, Object> map);

        void onLoadMore(Map<String, Object> map);
    }
}
