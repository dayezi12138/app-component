package zh.com.jyu.business.adapter;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseFragment;
import zh.com.jyu.R;

/**
 * author : dayezi
 * data :2019/6/13
 * description:
 */
public class PlanBAdapter extends BaseRecyclerAdapter<String> {

    @Inject
    public PlanBAdapter(BaseFragment fragment) {
        super(fragment.getActivity(), new ArrayList<>(), R.layout.item_plan);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.plan_no_tv,item);
    }
}
