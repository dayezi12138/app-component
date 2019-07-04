package zh.com.jyu.business.activity;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.api.ToolBarInject;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BasePresenter;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.PickingBean;
import zh.com.jyu.business.adapter.PickingAdapter;
import zh.com.jyu.mvp.contract.activity.PickingContract;
import zh.com.jyu.mvp.presenter.activity.PickingPresenter;

/**
 * author : dayezi
 * data :2019/6/13
 * description:
 */
@Route(path = PickingActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true)
@ToolbarTitle(title = "领料信息")
public class PickingActivity extends BaseActivity implements PickingContract.PickingUI {
    public static final String AROUTER_PATH = "/plan/PickingActivity/";
    public static final String PARAM_ID = "plan_id";

    @Autowired(name = PickingActivity.PARAM_ID, required = true)
    int planId;

    @BindViews({R.id.num_tv, R.id.time_tv, R.id.status_tv})
    List<TextView> textViewList;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    PickingPresenter presenter;

    @Inject
    PickingAdapter pickingAdapter;

    private MaterialDialog dialog;

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_picking;
    }

    @Override
    public void init() {
        ToolBarInject.inject(this, toolbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));  //线性布局
        recyclerView.setAdapter(pickingAdapter);
        pickingAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        presenter.getStockOutInfo(planId);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.item_line));
        recyclerView.addItemDecoration(dividerItemDecoration);
//        pickingAdapter.setOnItemClickListener((adapter, view, position) -> new MaterialDialog.Builder(this).title("提示").positiveText("确定").content(pickingAdapter.getData().get(position).getTradeNos()).onPositive((dialog, which) -> dialog.dismiss()).build().show());
        dialog = new MaterialDialog.Builder(this).title("提示").positiveText("确定").onPositive((dialog, which) -> dialog.dismiss()).build();
    }

    @Override
    public void stockOutInfo(PickingBean pickingBean) {
        textViewList.get(0).setText(pickingBean.getPlanNo());
        textViewList.get(1).setText(pickingBean.getSDate());
        textViewList.get(2).setText(pickingBean.getCurStatusStr());
        pickingAdapter.setNewData(pickingBean.getDetails());
    }

    @Override
    public void fail(String msg) {
        if (!dialog.isShowing()) {
            dialog.setContent(msg);
            dialog.show();
        }

    }

    @Override
    public boolean addToolbar() {
        return true;
    }
}
