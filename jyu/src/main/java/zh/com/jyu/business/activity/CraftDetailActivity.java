package zh.com.jyu.business.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.api.ToolBarInject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MessageEvent;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.CraftDetailBean;
import zh.com.jyu.bean.activity.UserListBean;
import zh.com.jyu.business.adapter.CrafDetailAdapter;
import zh.com.jyu.business.fragment.leader.NLeaderFragment;
import zh.com.jyu.mvp.contract.activity.CraftDetailContract;
import zh.com.jyu.mvp.presenter.activity.CraftDetailPresenter;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
@Route(path = CraftDetailActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true)
@ToolbarTitle(title = "工艺单明细")
@ToolbarLeft(menuId = R.menu.craf_detail_tool_bar)
public class CraftDetailActivity extends BaseActivity implements CraftDetailContract.CraftDetailUI, SynthesizerListener {
    public static final int SEARCH_EVENT_CODE = 100007;
    public static final String AROUTER_PATH = "/plan/CraftDetailActivity/";
    public final static String CRAFT_ID_KEY = "id";
    public static final String REAL_COUNT_KEY = "realCountKey";
    public static final String PACK_COUNT_KEY = "packCountKey";

    @Autowired(name = CRAFT_ID_KEY)
    int crafId;

    @Inject
    CraftDetailPresenter presenter;

    @Inject
    CrafDetailAdapter crafDetailAdapter;

    @Inject
    SpeechSynthesizer mTts;

    AlertDialog dialog;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindViews({R.id.name_tv, R.id.count_tv, R.id.pack_count_tv, R.id.status_tv, R.id.accounts_tv, R.id.first_time_tv, R.id.last_time_tv, R.id.pre_time_tv, R.id.create_time_tv})
    List<TextView> textViewList;

    @BindView(R.id.order_num_tv)
    TextView orderNumTv;

    @BindView(R.id.imgage_iv)
    ImageView imageView;

    @BindView(R.id.command_imgage_iv)
    ImageView commandIv;

    @BindView(R.id.goods_name_tv)
    TextView goodsNameTv;

    @BindView(R.id.length_tv)
    TextView lengthTv;

    @BindView(R.id.space_name_tv)
    TextView specNameTv;

    private int status = -1;
    private CraftDetailBean craftDetailBean;

