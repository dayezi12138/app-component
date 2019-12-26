package com.zh.xfz.business.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.fragment.HelpArticle;
import com.zh.xfz.business.adapter.HelpListAdapter;
import com.zh.xfz.mvp.presenter.HelpPresenter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.MyBaseAdapter;
import core.app.zh.com.core.view.ClearEditTextView;

import static com.zh.xfz.constans.RequestParamsConstant.APP_ID;

/**
 * author : dayezi
 * data :2019/12/25
 * description:
 */
@ToolbarLeft(menuId = R.menu.menu_contact_me)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_light_blue)
@ToolbarTitle(backGroundColorId = R.color.white, textColorId = R.color.textColorPrimary, statusBarColorId = R.color.translucent)
public class HelpListFragment extends SmartBaseListFragment<HelpArticle> {
    public static final String APP_ID_KEY = "APP_ID_KEY";
    public static final String TOOLBAR_TITLE_KEY = "TOOLBAR_TITLE_KEY";
    @Inject
    HelpListAdapter adapter;
    @BindView(R.id.et_search)
    ClearEditTextView clearEditTextView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Inject
    HelpPresenter helpPresenter;
    private String appId;

    @SuppressLint("ValidFragment")
    private HelpListFragment() {
    }

    public static HelpListFragment newInstance(Bundle bundle) {
        HelpListFragment fragment = new HelpListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initRefresh() {
        appId = getArguments().getString(APP_ID_KEY);
        TextView textView = toolbar.findViewById(com.zh.api.R.id.tool_bar_text_view_id_x);
        textView.setText(getArguments().getString(TOOLBAR_TITLE_KEY));
        refreshLayout.autoRefresh();
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.fragment_help_list;
    }

    @Override
    public MyBaseAdapter getMyBaseAdapter() {
        return adapter;
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        helpPresenter.onLoad(getParams());
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        helpPresenter.onRefresh(getParams());
    }

    private Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<>();
        params.put(APP_ID, appId);
        return params;
    }

    @Override
    public boolean needInitRefresh() {
        return !super.needInitRefresh();
    }
}
