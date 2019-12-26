package com.zh.xfz.business;

import android.animation.Animator;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;

import com.blankj.utilcode.util.ToastUtils;
import com.classic.common.MultipleStatusView;
import com.zh.xfz.R;

import butterknife.BindView;
import core.app.zh.com.core.base.TestBaseActivity;
import core.app.zh.com.core.listener.LoadingOptionListener;

/**
 * author : dayezi
 * data :2019/12/11
 * description:
 */

public class TestActivity extends TestBaseActivity implements LoadingOptionListener {

    MultipleStatusView multipleStatusView;

//    @BindView(R.id.text)
//    TextView textView;
//
//    @BindView(R.id.ripper_view)
//    RippleView rippleView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.toolbar_;
    }

    Animator animator;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void init() {
        toolbar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(animator==null){
                    animator = ViewAnimationUtils.createCircularReveal(toolbar,
                            toolbar.getWidth() / 2, toolbar.getHeight() / 2,
                            0,
                            toolbar.getHeight());
                    animator.setDuration(1000);
                    animator.setInterpolator(new AccelerateInterpolator(10f));
                    animator.start();
                }
            }
        });
//        RefreshLayout refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
//        refreshLayout.setRefreshHeader(new FalsifyHeader(this));
//        refreshLayout.setRefreshFooter(new FalsifyFooter(this));
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
//            }
//        });
//        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(RefreshLayout refreshlayout) {
//                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
//            }
//        });
//        rippleView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                rippleView.setCentered(true);
//                rippleView.setRippleColor(R.color.d);
//                rippleView.animateRipple(ScreenUtils.getScreenWidth() / 2, 0);
//            }
//        });
    }


    @Override
    public View getLoadingView() {
        if (multipleStatusView == null) {
            multipleStatusView = (MultipleStatusView) LayoutInflater.from(this).inflate(R.layout.test_, null);
            multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.showShort("222");
                }
            });
        }
        return multipleStatusView;
    }

    @Override
    public void showLoading() {
        multipleStatusView.showLoading();
    }

    @Override
    public void hideLoading() {
        multipleStatusView.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyView() {
        multipleStatusView.showEmpty();
    }

    @Override
    public void showNoNetWork() {
        multipleStatusView.showNoNetwork();
    }

    @Override
    public void showError() {
        multipleStatusView.showError();
    }
}
