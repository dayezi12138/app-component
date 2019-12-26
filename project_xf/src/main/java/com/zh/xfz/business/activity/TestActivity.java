package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zh.xfz.R;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/10/25
 * description:
 */
public class TestActivity extends BaseActivity {

    @BindView(R.id.text_tv)
    TextView textView;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.text_one;
    }

    @Override
    public void init() {

    }

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        setLoadingOptionListener(this);
//        super.onCreate(savedInstanceState);
//    }


    @Override
    public View beforeInit(LayoutInflater inflater, ViewGroup container) {
//        setLoadingOptionListener(this);
        return super.beforeInit(inflater, container);
    }


//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        View view = LayoutInflater.from(this).inflate(R.layout.test_1, null);
//        setContentView(view);
//        MultipleStatusView statusView = findViewById(R.id.status_view);
//        statusView.optionContentView(LayoutInflater.from(this).inflate(R.layout.text_one, null));
//        ButterKnife.bind(this, view);
//    }
}
