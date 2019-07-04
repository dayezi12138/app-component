package zh.com.jyu.business.adapter;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.RelationOrderBean;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
public class RelationOrderListAdapter extends MyBaseAdapter<RelationOrderBean> {

    @Inject
    public RelationOrderListAdapter() {
        super(R.layout.item_relation_order, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, RelationOrderBean item) {
        helper.setText(R.id.order_num_tv, item.getTradeNO());
        helper.setText(R.id.user_name_tv, item.getCompanyName());
        helper.setText(R.id.buy_count_tv, String.valueOf(item.getSellCount()));
        helper.setText(R.id.account_tv, item.getSndTo());
        helper.setText(R.id.phone_tv, item.getTel());
        helper.setText(R.id.send_tv, item.getYSndTime());
    }
}
