package zh.com.jyu.business.activity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.business.adapter.UserListAdapter;
import zh.com.jyu.mvp.presenter.activity.UserListPresenter;

/**
 * author : dayezi
 * data :2019/6/19
 * description:
 */
@Route(path = UserListActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true)
@ToolbarTitle(title = "班组")
public class UserListActivity extends BaseListActivity {

    public static final String USER_LIST_KEY = "crewIDs";
    public static final String AROUTER_PATH = "/plan/UserListActivity/";
    @Inject
    UserListPresenter presenter;

    @Inject
    UserListAdapter userListAdapter;

    @Autowired(name = USER_LIST_KEY)
    String crewIDs;


    @Override
    public MyBaseAdapter myBaseAdapter() {
        return userListAdapter;
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public Map<String, Object> params() {
        Map<String, Object> map = new HashMap<>();
        map.put("crewIDs", crewIDs);
        return map;
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }
}
