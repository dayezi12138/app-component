package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.SearchFriend;
import com.zh.xfz.mvp.presenter.ConversationPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;

/**
 * author : dayezi
 * data :2019/9/23
 * description:
 */
@Route(path = AddFriendActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color)
public class AddFriendActivity extends BaseActivity {
    public final static String AROUTER_PATH = "/main/AddFriendActivity/";
    public final static String ADD_FRIEND_INFO = "addFriendInfo";

    @Autowired(name = ADD_FRIEND_INFO)
    SearchFriend searchFriend;

    @BindView(R.id.tv_name)
    TextView nameTv;

    @BindView(R.id.alias_tv)
    TextView aliasTv;

    @BindView(R.id.remark_et)
    EditText remarkEt;

    @BindView(R.id.memo_tv)
    EditText memoTv;

    @Inject
    ConversationPresenter conversationPresenter;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_add_friend;
    }

    @Override
    public void init() {
        nameTv.setText(searchFriend.getName());
        aliasTv.setText(searchFriend.getMobile());

    }

    @OnClick(R.id.submit)
    public void submit() {
        conversationPresenter.applyFriend(String.valueOf(searchFriend.getID()), TextUtils.isEmpty(memoTv.getText().toString()) ? "" : memoTv.getText().toString(), TextUtils.isEmpty(remarkEt.getText().toString()) ? nameTv.getText().toString() : remarkEt.getText().toString());
    }
}
