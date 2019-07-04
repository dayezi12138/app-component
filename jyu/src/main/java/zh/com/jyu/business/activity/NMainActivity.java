package zh.com.jyu.business.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MessageEvent;
import zh.com.jyu.R;
import zh.com.jyu.business.fragment.HomeFragment;
import zh.com.jyu.business.fragment.PlanFragment;
import zh.com.jyu.listener.IntentSearchLister;

/**
 * author : dayezi
 * data :2019/6/21
 * description:
 */
@Route(path = NMainActivity.AROUTE_PATH)
public class NMainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    public static final String AROUTE_PATH = "/main/NMainActivity/";

    @Inject
    List<BaseFragment> fragmentList;


    @BindView(R.id.tabs)
    RadioGroup radioGroup;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_n_main;
    }

    @Override
    public void init() {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        for (Fragment fragment : fragmentList) {
            transaction.add(R.id.fragment_, fragment);
            transaction.hide(fragment);
        }
        transaction.show(fragmentList.get(0));
        transaction.commit();
        radioGroup.setOnCheckedChangeListener(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(MessageEvent message) {
        if (message.getCode() == SearchResultActivity.SEARCH_EVENT_CODE) {
            if (fragmentList.get(positon) instanceof HomeFragment) {
                IntentSearchLister lister = (IntentSearchLister) ((HomeFragment) fragmentList.get(positon)).getFragment();
                lister.sendSearchValue(message.getMsg());
                return;
            }

            if (fragmentList.get(positon) instanceof IntentSearchLister) {
                IntentSearchLister lister = (IntentSearchLister) fragmentList.get(positon);
                lister.sendSearchValue(message.getMsg());
            }
            if (fragmentList.get(positon) instanceof PlanFragment) {
                IntentSearchLister lister = (IntentSearchLister) ((PlanFragment) fragmentList.get(positon)).getFragment();
                lister.sendSearchValue(message.getMsg());
                return;
            }

        }
    }

    private int positon = 0;

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i <= group.getChildCount(); i++) {
            if (group.getChildAt(i).getId() == checkedId) {
                showFragment(i);
                positon = i;
                return;
            }
        }
    }

    private void showFragment(int position) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        for (Fragment fragment : fragmentList) {
            transaction.hide(fragment);
        }
        transaction.show(fragmentList.get(position));
        transaction.commit();
    }
}
