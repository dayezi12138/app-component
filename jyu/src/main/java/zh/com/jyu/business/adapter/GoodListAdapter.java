package zh.com.jyu.business.adapter;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.GoodListBean;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
public class GoodListAdapter extends MyBaseAdapter<GoodListBean> {

    @Inject
    public GoodListAdapter() {
        super(R.layout.item_good_list, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodListBean item) {
//        helper.setText(R.id.status_tv, item.getStatusStr());
        helper.setText(R.id.plan_no_tv, item.getProduceGoodsReceiptNO());
        helper.setText(R.id.good_name_tv, item.getGoodsName());
        helper.setText(R.id.specs_tv, item.getSpecName());
        helper.setText(R.id.count_tv, String.valueOf(item.getRealNumber()));
        helper.setText(R.id.create_time_tv, item.getRegTime());
    }
}