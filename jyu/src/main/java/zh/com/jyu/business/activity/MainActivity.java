package zh.com.jyu.business.activity;

import android.support.annotation.NonNull;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.listener.AppExitListener;
import zh.com.jyu.R;
import zh.com.jyu.mvp.presenter.activity.MainPresenter;

/**
 * author : dayezi
 * data :2019/6/10
 * description:
 */
@Route(path = MainActivity.AROUTE_PATH)
public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static final String AROUTE_PATH = "/main/MainActivity/";


    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    @Inject
    AppExitListener appExitListener;

    @Inject
    MainPresenter presenter;

    @Inject
    List<BaseFragment> fragmentList;

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_main;
    }

    @Override
    public void init() {
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        setAppExitListener(appExitListener);
        presenter.addFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.custom_menu_id:
                presenter.setTabPosition(0);
                break;
            case R.id.home_menu_id:
                presenter.setTabPosition(1);
                break;
            case R.id.order_menu_id:
                presenter.setTabPosition(2);
                break;
            case R.id.my_menu_id:
                presenter.setTabPosition(3);
                break;
        }
        return true;
    }
}
