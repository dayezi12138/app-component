package com.zh.xfz.business.fragment;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.StringUtils;
import com.zh.xfz.R;
import com.zh.xfz.bean.fragment.BusinessBean;
import com.zh.xfz.business.activity.CreateBusinessActivity;
import com.zh.xfz.business.activity.MainActivity;
import com.zh.xfz.business.adapter.BusinessListAdapter;
import com.zh.xfz.mvp.contract.TenantContract;
import com.zh.xfz.mvp.presenter.TenantPresenter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.MyBaseAdapter;
import core.app.zh.com.core.view.ClearEditTextView;

/**
 * author : dayezi
 * data :2019/12/9
 * description:
 */
//@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white, title = "我")
//@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "加入商户")
public class BusinessListFragment extends BaseListFragment<BusinessBean> implements TenantContract.BusinessListUI {

    @Inject
    TenantPresenter presenter;

    @Inject
    BusinessListAdapter businessListAdapter;

    @BindView(R.id.et_search)
    ClearEditTextView clearEditTextView;

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.fragment_business_list;
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        presenter.onRefresh(getParams());
    }

    @Override
    public void onLoadMoreRequested() {
        swipeRefreshLayout.setRefreshing(true);
        presenter.onLoad(getParams());
    }

    private Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("search", StringUtils.isEmpty(clearEditTextView.getText().toString()) ? "" : clearEditTextView.getText().toString());
        return params;
    }

    @Override
    public MyBaseAdapter getMyBaseAdapter() {
        return businessListAdapter;
    }

    @Override
    public void refreshBeforeInit() {
        clearEditTextView.setmClearDrawableRecourse(R.drawable.ic_clear_gray);
        clearEditTextView.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                //处理事件
                onRefresh();
            }
            return false;
        });
        clearEditTextView.setOnClickImageListener(() -> onRefresh());
    }

    @Override
    public void refreshBeforeAfter() {
        businessListAdapter.setOnItemClickListener((adapter, view, position) -> new AlertDialog.Builder(getActivity()).setTitle("提示")
                .setMessage(businessListAdapter.getData().get(position).getTenantName())
                .setPositiveButton("申请加入", (dialog, which) -> {
                    presenter.applyTenant(String.valueOf(businessListAdapter.getData().get(position).getID()), 1);
                    dialog.dismiss();
                }).setNegativeButton("取消", (dialog, which) -> dialog.dismiss()).show());
    }

    @Override
    public void clickItemSuccess() {
        ARouter.getInstance().build(MainActivity.AROUTER_PATH).navigation();
    }

    @OnClick(R.id.navigation_tv)
    public void clicknavigation() {
        clickItemSuccess();
    }

    @OnClick(R.id.create_tv)
    public void create() {
        ARouter.getInstance().build(CreateBusinessActivity.AROUTER_PATH).navigation();
    }
}
