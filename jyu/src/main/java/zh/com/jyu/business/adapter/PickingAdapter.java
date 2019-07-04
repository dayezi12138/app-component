package zh.com.jyu.business.adapter;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.PickingBean;

/**
 * author : dayezi
 * data :2019/6/13
 * description:
 */
public class PickingAdapter extends MyBaseAdapter<PickingBean.DetailsBean> {

    @Inject
    public PickingAdapter() {
        super(R.layout.item_picking, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, PickingBean.DetailsBean item) {
        helper.setText(R.id.name_tv, item.getGoodsName());
        helper.setText(R.id.specs_tv, item.getSpecName());
        helper.setText(R.id.count_tv, String.valueOf(item.getGoodsCount()));
//        if (item.getTradeNos().length() > 23) {
//            StringBuilder builder = new StringBuilder(item.getTradeNos().substring(0, 24));
//            helper.setText(R.id.tradeNos_tv, builder.append("...").toString());
//        } else helper.setText(R.id.tradeNos_tv, item.getTradeNos());
    }
}
