package com.zh.xfz.business.activity.im;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.business.activity.GroupDetailActivity;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.utils.KeyboardPatch;
import io.rong.imkit.fragment.ConversationFragment;

/**
 * author : dayezi
 * data :2019/7/31
 * description:
 */
@Route(path = ConversationActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color)
public class ConversationActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {
    public final static String AROUTER_PATH = "/im/ConversationActivity/";
    private final static int CONVER_TITLE_KEY = 36010;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private TextView textView;
    private String targetId;
    private KeyboardPatch keyboardPatch;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_conversation;
    }

    @Override
    public void init() {
        if (getIntent() != null) {
            Uri uri = getIntent().getData();
            String title = uri.getQueryParameter("title");
            targetId = uri.getQueryParameter("targetId");
            textView = new TextView(this);
            Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.MATCH_PARENT);
            layoutParams.gravity = Gravity.CENTER;
            textView.setLayoutParams(layoutParams);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            textView.setTextColor(getResources().getColor(R.color.white));
            textView.setGravity(Gravity.CENTER);
            textView.setText(title);
            toolbar.addView(textView);
            if (uri.getPath().contains("group")) {
                toolbar.inflateMenu(R.menu.group_conversation);
                toolbar.setOnMenuItemClickListener(this::onMenuItemClick);
            }
            keyboardPatch = KeyboardPatch.patch(this, myContentView());
            keyboardPatch.enable();
            FragmentManager fragmentManage = getSupportFragmentManager();
            ConversationFragment fragment = (ConversationFragment) fragmentManage.findFragmentById(R.id.conversation);
            fragment.setUri(uri);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONVER_TITLE_KEY && resultCode == RESULT_OK && data.hasExtra("title")) {
            textView.setText(data.getStringExtra("title"));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (keyboardPatch != null)
            keyboardPatch.disable();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.add_person:
                if (TextUtils.isEmpty(targetId))
                    showMsg(getResources().getString(R.string.rong_tip_target_id_empty_error_msg));
                else
                    ARouter.getInstance().build(GroupDetailActivity.AROUTER_PATH).withString(GroupDetailActivity.ADD_GROUP, targetId).navigation(this, CONVER_TITLE_KEY);
                break;
        }
        return true;
    }
}
