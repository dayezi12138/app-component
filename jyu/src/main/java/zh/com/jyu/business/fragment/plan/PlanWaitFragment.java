package zh.com.jyu.business.fragment.plan;

import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.bean.fragment.PlanBean;
import zh.com.jyu.business.activity.GoodsListActivity;
import zh.com.jyu.business.activity.PickingActivity;
import zh.com.jyu.business.adapter.PlanAdapter;
import zh.com.jyu.business.fragment.StatusFragment;
import zh.com.jyu.mvp.presenter.fragment.plan.PlanWaitPresenter;

/**
 * author : dayezi
 * data :2019/6/12
 * description:
 */
public class PlanWaitFragment extends StatusFragment<PlanBean> {
    @Inject
    PlanWaitPresenter presenter;

    @Inject
    PlanAdapter planAdapter;
    private Map<String, Object> paramsMap = new HashMap<>();

    @Override
    public MyBaseAdapter adapter() {
        return planAdapter;
    }

    @Override
    public Map<String, Object> params() {
        paramsMap.put("keywords", clearEditTextView == null || TextUtils.isEmpty(clearEditTextView.getText().toString()) ? "" : clearEditTextView.getText().toString());
        paramsMap.put("status", 0);
        return paramsMap;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onItemClick(int position) {
        ARouter.getInstance()
                .build(GoodsListActivity.AROUTER_PATH)
                .withInt(PickingActivity.PARAM_ID, dataList.get(position).getID())
                .navigation();

    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }
}
