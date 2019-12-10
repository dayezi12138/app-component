package core.app.zh.com.core.base;

import java.util.List;

/**
 * author : dayezi
 * data :2019/12/10
 * description:
 */
public interface IBaseListView<T> extends BaseView {

    void listData(List<T> data,boolean isRefresh);

}
