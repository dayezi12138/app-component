package com.zh.xfz.business.activity;

import android.app.Dialog;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.shehuan.niv.NiceImageView;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.xfz.R;
import com.zh.xfz.bean.activity.SearchFriend;
import com.zh.xfz.bean.activity.TargetUserInfo;
import com.zh.xfz.bean.fragment.FriendInfo;
import com.zh.xfz.business.fragment.ContactFragment;
import com.zh.xfz.mvp.contract.ConversationContract;
import com.zh.xfz.mvp.presenter.ConversationPresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.bean.MessageEvent;
import core.app.zh.com.core.view.MyPopupWindow;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

/**
 * author : dayezi
 * data :2019/9/20
 * description:
 */
@Route(path = FriendDetailActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true, iconId = R.drawable.ic_back_white)
@ToolbarTitle(backGroundColorId = R.color.background_splash_color)
@ToolbarLeft(menuId = R.menu.friend_detail)
public class FriendDetailActivity extends BaseActivity implements View.OnClickListener, ConversationContract.FriendDetailInfoUI, MaterialDialog.SingleButtonCallback {
    public final static String AROUTER_PATH = "/main/FriendDetailActivity/";
    public final static String FRIEND_DETAIL_KEY = "FRIEND_DETAIL_KEY";

    @Autowired(name = FRIEND_DETAIL_KEY)
    FriendInfo friendInfo;

    @BindView(R.id.img)
    NiceImageView niceImageView;

    @BindView(R.id.tv_name)
    TextView nameTv;

    @BindView(R.id.memo)
    TextView memo;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.submit_btn)
    TextView btn;

    @Inject
    MyPopupWindow popupWindow;

    @Inject
    ConversationPresenter conversationPresenter;

    @Inject
    Dialog dialog;

    private TextView textView;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_friend_detail;
    }

    @Override
    public void init() {
        textView = new TextView(this);
        Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER;
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setGravity(Gravity.CENTER);
        toolbar.addView(textView);
        conversationPresenter.getFriendDetailInfo(String.valueOf(friendInfo.getTargetId()));
    }

    @OnClick(R.id.submit_btn)
    public void submit() {
        if (targetUserInfo.isStatus())
            RongIM.getInstance().startPrivateChat(this, String.valueOf(friendInfo.getTargetId()), friendInfo.getName());
        else {
            SearchFriend searchFriend = new SearchFriend();
            searchFriend.setChineseName(targetUserInfo.getChineseName());
            searchFriend.setID(targetUserInfo.getTargetId());
            searchFriend.setName(targetUserInfo.getChineseName());
            searchFriend.setMobile(targetUserInfo.getMobile());
            searchFriend.setUserIcon(targetUserInfo.getUserIcon());
            ARouter.getInstance().build(AddFriendActivity.AROUTER_PATH).withSerializable(AddFriendActivity.ADD_FRIEND_INFO, searchFriend).navigation();
        }
    }

    @OnClick(R.id.memo)
    public void updateMemo() {
        dialog.show();
    }

    @OnMenuOnclick
    public void menuClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_friend_operation:
                if (popupWindow.isShowing()) return;
                popupWindow.setBackgroundAlpha();
                popupWindow.showAtLocation(toolbar, Gravity.BOTTOM, 0, 0);
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete:
                conversationPresenter.deleteFriend(String.valueOf(friendInfo.getID()));
                break;
            case R.id.cancel:
                deleteFriendSuccess();
                break;
        }
    }

    private TargetUserInfo targetUserInfo;

    @Override
    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
        EditText editText = dialog.getCustomView().findViewById(R.id.memo);
        conversationPresenter.updateFriendMemo(String.valueOf(friendInfo.getID()), editText.getText().toString());
        editText.setText("");
        dialog.dismiss();
    }

    @Override
    public void friendInfo(TargetUserInfo targetUserInfo) {
        this.targetUserInfo = targetUserInfo;
        nameTv.setText(targetUserInfo.getChineseName());
        textView.setText(targetUserInfo.getChineseName());
        memo.setText(targetUserInfo.getRemarkName());
        if (!TextUtils.isEmpty(targetUserInfo.getUserIcon())) {
            Glide.with(this).load(Uri.parse(targetUserInfo.getUserIcon())).into(niceImageView);
        } else {
            niceImageView.setBackgroundResource(R.drawable.rc_default_portrait);
        }
        String text = targetUserInfo.isStatus() ? getResources().getString(R.string.act_send_msg_btn_text_msg) : getResources().getString(R.string.act_add_contact_btn_text_msg);
        btn.setText(text);
    }

    @Override
    public void deleteFriendSuccess() {
        if (popupWindow.isShowing()) popupWindow.dismiss();
        RongIM.getInstance().removeConversation(Conversation.ConversationType.PRIVATE, String.valueOf(targetUserInfo.getTargetId()), null);
        MessageEvent event = new MessageEvent(ContactFragment.CONTACT_EVENT_KEY, "");
        EventBus.getDefault().post(event);
        finish();
    }

    @Override
    public void updateFriendSuccess(String name) {
        memo.setText(name);
        Uri uri = StringUtils.isEmpty(targetUserInfo.getUserIcon()) ? null : Uri.parse(targetUserInfo.getUserIcon());
        UserInfo userInfo = new UserInfo(String.valueOf(this.targetUserInfo.getTargetId()), name, uri);
        RongIM.getInstance().refreshUserInfoCache(userInfo);
        MessageEvent msg = new MessageEvent(ContactFragment.CONTACT_EVENT_KEY, "");
        EventBus.getDefault().post(msg);
    }
}
