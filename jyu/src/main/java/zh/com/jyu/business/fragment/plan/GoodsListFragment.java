package zh.com.jyu.business.fragment.plan;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.BasePresenter;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.GoodListBean;
import zh.com.jyu.business.activity.GoodsDetailActivity;
import zh.com.jyu.business.adapter.GoodListAdapter;

/**
 * author : dayezi
 * data :2019/6/21
 * description:
 */
public class GoodsListFragment extends BaseFragment {

    @Inject
    GoodListAdapter goodListAdapter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_good_list;
    }

    @Override
    public void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));  //线性布局
        recyclerView.setAdapter(goodListAdapter);
        goodListAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.item_line));
        recyclerView.addItemDecoration(dividerItemDecoration);
        goodListAdapter.setOnItemClickListener((adapter, view, position) -> ARouter.getInstance().build(GoodsDetailActivity.AROUTER_PATH)
                .withInt(GoodsDetailActivity.PARAM_ID, goodListAdapter.getData().get(position).getID())
                .navigation());
    }

    public void setData(List<GoodListBean> data) {
        goodListAdapter.setNewData(data);
    }
}
