package zh.com.jyu.business.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.SPUtils;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.api.ToolBarInject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BasePresenter;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.GroupBean;
import zh.com.jyu.bean.activity.UserList;
import zh.com.jyu.business.adapter.AddGroupAdapter;
import zh.com.jyu.constans.SpKeyConstant;
import zh.com.jyu.mvp.contract.activity.EditGroupContract;
import zh.com.jyu.mvp.presenter.activity.EditGroupPresenter;
import zh.com.jyu.utils.MyTextUtils;

/**
 * author : dayezi
 * data :2019/6/20
 * description:
 */
@Route(path = EditGroupActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true)
@ToolbarTitle(title = "编辑班组")
public class EditGroupActivity extends BaseActivity implements EditGroupContract.EditGroupUI {

    public static final String AROUTER_PATH = "/plan/EditGroupActivity/";

    @Inject
    EditGroupPresenter presenter;

    @Inject
    AddGroupAdapter addGroupAdapter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.name_tv)
    EditText nameEt;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.memo_tv)
    EditText memoTv;

    @Autowired(name = GroupListActivity.GROUP_BEAN_KEY)
    GroupBean bean;

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_edit_group;
    }

    @Override
    public void init() {
        ToolBarInject.inject(this, toolbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));  //线性布局
        recyclerView.setAdapter(addGroupAdapter);
        Map<String, Object> map = new HashMap<>();
        map.put("UserID", SPUtils.getInstance().getInt(SpKeyConstant.LOGIN_UID_KEY));
        presenter.requestData(map);
        if (bean != null) {
            nameEt.setText(bean.getCrewName());
            memoTv.setText(TextUtils.isEmpty(bean.getRemark()) ? "" : bean.getRemark());
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("crewIDs", bean.getCrewID());
            presenter.requestUser(userMap);
        }
    }

    @Override
    public boolean addToolbar() {
        return true;
    }

    private List<UserList.MembersBean> membersBeans;

    @Override
    public void success(List<UserList.MembersBean> data) {
        this.membersBeans = data;
    }

    @Override
    public void successUser(List<UserList> data) {
        if (!data.isEmpty()) {
            List<UserList.MembersBean> membersBeanList = data.get(0).getMembers();
            for (UserList.MembersBean membersBean : membersBeanList) {
                addGroupAdapter.getMembersBeanHashMap().put(membersBean.getStaffID(), membersBean);
                if (membersBeans == null || membersBeans.isEmpty()) continue;
                for (UserList.MembersBean membersBean1 : membersBeans) {
                    if (membersBean1.getStaffID() == membersBean.getStaffID()) {
                        membersBean1.setVisible(true);
                    }
                }
            }
        }
        addGroupAdapter.setNewData(membersBeans);
    }

    private boolean valid() {
        return MyTextUtils.isEmpty(nameEt.getText().toString());
    }

    @OnClick(R.id.submit_btn)
    public void submit() {
        Map<Integer, UserList.MembersBean> membersBeanHashMap = addGroupAdapter.getMembersBeanHashMap();
        if (membersBeanHashMap.isEmpty()) {
            showMsg("请添加人员");
        }
        String StaffIDs;
        StringBuilder builder = new StringBuilder();
        for (Integer i : membersBeanHashMap.keySet()) {
            builder.append(membersBeanHashMap.get(i).getStaffID()).append(",");
        }
        StaffIDs = builder.toString();
        if (valid()) {
            showMsg("班组名不能为空");
            return;
        }
        if (bean == null) return;
        if (TextUtils.isEmpty(StaffIDs)) {
            showMsg("请添加人员");
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("CrewID", bean.getCrewID());
        map.put("CrewName", nameEt.getText().toString());
        map.put("Remark", TextUtils.isEmpty(memoTv.getText().toString()) ? "" : memoTv.getText().toString());
        map.put("StaffIDs", StaffIDs.substring(0, StaffIDs.lastIndexOf(",")));
        presenter.editCrew(map);
    }
}
