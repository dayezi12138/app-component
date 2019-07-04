package zh.com.jyu.base;

import java.util.List;

import core.app.zh.com.core.base.BaseView;

/**
 * author : dayezi
 * data :2019/6/12
 * description:
 */
public interface ListViewUI<T> extends BaseView {

    void success(List<T> list, boolean refresh);

    void emptyData();
}
