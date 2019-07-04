package zh.com.jyu.business.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MessageEvent;
import zh.com.jyu.R;
import zh.com.jyu.business.adapter.HomePagerAdapter;
import zh.com.jyu.business.fragment.plan.NPlanFragment;
import zh.com.jyu.listener.SearchListener;

/**
 * author : dayezi
 * data :2019/6/27
 * description:
 */
@Route(path = PlanActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true)
@ToolbarTitle(title = "生产货品单")
public class PlanActivity extends BaseActivity implements SearchListener {
    public static final String AROUTER_PATH = "/plan/PlanActivity/";
    public static final int SEARCH_EVENT_CODE = 100003;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.mytab)
    TabLayout myTabLayout;

    @BindView(R.id.et_search)
    TextView clearEditTextView;

    @Inject
    HomePagerAdapter homePagerAdapter;

    @Inject
    List<Fragment> fragmentList;

    @BindString(R.string.fragment_plan_search_hint_str)
    String hintStr;

    @BindView(R.id.clear_iv)
    ImageView clear;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_plan;
    }

    @Override
    public void init() {
        clearEditTextView.setHint(hintStr);
        viewPager.setAdapter(homePagerAdapter);
        myTabLayout.setupWithViewPager(viewPager, true);
        viewPager.setOffscreenPageLimit(homePagerAdapter.getCount());
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(MessageEvent message) {
        if (message.getCode() == SEARCH_EVENT_CODE) {
            if (!TextUtils.isEmpty(message.getMsg())) clear.setVisibility(View.VISIBLE);
            clearEditTextView.setText(message.getMsg());
            fragmentRefresh();
        }
    }

    @OnClick(R.id.clear_iv)
    public void clear() {
        clear.setVisibility(View.GONE);
        clearEditTextView.setText("");
        fragmentRefresh();
    }

    private void fragmentRefresh() {
        NPlanFragment fragment = (NPlanFragment) fragmentList.get(myTabLayout.getSelectedTabPosition());
        fragment.onRefresh();
    }

    @OnClick(R.id.et_search)
    public void search() {
        ARouter.getInstance().build(SearchResultActivity.AROUTER_PATH)
                .withString(SearchResultActivity.HINT_KEY, hintStr)
                .withInt(SearchResultActivity.SEARCH_KEY_CODE, SEARCH_EVENT_CODE)
                .navigation();
    }

    @Override
    public String inputValue() {
        return TextUtils.isEmpty(clearEditTextView.getText().toString()) ? "" : clearEditTextView.getText().toString();
    }
}
