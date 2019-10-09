package core.app.zh.com.core.listener;

import core.app.zh.com.core.base.BasePresenter;

/**
 * @auther create by Administrator
 * DATE:2019/3/27 0027 19
 */
@Deprecated
public interface GetPresenter {
    default BasePresenter getPresenter() {
        return null;
    }
}
