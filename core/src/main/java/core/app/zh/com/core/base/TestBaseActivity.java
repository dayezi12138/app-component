package core.app.zh.com.core.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.zh.api.ToolBarInject;

import butterknife.ButterKnife;
import core.app.zh.com.core.R;
import core.app.zh.com.core.annotation.Loading;
import core.app.zh.com.core.listener.LayoutInitListener;

import static core.app.zh.com.core.listener.LoadingOptionListener.LoadingOperation.INIT_VIEW;

/**
 * author : dayezi
 * data :2019/12/19
 * description:beta 测试版基础activity
 */
public abstract class TestBaseActivity extends AppCompatActivity implements LayoutInitListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootLy = LayoutInflater.from(this).inflate(R.layout.test_base_view, null);
        setContentView(rootLy);
        initToolbar();
        initContentView();
        ButterKnife.bind(this);
        init();
    }


    @Loading(INIT_VIEW)
    private void initContentView() {
        FrameLayout frameLayout = findViewById(R.id.content_fl);
        if (layoutId() != 0) {
            frameLayout.addView(LayoutInflater.from(this).inflate(layoutId(), frameLayout, false));
        }
    }

    private void initToolbar() {
        LinearLayout view = findViewById(R.id.toolbar_ly);
        boolean addToolbar = ToolBarInject.needAddToolbar(this);
        if (!addToolbar || view == null) return;
        Toolbar toolbarLayout = (Toolbar) LayoutInflater.from(this).inflate(R.layout.common_toolbar_, view, false);
        view.addView(toolbarLayout);
        ToolBarInject.inject(this, toolbarLayout);
    }

    @Override
    public View beforeInit(LayoutInflater inflater, ViewGroup container) {
        return null;
    }

    @Override
    public ViewGroup myContentView() {
        return findViewById(R.id.content_fl);
    }
}
