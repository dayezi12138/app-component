package com.zh.xfz.business.fragment;

import android.support.annotation.NonNull;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.BarUtils;
import com.zh.xfz.R;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseFragment;

/**
 * author : dayezi
 * data :2019/10/16
 * description:
 */
public class MainFragment extends BaseFragment {
    @BindView(R.id.ly)
    LinearLayout ly;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.fragment_work_control_center;
    }

    @Override
    public void init() {
        BarUtils.addMarginTopEqualStatusBarHeight(ly);
        BarUtils.setStatusBarColor(getActivity(), getActivity().getResources().getColor(R.color.background_splash_color),1);
    }
}
