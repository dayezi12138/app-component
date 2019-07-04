package zh.com.jyu.business.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.api.ToolBarInject;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MessageEvent;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.UserListBean;
import zh.com.jyu.business.adapter.UserAdapter;

/**
 * author : dayezi
 * data :2019/6/19
 * description:
 */
@Route(path = SelectUserActivity.AROUTER_PATH)
@ToolbarNavigation(visibleNavigation = true)
@ToolbarTitle(title = "选择班组")
@ToolbarLeft(menuId = R.menu.select_user_tool_bar)
public class SelectUserActivity extends BaseActivity {

    public static final String AROUTER_PATH = "/plan/UserListActivity/";
    public static final String USER_LIST_BEAN_KEY = "USER_LIST_Key";
    public static final String VISIBLE = "VISIBLE";

    @Autowired(name = USER_LIST_BEAN_KEY)
    String userListJson;

    @Autowired(name = VISIBLE)
    boolean isVisible;

    @Inject
    UserAdapter userAdapter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_good_list;
    }

    @Override
    public void init() {
        Type type = new TypeToken<List<UserListBean>>() {
        }.getType();
        List<UserListBean> userListBeans = new Gson().fromJson(userListJson, type);
        userAdapter.setNewData(userListBeans);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));  //线性布局
        recyclerView.setAdapter(userAdapter);
        ToolBarInject.inject(this, toolbar);
        userAdapter.setOnItemClickListener((adapter, view, position) -> {
            int id = userAdapter.getData().get(position).getID();
            ImageView imageView = view.findViewById(R.id.image);
            if (userAdapter.getUserListBeanMap().containsKey(id)) {
                userAdapter.getUserListBeanMap().remove(id);
                imageView.setVisibility(View.GONE);
            } else {
                userAdapter.getUserListBeanMap().put(id, userAdapter.getData().get(position));
                imageView.setVisibility(View.VISIBLE);
            }
        });
        if (isVisible) {
            toolbar.getMenu().clear();
        }
    }

    @OnMenuOnclick
    public void menuOnclick(MenuItem menuItem) {
        List<UserListBean> listBeans = new ArrayList<>();
        for (Integer integer : userAdapter.getUserListBeanMap().keySet()) {
            listBeans.add(userAdapter.getUserListBeanMap().get(integer));
        }
        if (listBeans.isEmpty()) {
            showMsg("请选择班组");
            return;
        }
        MessageEvent event = new MessageEvent(AddReportActivity.CODE, new Gson().toJson(listBeans));
        EventBus.getDefault().post(event);
        finish();
    }

    @Override
    public boolean addToolbar() {
        return true;
    }
}
