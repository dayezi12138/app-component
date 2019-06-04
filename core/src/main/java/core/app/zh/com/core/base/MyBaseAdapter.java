package core.app.zh.com.core.base;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @auther create by Administrator
 * DATE:2019/3/27 0027 19
 */
public abstract class MyBaseAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {
    public MyBaseAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }


}
