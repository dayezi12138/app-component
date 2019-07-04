package zh.com.jyu.business.activity;

import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.OnClick;
import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.GroupBean;
import zh.com.jyu.business.adapter.GroupListAdapter;
import zh.com.jyu.constans.SpKeyConstant;
import zh.com.jyu.mvp.presenter.activity.GroupListPresenter;

/**
 * author : dayezi
 * data :2019/6/20
 * description:
 */
@Route(path = GroupListActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true)
@ToolbarTitle(title = "班组")
public class GroupListActivity extends BaseListActivity<GroupBean> {

    public static final String AROUTER_PATH = "/plan/GroupListActivity/";
    public static final String GROUP_BEAN_KEY = "GroupBean";
    @Inject
    GroupListAdapter groupListAdapter;

    @Inject
    GroupListPresenter presenter;

    private MaterialDialog dialog;


    @Override
    public MyBaseAdapter myBaseAdapter() {
        return groupListAdapter;
    }

    @Override
    public void onItemClick(int position) {
        ARouter.getInstance().build(EditGroupActivity.AROUTER_PATH)
                .withSerializable(GROUP_BEAN_KEY, groupListAdapter.getData().get(position)).navigation();
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_group_list;
    }

    @Override
    public boolean isOnResume() {
        return true;
    }


    @Override
    public void onItemLongClick(int position) {
        this.position = position;
        if (!dialog.isShowing())
            dialog.show();
    }

    private int position = -1;

    @Override
    public void init() {
        super.init();
        dialog = new MaterialDialog.Builder(this).title("提示").content("是否删除该条数据?").positiveText("确定").neutralText("取消")
                .onPositive((dialog, which) -> {
                    if (position != -1)
                        presenter.delete(position, groupListAdapter);
                }).build();
    }


    @Override
    public Map<String, Object> params() {
        Map<String, Object> param = new HashMap<>();
        param.put("UserID", SPUtils.getInstance().getInt(SpKeyConstant.LOGIN_UID_KEY));
        return param;
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @OnClick(R.id.add_report_btn)
    public void add() {
        ARouter.getInstance().build(AddGroupActivity.AROUTER_PATH).navigation();
    }
}
