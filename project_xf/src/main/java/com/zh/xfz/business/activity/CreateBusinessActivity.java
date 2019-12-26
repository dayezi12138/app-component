package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.Industry;
import com.zh.xfz.business.adapter.CreateBusinessAdapter;
import com.zh.xfz.listener.MyTextWatcher;
import com.zh.xfz.mvp.contract.TenantContract;
import com.zh.xfz.mvp.presenter.TenantPresenter;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.utils.KeyboardPatch;

import static com.zh.xfz.constans.Constants.FLAG_STR;

/**
 * author : dayezi
 * data :2019/7/29
 * description:
 */
@Route(path = CreateBusinessActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "创建商户")
public class CreateBusinessActivity extends BaseActivity implements TenantContract.CreateBusinessUI {

    public final static String AROUTER_PATH = "/main/CreateBusinessActivity/";

    @Inject
    TenantPresenter tenantPresenter;

    @BindView(R.id.submit_btn)
    Button submitBtn;

    @BindView(R.id.busi_name_et)
    EditText busiNameEt;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Inject
    CreateBusinessAdapter adapter;

    private KeyboardPatch keyboardPatch;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_create_busi;
    }

    @Override
    public void init() {
        tenantPresenter.getIndustry();
        submitBtn.setAlpha(0.5f);
        busiNameEt.addTextChangedListener((MyTextWatcher) s -> {
            if (!TextUtils.isEmpty(s) && !adapter.getSelectData().isEmpty())
                submitBtn.setAlpha(1f);
            else submitBtn.setAlpha(0.5f);
        });
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnClickAfterListener(hasSelect -> {
            if (hasSelect && !TextUtils.isEmpty(busiNameEt.getText().toString()))
                submitBtn.setAlpha(1f);
            else submitBtn.setAlpha(0.5f);
        });
        keyboardPatch = KeyboardPatch.patch(this, myContentView());
        keyboardPatch.enable();
    }

    @OnClick(R.id.submit_btn)
    public void submit() {
        StringBuilder industryIds = new StringBuilder();
        for (Industry industry : adapter.getSelectData()) {
            industryIds.append(String.valueOf(industry.getID())).append(FLAG_STR);
        }
        tenantPresenter.createTenant(busiNameEt.getText().toString(), industryIds.substring(0, industryIds.length() - 1));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (keyboardPatch != null)
            keyboardPatch.disable();
    }

    @Override
    public void industryList(List<Industry> dataList) {
        Collections.sort(dataList);
        adapter.setNewData(dataList);
    }

}
