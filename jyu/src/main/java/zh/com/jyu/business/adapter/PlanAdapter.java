package zh.com.jyu.business.adapter;

import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.R;
import zh.com.jyu.bean.fragment.PlanBean;
import zh.com.jyu.business.activity.HtmlActivity;

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
        helper.setText(R.id.remark_tv, item.getRemark());
        if (TextUtils.isEmpty(item.getRemark()))
            helper.getView(R.id.remark_tv).setVisibility(View.GONE);
        else {
            helper.getView(R.id.remark_tv).setVisibility(View.VISIBLE);
            helper.setText(R.id.remark_tv, item.getRemark());
        }
        helper.getView(R.id.select_btn).setOnClickListener(v -> ARouter.getInstance().build(HtmlActivity.AROUTER_PATH).withString(HtmlActivity.PID_KEY, String.valueOf(item.getID())).navigation());
    }

}
