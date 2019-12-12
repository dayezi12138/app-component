package com.zh.xfz.business.activity.im;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;

import core.app.zh.com.core.base.BaseActivity;
import io.rong.imkit.fragment.SubConversationListFragment;

/**
 * author : dayezi
 * data :2019/8/1
 * description:
 */
@Route(path = SubConversationListActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color,title = "系统消息")
public class SubConversationListActivity extends BaseActivity {
    public final static String AROUTER_PATH = "/im/SubConversationListActivity/";

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_sub_conversation_list;
    }

    @Override
    public void init() {
        if (getIntent() != null) {
            FragmentManager fragmentManage = getSupportFragmentManager();
            SubConversationListFragment fragement = (SubConversationListFragment) fragmentManage.findFragmentById(R.id.subconversationlist);
            Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                    .appendPath("subconversationlist")
                    .appendQueryParameter("type", getIntent().getData().getQueryParameter("type"))
                    .build();
            fragement.setUri(uri);
        }
    }
}
