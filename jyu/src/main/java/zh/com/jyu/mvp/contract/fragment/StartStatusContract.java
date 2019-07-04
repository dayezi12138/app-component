package zh.com.jyu.mvp.contract.fragment;

import zh.com.jyu.base.ListViewIPresenter;
import zh.com.jyu.base.ListViewUI;

/**
 * author : dayezi
 * data :2019/6/12
 * description:
 */
public interface StartStatusContract {

    interface StartStatusUI<T> extends ListViewUI<T> {


    }

    interface Presenter extends ListViewIPresenter {

    }
}
