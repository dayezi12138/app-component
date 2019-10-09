package zh.com.jyu.business.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.api.ToolBarInject;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MessageEvent;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.GoodsDetail;
import zh.com.jyu.mvp.contract.activity.GoodsDetailContract;
import zh.com.jyu.mvp.presenter.activity.GoodsDetailPresenter;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
@Route(path = GoodsDetailActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true)
@ToolbarTitle(title = "生产单明细")
@ToolbarLeft(menuId = R.menu.good_detail_tool_bar)
public class GoodsDetailActivity extends BaseActivity implements GoodsDetailContract.GoodsDetailUI {
    public static final String AROUTER_PATH = "/plan/GoodsDetailActivity/";
    public static final String PARAM_ID = "id";
    public static final String REFRSH_CODE_KEY = "REFRSH_CODE_KEY";

    @Autowired(name = REFRSH_CODE_KEY)
    int refreshCode;

    @Autowired(name = PARAM_ID)
    int goodsId;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    GoodsDetailPresenter presenter;

    @BindViews({R.id.tradeNos_tv, R.id.produce_tv, R.id.plan_no_tv, R.id.create_time_tv, R.id.pre_tv, R.id.status_tv, R.id.good_name_tv, R.id.specs_tv, R.id.count_tv, R.id.pack_count_tv, R.id.ca_tv, R.id.rate_tv, R.id.length_tv})
    List<TextView> textViewList;

    @BindView(R.id.imgage_iv)
    ImageView imageView;

    private int id;

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_goods_detail;
    }

    @Override
    public void init() {
        ToolBarInject.inject(this, toolbar);
        presenter.request(goodsId);
    }

    @Override
    public boolean addToolbar() {
        return true;
    }

    @Override
    public void success(GoodsDetail goodsDetail) {
        this.id = goodsDetail.getID();
        textViewList.get(1).setText(goodsDetail.getProduceGoodsReceiptNO());
        textViewList.get(2).setText(goodsDetail.getPlanNO());
        textViewList.get(3).setText(goodsDetail.getRegTime());
        textViewList.get(4).setText(goodsDetail.getPreDate());
        textViewList.get(5).setText(goodsDetail.getStatusStr());
        textViewList.get(6).setText(goodsDetail.getGoodsName());
        textViewList.get(7).setText(goodsDetail.getSpecName());
        textViewList.get(8).setText(goodsDetail.getRealNumber());
        textViewList.get(9).setText(goodsDetail.getPackageCount());
        textViewList.get(10).setText(goodsDetail.getCount2());
        textViewList.get(11).setText(goodsDetail.getRate2());
        textViewList.get(12).setText(goodsDetail.getLength());
        Glide.with(this).load(goodsDetail.getPicURL()).into(imageView);
        this.url = goodsDetail.getPicURL();
    }

    private String url;

    @OnMenuOnclick
    public void menuOnclick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_craft:
                ARouter.getInstance().build(CraftListActivity.AROUTER_PATH).withInt(CraftListActivity.GOODS_ID_KEY, goodsId).navigation();
                break;
            case R.id.menu_picking:
                ARouter.getInstance().build(PickingActivity.AROUTER_PATH).withInt(PickingActivity.PARAM_ID, id).navigation();
                break;
        }
    }

    @OnClick(R.id.imgage_iv)
    public void img() {
        ARouter.getInstance().build(PhotoActivity.AROUTER_PATH).withString(PhotoActivity.PHOTO_URL_KEY, url).navigation();
    }

    @Override
    public void finish() {
        if (refreshCode > 0)
            EventBus.getDefault().post(new MessageEvent(refreshCode, ""));
        super.finish();
    }
}
