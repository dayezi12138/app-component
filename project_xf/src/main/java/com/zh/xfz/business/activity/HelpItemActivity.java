package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hgdendi.expandablerecycleradapter.BaseExpandableRecyclerViewAdapter;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.adapter.ChildBean;
import com.zh.xfz.bean.adapter.GroupBean;
import com.zh.xfz.business.adapter.HelpItemAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/10/30
 * description:
 */
@Route(path = HelpItemActivity.AROUTER_PATH)
@ToolbarLeft(menuId = R.menu.menu_contact_me)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "帮助中心")
public class HelpItemActivity extends BaseActivity {
    public final static String AROUTER_PATH = "/main/HelpItemActivity/";

    @Inject
    HelpItemAdapter helpItemAdapter;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Inject
    List<GroupBean> groupBeans;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_help;
    }

    @Override
    public void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(helpItemAdapter);
        helpItemAdapter.setData(groupBeans);
        helpItemAdapter.setListener(new BaseExpandableRecyclerViewAdapter.ExpandableRecyclerViewOnClickListener<GroupBean, ChildBean>() {
            @Override
            public boolean onGroupLongClicked(GroupBean groupItem) {
                return false;
            }

            @Override
            public boolean onInterceptGroupExpandEvent(GroupBean groupItem, boolean isExpand) {
                return false;
            }

            @Override
            public void onGroupClicked(GroupBean groupItem) {

            }

            @Override
            public void onChildClicked(GroupBean groupItem, ChildBean childItem) {
                ARouter.getInstance().build(HelpDetailActivity.AROUTER_PATH).navigation();
            }
        });
    }
}
