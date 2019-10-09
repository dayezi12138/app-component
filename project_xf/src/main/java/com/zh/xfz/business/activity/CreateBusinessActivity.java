package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.Industry;
import com.zh.xfz.business.adapter.CreateBusinessAdapter;
import com.zh.xfz.mvp.contract.activity.BusinessContract;
import com.zh.xfz.mvp.presenter.activity.BusinessPresenter;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;

import static com.zh.xfz.constans.Constans.FLAG_STR;

/**
 * author : dayezi
 * data :2019/7/29
 * description:
 */
@Route(path = CreateBusinessActivity.AROUTER_PATH)
@ToolbarLeft(menuId = R.menu.create_busi)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "创建商户")
public class CreateBusinessActivity extends BaseActivity implements BusinessContract.BusinessUI {

    public final static String AROUTER_PATH = "/main/CreateBusinessActivity/";

    @Inject
    BusinessPresenter presenter;

    @BindView(R.id.submit_btn)
    Button submitBtn;

    @BindView(R.id.busi_name_et)
    EditText busiNameEt;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Inject
    CreateBusinessAdapter adapter;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_create_busi;
    }

    @Override
    public void init() {
        presenter.getIndustry();
        submitBtn.setAlpha(0.5f);
        busiNameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && !adapter.getSelectData().isEmpty())
                    submitBtn.setAlpha(1f);
                else submitBtn.setAlpha(0.5f);
            }
        });
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnClickAfterListener(hasSelect -> {
            if (hasSelect && !TextUtils.isEmpty(busiNameEt.getText().toString()))
                submitBtn.setAlpha(1f);
            else submitBtn.setAlpha(0.5f);
        });
    }

    @Override
    public void success(List<Industry> dataList) {
        Collections.sort(dataList);
        adapter.setNewData(dataList);
    }

    @Override
    public void successSubmit() {
        ARouter.getInstance().build(MainActivity.AROUTER_PATH).navigation();
        finish();
    }

    @OnClick(R.id.submit_btn)
    public void submit() {
        StringBuilder industryIds = new StringBuilder();
        for (Industry industry : adapter.getSelectData()) {
            industryIds.append(String.valueOf(industry.getID())).append(FLAG_STR);
        }
        presenter.existTenantName(busiNameEt.getText().toString(), industryIds.substring(0, industryIds.length() - 1));
    }

    @OnMenuOnclick
    public void menuClick(MenuItem menuItem) {
        ARouter.getInstance().build(MainActivity.AROUTER_PATH).navigation();
        finish();
    }
}
