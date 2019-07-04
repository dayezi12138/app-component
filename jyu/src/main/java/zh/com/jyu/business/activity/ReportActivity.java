package zh.com.jyu.business.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.R;
import zh.com.jyu.business.adapter.ReportListAdapter;
import zh.com.jyu.mvp.presenter.activity.ReportPresenter;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
@Route(path = ReportActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true)
@ToolbarTitle(title = "汇报历史")
@ToolbarLeft(menuId = R.menu.report_list_tool_bar)
public class ReportActivity extends BaseListActivity {

    public static final String AROUTER_PATH = "/plan/ReportActivity/";
    public static final String REPORT_KEY = "reportBean";

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.count_tv)
    TextView countTv;

    @Inject
    ReportListAdapter reportListAdapter;

    @Inject
    ReportPresenter presenter;

    @Inject
    MaterialDialog.Builder builder;
    private MaterialDialog dialog;

    @Autowired(name = RelationOrderListActivity.PRODUCE_ID_KEY, required = true)
    int craftsReceiptID;

    private int position = -1;

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_report;
    }

    @Override
    public void init() {
        super.init();
        builder.onPositive((dialog, which) -> {
            if (position != -1)
                presenter.delete(reportListAdapter.getData().get(position).getID(), position);
            dialog.dismiss();
        });
        dialog = builder.build();
    }

    @Override
    public MyBaseAdapter myBaseAdapter() {
        return reportListAdapter;
    }


    @Override
    public void onItemClick(int position) {
        ARouter.getInstance().build(EditReportActivity.AROUTER_PATH)
                .withString(CraftDetailActivity.REAL_COUNT_KEY, String.valueOf(presenter.getReportListBean().getNumber1()))
                .withString(CraftDetailActivity.PACK_COUNT_KEY, String.valueOf(presenter.getReportListBean().getReportedCount()))
                .withSerializable(REPORT_KEY, presenter.getReportListBean().getReports().get(position)).navigation();
    }

    @Override
    public void success(List data) {
        super.success(data);
        countTv.setText(MessageFormat.format(getResources().getString(R.string.act_add_report_count_str), String.valueOf(presenter.getReportListBean().getNumber1()), String.valueOf(presenter.getReportListBean().getReportedCount())));
    }

    @Override
    public void onItemLongClick(int position) {
        this.position = position;
        if (!dialog.isShowing()) dialog.show();
    }

    @Override
    public boolean isOnResume() {
        return true;
    }

    @Override
    public Map<String, Object> params() {
        Map<String, Object> params = new HashMap<>();
        params.put("craftsReceiptID", craftsReceiptID);
        return params;
    }

    @OnMenuOnclick
    public void menuOnclick() {
        ARouter.getInstance().build(AddReportActivity.AROUTER_PATH)
                .withString(CraftDetailActivity.REAL_COUNT_KEY, String.valueOf(presenter.getReportListBean().getNumber1()))
                .withString(CraftDetailActivity.PACK_COUNT_KEY, String.valueOf(presenter.getReportListBean().getReportedCount()))
                .withInt(RelationOrderListActivity.PRODUCE_ID_KEY, craftsReceiptID).navigation();
    }

}
