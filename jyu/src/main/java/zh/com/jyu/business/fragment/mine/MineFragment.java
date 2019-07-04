package zh.com.jyu.business.fragment.mine;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.api.ToolBarInject;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.utils.DataCleanManager;
import zh.com.jyu.R;
import zh.com.jyu.business.activity.GroupListActivity;
import zh.com.jyu.business.activity.PlanActivity;

/**
 * author : dayezi
 * data :2019/6/18
 * description:
 */
@ToolbarTitle(title = "我的")
public class MineFragment extends BaseFragment {

    @BindView(R.id.clear_tv)
    TextView clearTv;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    MaterialDialog dialog;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void init() {
        ToolBarInject.inject(this, toolbar);
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

}
