package core.app.zh.com.core.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;

import butterknife.ButterKnife;
import core.app.zh.com.core.R;
import core.app.zh.com.core.bean.ToolbarBean;
import core.app.zh.com.core.bean.ToolbarMenu;
import core.app.zh.com.core.bean.ToolbarNavigation;
import core.app.zh.com.core.bean.ToolbarTitle;
import core.app.zh.com.core.factory.ToolbarStrategyFactory;
import core.app.zh.com.core.listener.AppExitListener;
import core.app.zh.com.core.listener.GetActivityListener;
import core.app.zh.com.core.listener.GetPresenter;
import core.app.zh.com.core.listener.LayoutInitListener;
import core.app.zh.com.core.listener.OnChangeToolbarListener;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseActivity extends DaggerAppCompatActivity implements LayoutInitListener, GetActivityListener, BaseView, GetPresenter {
    protected String TAG = this.getClass().getSimpleName();
    private OnChangeToolbarListener onChangeToolbarListener;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private AppExitListener appExitListener;

    LinearLayout rootLy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeInit();
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        init();
    }

    private void beforeInit() {
        setContentView(R.layout.base_view);
        rootLy = findViewById(R.id.root_ly);
        if (addToolbar()) {
            ViewGroup toolbarLayout = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.common_toolbar_,null);
            rootLy.addView(toolbarLayout);
        }
        if (layoutId() != 0) {
            View view = LayoutInflater.from(this).inflate(layoutId(),  rootLy,false);
            rootLy.addView(view);
        }
    }

    @Override
    public BaseActivity getMyActivity() {
        return this;
    }

    /**
     * 设置状态栏字体颜色 后期再添加6.0以下字体颜色设置 小米 魅族 听说有其他方式修改
     *
     * @param isDark
     */
    public void setSystemUiVisibility(boolean isDark) {
        if (isDark) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//黑色
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);//白色
        }
    }

    /**
     * 初始化
     *
     * @param toolbar
     * @param bean
     */
    @Deprecated
    public void initToolBar(Toolbar toolbar, ToolbarBean bean) {
        if (onChangeToolbarListener == null) initToolBar(toolbar, bean, true);
        else onChangeToolbarListener.onchange(this, toolbar, bean);

    }

    @Deprecated
    public void initToolBar(Toolbar toolbar, ToolbarBean bean, boolean isDark) {
        setSystemUiVisibility(isDark);
        ToolbarStrategyFactory.put(ToolbarNavigation.class.getSimpleName(), bean.getNavigation());
        ToolbarStrategyFactory.put(ToolbarTitle.class.getSimpleName(), bean.getToolbarTitle());
        ToolbarStrategyFactory.put(ToolbarMenu.class.getSimpleName(), bean.getMenu());
        ToolbarStrategyFactory.getPunish(ToolbarNavigation.class.getSimpleName()).punish(this, toolbar, bean.getNavigation());
        ToolbarStrategyFactory.getPunish(ToolbarTitle.class.getSimpleName()).punish(this, toolbar, bean.getToolbarTitle());
        ToolbarStrategyFactory.getPunish(ToolbarMenu.class.getSimpleName()).punish(this, toolbar, bean.getMenu());
        toolbar.setBackgroundColor(bean.getBackgroundColor());
        BarUtils.addMarginTopEqualStatusBarHeight(toolbar);
        BarUtils.setStatusBarColor(this, bean.getBackgroundColor(), 1);
    }

    /**
     * 添加标题栏
     *
     * @return
     */
    public boolean addToolbar() {
        return true;
    }

    /**
     * 添加退出
     *
     * @param appExitListener
     */
    public void setAppExitListener(AppExitListener appExitListener) {
        this.appExitListener = appExitListener;
    }

    /**
     * 不想用默认的标题栏，可实现该接口
     *
     * @param onChangeToolbarListener
     */
    public void setOnChangeToolbarListener(OnChangeToolbarListener onChangeToolbarListener) {
        this.onChangeToolbarListener = onChangeToolbarListener;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    @Override
    public void showMsg(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) getPresenter().detachView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && appExitListener != null) {
            appExitListener.exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

}
