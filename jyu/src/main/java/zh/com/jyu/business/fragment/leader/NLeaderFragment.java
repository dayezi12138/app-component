package zh.com.jyu.business.fragment.leader;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.annatation.toolbar.ToolbarTitle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MessageEvent;
import zh.com.jyu.R;
import zh.com.jyu.business.activity.SearchResultActivity;
import zh.com.jyu.business.adapter.HomePagerAdapter;
import zh.com.jyu.listener.SearchListener;

/**
 * author : dayezi
 * data :2019/6/21
 * description:
 */
@ToolbarTitle(title = "主任")
public class NLeaderFragment extends BaseFragment implements SearchListener {
    public static final int SEARCH_EVENT_CODE = 100004;
    public static final int REFRESH_CODE = 100008;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.mytab)
    TabLayout myTabLayout;

    @BindView(R.id.et_search)
    TextView clearEditTextView;

    @BindString(R.string.fragment_leader_search_hint_str)
    String hintStr;

    @BindView(R.id.clear_iv)
    ImageView clear;

    @Inject
    HomePagerAdapter homePagerAdapter;

    @Inject
    List<Fragment> fragmentList;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.fragment_produce;
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
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(MessageEvent message) {
        if (message.getCode() == SEARCH_EVENT_CODE) {
            if (!TextUtils.isEmpty(message.getMsg())) clear.setVisibility(View.VISIBLE);
            clearEditTextView.setText(message.getMsg());
            fragmentRefresh();
        } else if (message.getCode() == REFRESH_CODE && !isHidden()) {
            fragmentRefresh();
        }
    }

    private void fragmentRefresh() {
        NUnderFragment fragment = (NUnderFragment) fragmentList.get(myTabLayout.getSelectedTabPosition());
        fragment.onRefresh();
    }

    @OnClick(R.id.et_search)
    public void search() {
        ARouter.getInstance().build(SearchResultActivity.AROUTER_PATH)
                .withString(SearchResultActivity.HINT_KEY, hintStr)
                .withInt(SearchResultActivity.SEARCH_KEY_CODE, SEARCH_EVENT_CODE)
                .navigation();
    }

    @OnClick(R.id.clear_iv)
    public void clear() {
        clear.setVisibility(View.GONE);
        clearEditTextView.setText("");
        fragmentRefresh();
    }

    @Override
    public String inputValue() {
        return TextUtils.isEmpty(clearEditTextView.getText().toString()) ? "" : clearEditTextView.getText().toString();
    }
}
