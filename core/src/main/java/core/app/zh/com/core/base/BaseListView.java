package core.app.zh.com.core.base;

import java.util.List;

/**
 * author : dayezi
 * data :2019/6/26
 * description:
 */
public interface BaseListView<T> extends BaseView {
    void success(List<T> data, boolean isRefresh);

    void emptyData();
}
