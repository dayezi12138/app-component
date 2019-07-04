package zh.com.jyu.business.adapter;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.R;
import zh.com.jyu.bean.fragment.PlanBean;

/**
 * author : dayezi
 * data :2019/6/12
 * description:
 */
public class PlanAdapter extends MyBaseAdapter<PlanBean> {

    @Inject
    public PlanAdapter() {
        super(R.layout.item_plan, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, PlanBean item) {
        helper.setText(R.id.plan_no_tv, item.getPlanNO());
        helper.setText(R.id.create_time_tv, item.getCreateTime());
    }

}
