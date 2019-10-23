package com.zh.xfz.business.activity;

import android.support.annotation.NonNull;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.zh.xfz.R;
import com.zh.xfz.mvp.presenter.UserOperationPresenter;
import com.zh.xfz.mvp.presenter.activity.GroupPresenter;
import com.zh.xfz.utils.BottomNavigationViewHelper;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.listener.AppExitListener;
import core.app.zh.com.core.utils.BottomNavigationHelper;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * author : dayezi
 * data :2019/7/30
 * description:
 */
@Route(path = MainActivity.AROUTER_PATH)
public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    public final static String AROUTER_PATH = "/main/MainActivity/";
    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    @Inject
    BottomNavigationHelper bottomNavigationHelper;

    @Inject
    AppExitListener appExitListener;

    @Inject
    List<Fragment> fragmentList;

    @Inject
    RongIMClient.OnReceiveMessageListener onReceiveMessageListener;

    @Inject
    UserOperationPresenter presenter;

    @Inject
    GroupPresenter groupPresenter;

    @Inject
    RongIMClient.ConnectionStatusListener connectionStatusListener;


    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_main;
    }

    @Override
    public void init() {
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        View view = menuView.getChildAt(0);
        Badge badge = new QBadgeView(this).bindTarget(view).setGravityOffset(10, 0, true);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (Fragment fragment : fragmentList) {
            transaction.add(R.id.fragment_, fragment);
            transaction.hide(fragment);
        }
        transaction.show(fragmentList.get(0));
        transaction.commit();
        new Thread(() -> {
            RongIM.getInstance().addUnReadMessageCountChangedObserver(i -> badge.setBadgeNumber(i), Conversation.ConversationType.values());
            RongIM.setOnReceiveMessageListener(onReceiveMessageListener);
        }).start();
        /**
         * 设置连接状态的监听。
         */
        RongIMClient.setConnectionStatusListener(connectionStatusListener);
        setAppExitListener(appExitListener);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        presenter.refreshUserInfo();
        groupPresenter.refreshGroupInfo();
        BottomNavigationViewHelper.setImageSize(bottomNavigationView, this);
        PgyUpdateManager.setIsForced(false);
        PgyUpdateManager.register(this, new UpdateManagerListener() {
            @Override
            public void onNoUpdateAvailable() {

            }

            @Override
            public void onUpdateAvailable(String result) {
                // 将新版本信息封装到AppBean中
                final AppBean appBean = getAppBeanFromString(result);
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("提示")
                        .setMessage("发现新版本,是否更新?")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定",
                                (dialog, which) -> startDownloadTask(
                                        MainActivity.this,
                                        appBean.getDownloadURL())).show();
            }
        });
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
    protected void onDestroy() {
        super.onDestroy();
        PgyUpdateManager.unregister();
//        RongIM.getInstance().removeUnReadMessageCountChangedObserver(unReadMessageObserver);
    }
}
