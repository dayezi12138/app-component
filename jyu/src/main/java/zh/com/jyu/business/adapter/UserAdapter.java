package zh.com.jyu.business.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.UserListBean;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
public class UserAdapter extends MyBaseAdapter<UserListBean> {

    private Map<Integer, UserListBean> userListBeanMap = new HashMap<>();

    @Inject
    public UserAdapter() {
        super(R.layout.item_user, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, UserListBean item) {
        helper.setText(R.id.text_tv, item.getName());
        ImageView imageView = helper.getView(R.id.image);
//        helper.getView(R.id.ly).setOnClickListener(v -> {
//            int id = item.getID();
//            if (getUserListBeanMap().containsKey(id)) {
//                getUserListBeanMap().remove(id);
//                imageView.setVisibility(View.GONE);
//            } else {
//                getUserListBeanMap().put(id, item);
//                imageView.setVisibility(View.VISIBLE);
//            }
//        });
    }

    public Map<Integer, UserListBean> getUserListBeanMap() {
        return userListBeanMap;
    }

    public void setUserListBeanMap(Map<Integer, UserListBean> userListBeanMap) {
        this.userListBeanMap = userListBeanMap;
    }
}
