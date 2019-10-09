package zh.com.jyu.views.pine.entity;


import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public abstract class PinnedHeaderAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {


    public PinnedHeaderAdapter(@Nullable List<T> data) {
        super(data);
    }

    /**
     * 判断该position对应的位置是要固定
     *
     * @param position adapter position
     * @return true or false
     */
    public abstract boolean isPinnedPosition(int position);


    public RecyclerView.ViewHolder onCreatePinnedViewHolder(ViewGroup parent, int viewType) {
        return onCreateViewHolder(parent, viewType);
    }

    public void onBindPinnedViewHolder(BaseViewHolder holder, int position) {
        onBindViewHolder(holder, position);
    }

}
