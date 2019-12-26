package com.zh.xfz.business.fragment;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.StringUtils;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.fragment.BusinessBean;
import com.zh.xfz.business.activity.CreateBusinessActivity;
import com.zh.xfz.business.adapter.BusinessListAdapter;
import com.zh.xfz.mvp.contract.TenantContract;
import com.zh.xfz.mvp.presenter.TenantPresenter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.MyBaseAdapter;
import core.app.zh.com.core.view.ClearEditTextView;

import static com.zh.xfz.constans.Constants.COMPANY_STATUS_APPLY_CODE;
import static com.zh.xfz.constans.RequestParamsConstant.SEARCH;

/**
 * author : dayezi
 * data :2019/12/9
 * description:
 */
@Route(path = CreateBusinessActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "加入商户")
public class BusinessListFragment extends BaseListFragment<BusinessBean> implements TenantContract.BusinessListUI {

    @Inject
    TenantPresenter presenter;

    @Inject
    BusinessListAdapter businessListAdapter;

    @BindView(R.id.et_search)
    ClearEditTextView clearEditTextView;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.fragment_business_list;
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        presenter.onRefreshAllTenant(clearEditTextView.getText().toString());
    }

    @Override
    public void onLoadMoreRequested() {
        swipeRefreshLayout.setRefreshing(true);
        presenter.onLoadMoreAllTenant(clearEditTextView.getText().toString());
    }

    private Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<>();
        params.put(SEARCH, StringUtils.isEmpty(clearEditTextView.getText().toString()) ? "" : clearEditTextView.getText().toString());
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
        businessListAdapter.setOnItemClickListener((adapter, view, position) -> new AlertDialog.Builder(getActivity())
                .setTitle(getResources().getString(R.string.act_normal_title_dialog_msg))
                .setMessage(businessListAdapter.getData().get(position).getTenantName())
                .setPositiveButton(getResources().getString(R.string.act_business_list_sure_dialog_msg), (dialog, which) -> {
                    presenter.applyTenant(String.valueOf(businessListAdapter.getData().get(position).getID()), COMPANY_STATUS_APPLY_CODE);
                    dialog.dismiss();
                }).setNegativeButton(getResources().getString(R.string.alert_cancel_str), (dialog, which) -> dialog.dismiss()).show());
    }

    @Override
    public void clickItemSuccess() {
        getMyActivity().finish();
    }

    @Override
    public void showMsg(String msg) {
        super.showMsg(msg);
        swipeRefreshLayout.setRefreshing(false);
    }

}
