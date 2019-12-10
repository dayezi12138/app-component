package core.app.zh.com.core.base;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.List;

import core.app.zh.com.core.bean.FragmentInfo;

/**
 * author : dayezi
 * data :2019/12/9
 * description:需要增加fragment的Activity页面
 */
public abstract class MyFragmentActivity extends BaseActivity {


    @NonNull
    @Override
    public int layoutId() {
        return 0;
    }

    @Override
    public void init() {
        if (fragmentList() == null && fragmentList().isEmpty()) return;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        LinearLayout layout = linearLayout();
        ViewGroup viewGroup = (ViewGroup) myContentView();
        for (FragmentInfo info : fragmentList()) {
            FrameLayout frameLayout = new FrameLayout(this);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(info.getWidth(), info.getHeight());
            frameLayout.setLayoutParams(params);
            frameLayout.setId(info.getFragmentLayoutId());
            transaction.add(info.getFragmentLayoutId(), info.getFragment());
            viewGroup.addView(frameLayout);
        }
        transaction.commit();
    }

    private LinearLayout linearLayout() {
        ScrollView scrollView = new ScrollView(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView.setLayoutParams(params);
        LinearLayout layout = new LinearLayout(this);
        ScrollView.LayoutParams params1 = new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(params1);
        layout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(layout);
        ViewGroup viewGroup = (ViewGroup) myContentView();
        viewGroup.addView(scrollView);
        return layout;
    }

    public abstract List<FragmentInfo> fragmentList();
}