    @BindString(R.string.dialog_tip_str)
    String dialogTipStr;
    @BindString(R.string.dialog_sure_str)
    String dialogSureStr;
    @BindString(R.string.dialog_cancel_str)
    String dialogCancelStr;
    @BindString(R.string.act_craft_detail_force_complete_str)
    String forceComplete;
    @BindString(R.string.act_craft_detail_complete_str)
    String complete;
    @BindString(R.string.act_craft_detail_no_date_str)
    String nodataStr;


    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_craft_detail;
    }

    @Override
    public void init() {
        ToolBarInject.inject(this, toolbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));  //线性布局
        recyclerView.setAdapter(crafDetailAdapter);
        crafDetailAdapter.openLoadAnimation();
        crafDetailAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        crafDetailAdapter.setOnItemClickListener((adapter, view, position) -> {
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(dialogTipStr);
        builder.setPositiveButton(dialogSureStr, (dialog, which) -> presenter.operation(status, craftDetailBean.getCraftsReceiptID()));
        builder.setNegativeButton(dialogCancelStr, (dialog, which) -> dialog.dismiss());
        dialog = builder.create();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(MessageEvent message) {
        if (message.getCode() == SEARCH_EVENT_CODE) {
            crafId = Integer.valueOf(message.getMsg());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getProduceCraftsReceipt(crafId);
    }

    @Override
    public boolean addToolbar() {
        return true;
    }

    @Override
    public void success(CraftDetailBean craftDetailBean) {
        this.craftDetailBean = craftDetailBean;
        textViewList.get(0).setText(craftDetailBean.getCraftsName());
        textViewList.get(1).setText(String.valueOf(craftDetailBean.getNumber1()));
        textViewList.get(2).setText(String.valueOf(craftDetailBean.getReportedCount()));
        textViewList.get(3).setText(craftDetailBean.getCraftsStatusStr());
        textViewList.get(4).setText(craftDetailBean.getCrews());
        textViewList.get(5).setText(craftDetailBean.getFirstReportTime());
        textViewList.get(6).setText(craftDetailBean.getLastReportTime());
        textViewList.get(7).setText(craftDetailBean.getHandoverTime());
        textViewList.get(8).setText(craftDetailBean.getRegTime());
        orderNumTv.setText(craftDetailBean.getProduceGoodsReceiptNO());
        goodsNameTv.setText(craftDetailBean.getGoodsName());
        specNameTv.setText(craftDetailBean.getSpecName());
        lengthTv.setText(craftDetailBean.getLength());
        if (!TextUtils.isEmpty(craftDetailBean.getPicURL()))
            Glide.with(this).load(craftDetailBean.getPicURL()).into(imageView);
        if (!TextUtils.isEmpty(craftDetailBean.getCmdPicURL()))
            Glide.with(this).load(craftDetailBean.getCmdPicURL()).into(commandIv);
        if (craftDetailBean.getCmd() == null || craftDetailBean.getCmd().isEmpty()) {
            CraftDetailBean.CmdBean cmdBean = new CraftDetailBean.CmdBean();
            cmdBean.setContent(nodataStr);
            crafDetailAdapter.getData().clear();
            crafDetailAdapter.addData(cmdBean);
        } else {
            crafDetailAdapter.setNewData(craftDetailBean.getCmd());
        }
    }

    @Override
    public void successUserList(List<UserListBean> listBeans) {
        ARouter.getInstance().build(UserListActivity.AROUTER_PATH)
                .withBoolean(SelectUserActivity.VISIBLE, true)
                .withString(SelectUserActivity.USER_LIST_BEAN_KEY, new Gson().toJson(listBeans))
                .navigation();
    }

    @Override
    public void successSub() {
        mTts.startSpeaking(status == 0 ? "工艺单强制完成提交成功" : "工艺单完成提交成功", this);
    }

    @OnClick({R.id.submit_btn, R.id.add_report_btn, R.id.order_num_ly})
    public void submit(View view) {
        switch (view.getId()) {
            case R.id.submit_btn:
                ARouter.getInstance().build(ReportActivity.AROUTER_PATH)
                        .withInt(RelationOrderListActivity.PRODUCE_ID_KEY, craftDetailBean.getCraftsReceiptID())
                        .navigation();
                break;
            case R.id.add_report_btn:
                ARouter.getInstance().build(AddReportActivity.AROUTER_PATH)
                        .withString(REAL_COUNT_KEY, String.valueOf(craftDetailBean.getNumber1()))
                        .withString(PACK_COUNT_KEY, String.valueOf(craftDetailBean.getReportedCount()))
                        .withInt(RelationOrderListActivity.PRODUCE_ID_KEY, craftDetailBean.getCraftsReceiptID())
                        .navigation();
                break;

            case R.id.order_num_ly:
                ARouter.getInstance().build(GoodsDetailActivity.AROUTER_PATH).withInt(GoodsDetailActivity.PARAM_ID, craftDetailBean.getProduceGoodsReceiptID()).navigation();
                break;

        }
    }

    @OnClick(R.id.imgage_iv)
    public void img() {
        ARouter.getInstance().build(PhotoActivity.AROUTER_PATH).withString(PhotoActivity.PHOTO_URL_KEY, craftDetailBean.getPicURL()).navigation();
    }

    @OnMenuOnclick
    public void menuOnclick(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menu_detail) {
            ARouter.getInstance().build(RelationOrderListActivity.AROUTER_PATH)
                    .withInt(RelationOrderListActivity.PRODUCE_ID_KEY, craftDetailBean.getProduceGoodsReceiptID())
                    .navigation();
            return;
        }
        switch (menuItem.getItemId()) {
            case R.id.menu_cancel:
                status = 0;
                dialog.setMessage(forceComplete);
                mTts.startSpeaking(forceComplete, this);
                break;
            case R.id.menu_complete:
                status = 1;
                dialog.setMessage(complete);
                mTts.startSpeaking(complete, this);
                break;
        }
        dialog.show();
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

    @Override
    public void finish() {
        EventBus.getDefault().post(new MessageEvent(NLeaderFragment.REFRESH_CODE, ""));
        super.finish();
    }
}
