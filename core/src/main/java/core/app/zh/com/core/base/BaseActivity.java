package core.app.zh.com.core.base;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zh.api.ToolBarInject;

import java.util.List;

import core.app.zh.com.core.R;
import core.app.zh.com.core.bean.ToolbarBean;
import core.app.zh.com.core.bean.ToolbarMenu;
import core.app.zh.com.core.bean.ToolbarNavigation;
import core.app.zh.com.core.bean.ToolbarTitle;
import core.app.zh.com.core.factory.ToolbarStrategyFactory;
import core.app.zh.com.core.listener.AddOptionInApplicationListener;
import core.app.zh.com.core.listener.AddOptionInPageListener;
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
    private View rootContentView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view1 = LayoutInflater.from(this).inflate(R.layout.base_view, null);
        setContentView(view1);
        ViewGroup viewGroup = findViewById(R.id.root_ly);
        View view = beforeInit(LayoutInflater.from(this), viewGroup);
        if (this.getApplication() instanceof AddOptionInApplicationListener) {
            AddOptionInApplicationListener listener = (AddOptionInApplicationListener) this.getApplication();
            List<AddOptionInPageListener> list = listener.optionActivityList();
            for (AddOptionInPageListener activityListener : list) {
                activityListener.init(this, view);
            }
        }
        init();
    }


    @Override
    public View beforeInit(LayoutInflater inflater, ViewGroup container) {
        boolean old = oldAddToolbar(container);
        if (!old) newAddToolbar(container);
        if (layoutId() != 0) {
            rootContentView = inflater.inflate(layoutId(), container, false);
            container.addView(rootContentView);
        } else rootContentView = container;
        return container;
    }

    protected void newAddToolbar(ViewGroup container) {
        boolean addToolbar = ToolBarInject.needAddToolbar(this);
        if (!addToolbar) return;
        Toolbar toolbarLayout = (Toolbar) LayoutInflater.from(this).inflate(R.layout.common_toolbar_, null);
        container.addView(toolbarLayout);
        ToolBarInject.inject(this, toolbarLayout);
    }


    @Deprecated
    private boolean oldAddToolbar(ViewGroup container) {
        container.removeAllViews();
        boolean oldAddToolbar = addToolbar();
        if (oldAddToolbar) {
            Toolbar toolbarLayout = (Toolbar) LayoutInflater.from(this).inflate(R.layout.common_toolbar_, null);
            container.addView(toolbarLayout);
        }
        return oldAddToolbar;
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

    @Deprecated
    public void setStatusBarColor(View view, @ColorInt int color) {
        BarUtils.addMarginTopEqualStatusBarHeight(view);
        BarUtils.setStatusBarColor(this, color, 1);
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
    @Deprecated
    public void setOnChangeToolbarListener(OnChangeToolbarListener onChangeToolbarListener) {
        this.onChangeToolbarListener = onChangeToolbarListener;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    @Override
    public void showMsg(String msg) {
        ToastUtils.showShort(TextUtils.isEmpty(msg) ? getResources().getString(R.string.toast_msg_empty_str) : msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) getPresenter().detachView();
        if (this.getApplication() instanceof AddOptionInApplicationListener) {
            AddOptionInApplicationListener listener = (AddOptionInApplicationListener) this.getApplication();
            listener.unregister();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && appExitListener != null) {
            appExitListener.exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public ViewGroup myContentView() {
        return (ViewGroup) rootContentView;
    }

}
