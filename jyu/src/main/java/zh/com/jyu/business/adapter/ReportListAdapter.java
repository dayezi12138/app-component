package zh.com.jyu.business.adapter;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.ReportListBean;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
public class ReportListAdapter extends MyBaseAdapter<ReportListBean.ReportsBean> {

    @Inject
    public ReportListAdapter() {
        super(R.layout.item_report, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, ReportListBean.ReportsBean item) {
        helper.setText(R.id.name_tv, item.getCrewName());
        helper.setText(R.id.report_num_tv, String.valueOf(item.getCount()));
        helper.setText(R.id.create_time_tv, item.getCreateTime());
        helper.setText(R.id.produce_tv, item.getProduceDate());
    }
}
