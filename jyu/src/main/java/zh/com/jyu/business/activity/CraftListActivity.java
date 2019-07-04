package zh.com.jyu.business.activity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.base.MyBaseAdapter;
import core.app.zh.com.core.bean.MessageEvent;
import zh.com.jyu.business.adapter.CraftListActAdapter;
import zh.com.jyu.constans.SpKeyConstant;
import zh.com.jyu.mvp.presenter.activity.CraftListPresenter;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
@Route(path = CraftListActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true)
@ToolbarTitle(title = "工艺单")
public class CraftListActivity extends BaseListActivity {

    public final static String AROUTER_PATH = "/plan/CraftListActivity/";
    public final static String GOODS_ID_KEY = "id";
    @Inject
    CraftListPresenter presenter;

    @Inject
    CraftListActAdapter craftListActAdapter;

    @Autowired(name = GOODS_ID_KEY)
    int goodsId;

    @Override
    public MyBaseAdapter myBaseAdapter() {
        return craftListActAdapter;
    }

    @Override
    public void onItemClick(int position) {
        MessageEvent event = new MessageEvent(CraftDetailActivity.SEARCH_EVENT_CODE, String.valueOf(craftListActAdapter.getData().get(position).getCraftsReceiptID()));
        EventBus.getDefault().post(event);
        ARouter.getInstance().build(CraftDetailActivity.AROUTER_PATH)
                .withInt(CraftDetailActivity.CRAFT_ID_KEY, craftListActAdapter.getData().get(position).getCraftsReceiptID())
                .navigation();
    }

    @Override
    public Map<String, Object> params() {
        Map<String, Object> parm = new HashMap<>();
        parm.put("produceGoodsReceiptId", goodsId);
        parm.put("UserID", SPUtils.getInstance().getInt(SpKeyConstant.LOGIN_UID_KEY));
        return parm;
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

}
