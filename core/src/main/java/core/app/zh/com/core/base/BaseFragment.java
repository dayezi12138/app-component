package core.app.zh.com.core.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.zh.api.ToolBarInject;

import java.util.List;

import core.app.zh.com.core.R;
import core.app.zh.com.core.listener.AddOptionInApplicationListener;
import core.app.zh.com.core.listener.AddOptionInPageListener;
import core.app.zh.com.core.listener.GetActivityListener;
import core.app.zh.com.core.listener.GetPresenter;
import core.app.zh.com.core.listener.LayoutInitListener;
import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends DaggerFragment implements LayoutInitListener, GetActivityListener, BaseView, GetPresenter {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.base_view, container, false);
        rootLy = rootView.findViewById(R.id.root_ly);
        beforeInit(inflater, container);
        if (this.getActivity().getApplication() instanceof AddOptionInApplicationListener) {
            AddOptionInApplicationListener listener = (AddOptionInApplicationListener) this.getActivity().getApplication();
            List<AddOptionInPageListener> list = listener.optionActivityList();
            for (AddOptionInPageListener activityListener : list) {
                activityListener.init(this, rootView);
            }
        }
//        beforeInitTemp();
        init();
        return rootView;
    }

//    public void beforeInitTemp() {
//        if (loadingListener != null && loadingListener.loadingView() != null) {
//            rootLy.addView(loadingListener.loadingView());
//        }
//    }

    private LinearLayout rootLy;
//    private StatusViewListener loadingListener;
    private View contentView;

    @Override
    public View beforeInit(LayoutInflater inflater, ViewGroup container) {
        boolean old = oldAddToolbar();
        if (!old) newAddToolbar();
        if (layoutId() != 0) {
            contentView = LayoutInflater.from(getContext()).inflate(layoutId(), rootLy, false);
            rootLy.addView(contentView);
        }
        return rootView;
    }

    protected void newAddToolbar() {
        boolean addToolbar = ToolBarInject.needAddToolbar(this);
        if (!addToolbar) return;
        Toolbar toolbarLayout = (Toolbar) LayoutInflater.from(getContext()).inflate(R.layout.common_toolbar_, null);
        rootLy.addView(toolbarLayout);
        ToolBarInject.inject(this, toolbarLayout);
    }


    @Deprecated
    private boolean oldAddToolbar() {
        rootLy.removeAllViews();
        boolean oldAddToolbar = addToolbar();
        if (oldAddToolbar) {
            Toolbar toolbarLayout = (Toolbar) LayoutInflater.from(getContext()).inflate(R.layout.common_toolbar_, null);
            rootLy.addView(toolbarLayout);
        }
        return oldAddToolbar;
    }

    @Override
    public BaseActivity getMyActivity() {
        return (BaseActivity) getActivity();
    }

    @Override
    public void showMsg(String msg) {
        if (TextUtils.isEmpty(msg)) return;
        ToastUtils.showShort(msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) getPresenter().detachView();
    }

    @Deprecated
    public boolean addToolbar() {
        return false;
    }

//    public StatusViewListener getLoadingListener() {
//        return loadingListener;
//    }
//
//    public void setLoadingListener(StatusViewListener loadingListener) {
//        this.loadingListener = loadingListener;
//    }

    @Override
    public View myContentView() {
        return contentView;
    }

    public LinearLayout getRootLy() {
        return rootLy;
    }
}
