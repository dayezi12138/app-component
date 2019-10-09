package com.zh.xfz.business.activity.im;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;

import core.app.zh.com.core.base.BaseActivity;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

/**
 * author : dayezi
 * data :2019/7/31
 * description:
 */
@Route(path = ConversationListActivity.AROUTER_PATH)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color, title = "消息")
public class ConversationListActivity extends BaseActivity {
    public final static String AROUTER_PATH = "/im/ConversationListActivity/";

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_conversation_list;
    }

    @Override
    public void init() {
        FragmentManager fragmentManage = getSupportFragmentManager();
        ConversationListFragment fragment = (ConversationListFragment) fragmentManage.findFragmentById(R.id.conversationlist);
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")
                .build();
        fragment.setUri(uri);
    }
}
