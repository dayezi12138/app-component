package com.zh.xfz.business.activity;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zh.xfz.R;
import com.zh.xfz.mvp.presenter.ConversationPresenter;
import com.zh.xfz.mvp.presenter.UserPresenter;
import com.zh.xfz.utils.BottomNavigationViewHelper;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.listener.AppExitListener;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.UserInfo;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * author : dayezi
 * data :2019/7/30
 * description:
 */
@Route(path = MainActivity.AROUTER_PATH)
public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener, RongIM.UserInfoProvider, RongIM.GroupInfoProvider {
    public final static String AROUTER_PATH = "/main/MainActivity/";

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    @Inject
    AppExitListener appExitListener;

    @Inject
    List<Fragment> fragmentList;

    @Inject
    RongIMClient.OnReceiveMessageListener onReceiveMessageListener;

    @Inject
    UserPresenter userPresenter;

    @Inject
    ConversationPresenter conversationPresenter;

    @Inject
    String PGYID;

    @Inject
    RongIMClient.ConnectionStatusListener connectionStatusListener;

    private Badge badge;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_main;
    }

    @Override
    public void init() {
        initBottomTab();
        initRongIM();
        setAppExitListener(appExitListener);
    }

    private void initRongIM() {
        new Thread(() -> {
            RongIM.getInstance().addUnReadMessageCountChangedObserver(i -> badge.setBadgeNumber(i), Conversation.ConversationType.values());
            RongIM.setOnReceiveMessageListener(onReceiveMessageListener);
            RongIMClient.setConnectionStatusListener(connectionStatusListener);
            RongIM.setUserInfoProvider(this, false);
            RongIM.setGroupInfoProvider(this, false);
        }).start();
    }

    private void initBottomTab() {
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        View view = menuView.getChildAt(0);
        badge = new QBadgeView(this).bindTarget(view).setGravityOffset(10, 0, true);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (Fragment fragment : fragmentList) {
            transaction.add(R.id.fragment_, fragment);
            transaction.hide(fragment);
        }
        transaction.show(fragmentList.get(0));
        transaction.commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        BottomNavigationViewHelper.setImageSize(bottomNavigationView, this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_msg_id:
                setTabPosition(0);
                break;
            case R.id.menu_level_id:
                setTabPosition(1);
                break;
            case R.id.menu_controller_id:
                setTabPosition(2);
                break;
            case R.id.menu_count_id:
                setTabPosition(3);
                break;
            case R.id.menu_my_id:
                setTabPosition(4);
                break;
        }
        return true;
    }

    public void setTabPosition(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragmentList.size(); i++) {
            if (i != index) transaction.hide(fragmentList.get(i));
        }
        transaction.show(fragmentList.get(index));
        transaction.commit();
    }

    @Override
    public UserInfo getUserInfo(String userId) {
        userPresenter.getTargetUserInfo(userId);
        return new UserInfo(userId, "", userPresenter.imageTranslateUri(R.drawable.rc_default_portrait));//根据 userId 去你的用户系统里查询对应的用户信息返回给融云 SDK。
    }


    @Override
    public Group getGroupInfo(String groupId) {
        conversationPresenter.getGroupInfoById(groupId);
        return new Group(groupId, "", Uri.EMPTY);
    }
}
