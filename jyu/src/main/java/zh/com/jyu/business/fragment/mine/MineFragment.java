package zh.com.jyu.business.fragment.mine;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.api.ToolBarInject;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.annotation.NeedPermission;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.utils.DataCleanManager;
import zh.com.jyu.R;
import zh.com.jyu.bean.fragment.UpdateInfo;
import zh.com.jyu.business.activity.GroupListActivity;
import zh.com.jyu.business.activity.PlanActivity;
import zh.com.jyu.mvp.contract.fragment.MineContract;
import zh.com.jyu.mvp.presenter.fragment.mine.MinePresenter;

/**
 * author : dayezi
 * data :2019/6/18
 * description:
 */
@ToolbarTitle(title = "我的")
public class MineFragment extends BaseFragment implements MineContract.MineUI {

    @BindView(R.id.clear_tv)
    TextView clearTv;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.update_state_tv)
    TextView updateStateTv;

    @BindView(R.id.version_name_tv)
    TextView versionNameTv;

    @Inject
    MaterialDialog dialog;

    Dialog tipDialog;
    @Inject
    MaterialDialog.Builder builder;

    @Inject
    MinePresenter presenter;

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void init() {
        ToolBarInject.inject(this, toolbar);
        tipDialog = builder.onPositive((dialog, which) -> presenter.downLoad(updateInfo.getUrl())).build();
        tipDialog.setCanceledOnTouchOutside(false);
        try {
            clearTv.setText(DataCleanManager.getTotalCacheSize(getActivity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addToolbar() {
        return true;
    }

    @OnClick(R.id.submit_btn)
    public void logout() {
        if (!dialog.isShowing()) dialog.show();
    }

    @OnClick(R.id.clear)
    public void clear() {
        DataCleanManager.clearAllCache(getContext());
        try {
            clearTv.setText(DataCleanManager.getTotalCacheSize(getActivity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.select_group_btn)
    public void selectGroup() {
        ARouter.getInstance().build(GroupListActivity.AROUTER_PATH).navigation();
    }

    @OnClick(R.id.plan_btn)
    public void plan() {
        ARouter.getInstance().build(PlanActivity.AROUTER_PATH).navigation();
    }

    @OnClick(R.id.update_btn)
    @NeedPermission(value = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, next = false)
    public void updateApp() {
        if (!tipDialog.isShowing() && updateStateTv.getVisibility() == View.VISIBLE)
            tipDialog.show();
    }

    private boolean hasInit = false;

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden && !hasInit) {
            hasInit = true;
            presenter.checkUpdate();
        }
    }

    private UpdateInfo updateInfo;

    @Override
    public void success(UpdateInfo updateInfo) {
        try {
            this.updateInfo = updateInfo;
            Context context = this.getActivity();
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            if (Integer.valueOf(updateInfo.getVersion()) > pi.versionCode) {
                updateStateTv.setVisibility(View.VISIBLE);
            }
            versionNameTv.setText(pi.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
