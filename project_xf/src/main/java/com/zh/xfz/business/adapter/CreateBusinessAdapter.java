package com.zh.xfz.business.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.Industry;
import com.zh.xfz.business.activity.CreateBusinessActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;

/**
 * author : dayezi
 * data :2019/7/29
 * description:
 */
public class CreateBusinessAdapter extends MyBaseAdapter<Industry> {
    private Map<Integer, Industry> map = new HashMap<>();
    private CreateBusinessActivity activity;

    @Inject
    public CreateBusinessAdapter(CreateBusinessActivity activity) {
        super(R.layout.item_create_business, new ArrayList<>());
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, Industry item) {
        TextView nameTv = helper.getView(R.id.name);
        nameTv.setText(item.getName());
        nameTv.setOnClickListener(v -> {
            if (!map.containsKey(helper.getAdapterPosition())) {
                map.put(helper.getAdapterPosition(), item);
                nameTv.setBackgroundResource(R.drawable.shape_select_create_busi);
                nameTv.setTextColor(mContext.getResources().getColor(R.color.white));
            } else {
                map.remove(helper.getAdapterPosition());
                nameTv.setBackgroundResource(R.drawable.shape_un_select_create_busi);
                nameTv.setTextColor(mContext.getResources().getColor(R.color.item_text_un_select_create_busi_color));
            }
            if (onClickAfterListener != null) onClickAfterListener.onClickAfter(!map.isEmpty());
        });
    }

    public List<Industry> getSelectData() {
        Collection<Industry> collection = map.values();
        return new ArrayList<>(collection);
    }

    private OnClickAfterListener onClickAfterListener;

    public void setOnClickAfterListener(OnClickAfterListener listener) {
        this.onClickAfterListener = listener;
    }

    public interface OnClickAfterListener {
        void onClickAfter(boolean hasSelect);
    }
}
