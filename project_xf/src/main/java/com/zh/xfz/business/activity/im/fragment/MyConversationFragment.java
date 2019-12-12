package com.zh.xfz.business.activity.im.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.zh.xfz.R;

import io.rong.imkit.fragment.ConversationListFragment;

/**
 * author : dayezi
 * data :2019/8/1
 * description:
 */
public class MyConversationFragment extends ConversationListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(params);
        Toolbar toolbar = (Toolbar) inflater.inflate(R.layout.common_toolbar_, null);
        TextView textView = new TextView(getActivity());
        Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER;
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setGravity(Gravity.CENTER);
        textView.setText("消息");
        toolbar.setBackgroundColor(getActivity().getResources().getColor(R.color.background_splash_color));
        toolbar.addView(textView);
        layout.addView(toolbar);
        BarUtils.addMarginTopEqualStatusBarHeight(toolbar);
        BarUtils.setStatusBarColor(getActivity(), getActivity().getResources().getColor(R.color.background_splash_color), 1);
        layout.addView(view);
        return layout;
    }

}
