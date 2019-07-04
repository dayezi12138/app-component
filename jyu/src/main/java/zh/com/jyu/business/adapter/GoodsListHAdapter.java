package zh.com.jyu.business.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.OrderDetailBean;

/**
 * author : dayezi
 * data :2019/6/25
 * description:
 */
public class GoodsListHAdapter extends MyBaseAdapter<OrderDetailBean.TradeGoodsListBean> {

    @Inject
    public GoodsListHAdapter() {
        super(R.layout.item_goods_list_h, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderDetailBean.TradeGoodsListBean item) {
        helper.setText(R.id.goods_no_tv, item.getGoodsNo());
        DecimalFormat format = new DecimalFormat("0.00");
        helper.setText(R.id.unit_tv, MessageFormat.format(mContext.getResources().getString(R.string.item_goods_list_h_unit_str), item.getUnit()));
        helper.setText(R.id.price_tv, MessageFormat.format(mContext.getResources().getString(R.string.item_goods_list_h_im_str), format.format(item.getPrice_Detail())));
        helper.setText(R.id.sell_count_tv, MessageFormat.format(mContext.getResources().getString(R.string.item_goods_list_h_im_str), item.getSellCount()));
        ImageView image = helper.getView(R.id.image);
        Glide.with(mContext).load(item.getIndexURL()).into(image);
    }
}
