package zh.com.jyu.business.adapter;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.bean.fragment.ProduceListBean;

/**
 * author : dayezi
 * data :2019/6/27
 * description:
 */
public class ProduceListAdapter extends MyBaseAdapter<ProduceListBean> {

    @Inject
    public ProduceListAdapter(int layoutResId) {
        super(layoutResId, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, ProduceListBean item) {

    }
}
