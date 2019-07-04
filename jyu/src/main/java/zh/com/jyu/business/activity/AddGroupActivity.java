package zh.com.jyu.business.activity;

import android.support.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
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
import zh.com.jyu.bean.activity.UserList;
import zh.com.jyu.business.adapter.AddGroupAdapter;
import zh.com.jyu.constans.SpKeyConstant;
import zh.com.jyu.mvp.presenter.activity.AddGroupPresenter;

/**
 * author : dayezi
 * data :2019/6/20
 * description:
 */
@Route(path = AddGroupActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true)
@ToolbarTitle(title = "添加班组")
public class AddGroupActivity extends BaseListActivity {
    public final static String AROUTER_PATH = "/plan/AddGroupActivity/";

    @Inject
    AddGroupPresenter presenter;

    @Inject
    AddGroupAdapter addGroupAdapter;

    @Override
    public MyBaseAdapter myBaseAdapter() {
        return addGroupAdapter;
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public Map<String, Object> params() {
        Map<String, Object> map = new HashMap<>();
        map.put("UserID", SPUtils.getInstance().getInt(SpKeyConstant.LOGIN_UID_KEY));
        return map;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_group_list;
    }

    @Override
    public boolean clickble() {
        return false;
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }


    @OnClick(R.id.add_report_btn)
    public void add() {
        Map<Integer, UserList.MembersBean> membersBeanHashMap = addGroupAdapter.getMembersBeanHashMap();
        if (membersBeanHashMap.isEmpty()) {
            showMsg("请选择人员");
            return;
        }
        String staffIDs;
        StringBuilder builder = new StringBuilder();
        for (Integer i : membersBeanHashMap.keySet()) {
            builder.append(membersBeanHashMap.get(i).getStaffID()).append(",");
        }
        staffIDs = builder.toString();
        presenter.submit(staffIDs.substring(0, staffIDs.lastIndexOf(",")));
    }
}
