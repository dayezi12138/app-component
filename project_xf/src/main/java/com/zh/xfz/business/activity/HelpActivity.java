package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.business.adapter.HelpAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.view.ClearEditTextView;

/**
 * author : dayezi
 * data :2019/10/30
 * description:
 */
@Route(path = HelpActivity.AROUTER_PATH)
@ToolbarLeft(menuId = R.menu.menu_contact_me)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white, title = "个人信息")
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "帮助中心")
public class HelpActivity extends BaseActivity {
    public final static String AROUTER_PATH = "/main/HelpActivity/";
    @Inject
    List<String> stringList;

    @Inject
    HelpAdapter adapter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.et_search)
    ClearEditTextView clearEditTextView;


    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_help;
    }

    @Override
    public void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setNewData(stringList);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            if (position == 1) {
                ARouter.getInstance().build(HelpItemActivity.AROUTER_PATH).navigation();
            }
        });
        clearEditTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    adapter.setNewData(searchResult(s.toString()));
                }
            }
        });
    }

    private List<String> searchResult(String searchV) {
        List<String> list = new ArrayList<>();
        for (String data : stringList) {
            if (data.contains(searchV)) list.add(data);
        }
        return list;
    }

    @OnMenuOnclick
    public void menuClick() {
        ARouter.getInstance().build(CsrActivity.AROUTER_PATH).navigation();
    }
}
