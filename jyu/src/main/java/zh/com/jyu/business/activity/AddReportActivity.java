package zh.com.jyu.business.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.api.ToolBarInject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MessageEvent;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.AddReportParam;
import zh.com.jyu.bean.activity.ReportListBean;
import zh.com.jyu.bean.activity.UserListBean;
import zh.com.jyu.business.adapter.AddReportAdapter;
import zh.com.jyu.constans.SpKeyConstant;
import zh.com.jyu.mvp.contract.activity.AddReportContract;
import zh.com.jyu.mvp.presenter.activity.AddReportPresenter;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
@Route(path = AddReportActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true)
@ToolbarTitle(title = "添加汇报")
public class AddReportActivity extends BaseActivity implements AddReportContract.AddReportUI, SynthesizerListener {

    public final static String AROUTER_PATH = "/plan/AddReportActivity/";

    public final static int CODE = 1008;
    @BindView(R.id.select_date_et)
    EditText dateEt;

    @BindView(R.id.count_tv)
    TextView countTv;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Inject
    AddReportPresenter presenter;

    @Inject
    MaterialDialog dialog;

    @Inject
    AddReportAdapter addReportAdapter;

    @Autowired(name = RelationOrderListActivity.PRODUCE_ID_KEY, required = true)
    int craftsReceiptID;

    @Autowired(name = CraftDetailActivity.PACK_COUNT_KEY)//	汇报量
            String countP;

    @Autowired(name = CraftDetailActivity.REAL_COUNT_KEY)//	目标产量
            String countR;

    @Autowired(name = ReportActivity.REPORT_KEY)
    ReportListBean.ReportsBean bean;

    private TimePickerView timePickerView;

    private List<UserListBean> listBeans;

    @Inject
    MaterialDialog.Builder deleteDialogBuidler;
    private MaterialDialog deleteDialog;

    private int deletePosition = -1;

    @BindString(R.string.dialog_sure_str)
    String dialogSureStr;

    @BindString(R.string.dialog_cancel_str)
    String dialogCancelStr;

    @Inject
    SpeechSynthesizer mTts;

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_add_report;
    }

    @Override
    public void init() {
        ToolBarInject.inject(this, toolbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));  //线性布局
        recyclerView.setAdapter(addReportAdapter);
        recyclerView.setNestedScrollingEnabled(false);//禁止rcyc嵌套滑动
        presenter.selectUser();
        initDate();
        EventBus.getDefault().register(this);
        countTv.setText(MessageFormat.format(getResources().getString(R.string.act_add_report_count_str), countR, countP));
        addReportAdapter.setOnItemLongClickListener((adapter, view, position) -> {
            deletePosition = position;
            deleteDialog.show();
            return true;
        });
        deleteDialog = deleteDialogBuidler.onPositive((dialog, which) -> {
            dialog.dismiss();
            if (deletePosition == -1) return;
            addReportAdapter.remove(deletePosition);
            deletePosition = -1;
        }).positiveText(dialogSureStr).neutralText(dialogCancelStr).build();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(MessageEvent message) {
        if (AddReportActivity.CODE == message.getCode()) {
            Type type = new TypeToken<List<UserListBean>>() {
            }.getType();
            List<UserListBean> listBeans = new Gson().fromJson(message.getMsg(), type);
            addReportAdapter.setNewData(listBeans);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean addToolbar() {
        return true;
    }

    @Override
    public void successUserList(List<UserListBean> listBeans) {
        this.listBeans = listBeans;
    }

    @Override
    public void submitSuccess() {
        showMsg("提交成功");
        mTts.startSpeaking("提交成功", this);
        finish();
    }

    @Override
    public void tipShowMsg(String msg) {
        showMsg(msg);
        mTts.startSpeaking(msg, this);
    }

    @OnClick(R.id.select_date_et)
    public void selectDate() {
        timePickerView.show();
    }

    private void initDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.add(Calendar.DAY_OF_MONTH, -7);
        dateEt.setText(format.format(startDate.getTime()));
        timePickerView = new TimePickerBuilder(AddReportActivity.this, (date, v) -> {
            String time = format.format(date);
            dateEt.setText(time);
        }).setType(new boolean[]{true, true, true, false, false, false})
                .setCancelText("取消")
                .setSubmitText("确定")
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .setRangDate(startDate, endDate)
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isDialog(false)//是否显示为对话框样式;
                .setBgColor(Color.WHITE)
                .build();

    }

    @OnClick(R.id.select_group_btn)
    public void groupBtn() {
        ARouter.getInstance().build(SelectUserActivity.AROUTER_PATH).withString(SelectUserActivity.USER_LIST_BEAN_KEY, new Gson().toJson(listBeans)).navigation();
    }

    @OnClick(R.id.submit_btn)
    public void submit() {
        if (valid()) {
            List<AddReportParam.ChildParam> childDataList = new ArrayList<>();
            for (UserListBean bean : addReportAdapter.getData()) {
                AddReportParam.ChildParam childParam = new AddReportParam.ChildParam(bean.getCount_(), bean.getMemo_(), bean.getID(), bean.getName());
                childDataList.add(childParam);
            }
            AddReportParam addReportParam = new AddReportParam(dateEt.getText().toString(), SPUtils.getInstance().getInt(SpKeyConstant.LOGIN_UID_KEY), craftsReceiptID, childDataList);
            presenter.submit(addReportParam);
        } else showMsg("必填项不能为空");
    }

    private boolean valid() {
        boolean valid = true;
        for (UserListBean bean : addReportAdapter.getData()) {
            if (TextUtils.isEmpty(bean.getCount_())) {
                valid = false;
            }
        }
        return valid;
    }

    @Override
    public void onSpeakBegin() {

    }

    @Override
    public void onBufferProgress(int i, int i1, int i2, String s) {

    }

    @Override
    public void onSpeakPaused() {

    }

    @Override
    public void onSpeakResumed() {

    }

    @Override
    public void onSpeakProgress(int i, int i1, int i2) {

    }

    @Override
    public void onCompleted(SpeechError speechError) {

    }

    @Override
    public void onEvent(int i, int i1, int i2, Bundle bundle) {

    }
}
