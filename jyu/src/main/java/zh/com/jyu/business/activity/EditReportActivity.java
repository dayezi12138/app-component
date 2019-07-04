package zh.com.jyu.business.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.SPUtils;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.api.ToolBarInject;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BasePresenter;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.ReportListBean;
import zh.com.jyu.constans.SpKeyConstant;
import zh.com.jyu.mvp.contract.activity.EditReportContract;
import zh.com.jyu.mvp.presenter.activity.EditReportPresenter;

/**
 * author : dayezi
 * data :2019/6/20
 * description:
 */
@Route(path = EditReportActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true)
@ToolbarTitle(title = "修改汇报")
public class EditReportActivity extends BaseActivity implements EditReportContract.EditReportUI {

    public final static String AROUTER_PATH = "/plan/EditReportActivity/";
    @Inject
    EditReportPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.name_tv)
    TextView nameTv;

    @Autowired(name = CraftDetailActivity.PACK_COUNT_KEY)
    String countP;//	汇报量

    @Autowired(name = CraftDetailActivity.REAL_COUNT_KEY)
    String countR;//	目标产量

    @Autowired(name = ReportActivity.REPORT_KEY)
    ReportListBean.ReportsBean reportsBean;
    @BindView(R.id.count_tv)
    TextView countTv;

    @BindView(R.id.create_time_tv)
    TextView createTv;

    @BindView(R.id.report_num_tv)
    EditText reportNumTv;

    @BindView(R.id.memo_tv)
    EditText memoTv;

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_edit_report;
    }

    @Override
    public void init() {
        ToolBarInject.inject(this, toolbar);
        countTv.setText(MessageFormat.format(getResources().getString(R.string.act_add_report_count_str), countR, countP));
        nameTv.setText(reportsBean.getCrewName());
        createTv.setText(String.valueOf(reportsBean.getCreateTime()));
        reportNumTv.setText(String.valueOf(reportsBean.getCount()));
        memoTv.setText(TextUtils.isEmpty(reportsBean.getRemark()) ? "" : reportsBean.getRemark());
    }

    @Override
    public boolean addToolbar() {
        return true;
    }

    @OnClick(R.id.submit_btn)
    public void submit() {
        if (TextUtils.isEmpty(reportNumTv.getText().toString())) {
            showMsg("必填项不能为空");
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("ReportID", reportsBean.getID());
        map.put("userId", SPUtils.getInstance().getInt(SpKeyConstant.LOGIN_UID_KEY));
        map.put("ReportedCount", reportNumTv.getText().toString().trim());
        map.put("Remark", memoTv.getText().toString());
        presenter.edit(map);
    }
}
