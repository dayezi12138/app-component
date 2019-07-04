package zh.com.jyu.business.adapter;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.CraftBean;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
public class CraftListActAdapter extends MyBaseAdapter<CraftBean> {


    @Inject
    public CraftListActAdapter() {
        super(R.layout.item_craft, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, CraftBean item) {
        helper.setText(R.id.name_tv, item.getCraftsName());
        helper.setText(R.id.status_tv, item.getCraftsStatusStr());
        helper.setText(R.id.pack_count_tv, String.valueOf(item.getReportedCount()));
        helper.setText(R.id.real_num_tv, String.valueOf(item.getNumber1()));
        helper.setText(R.id.create_time_tv, item.getRegTime());
    }
}
