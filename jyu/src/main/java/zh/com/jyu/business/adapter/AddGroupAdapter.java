package zh.com.jyu.business.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.UserList;

/**
 * author : dayezi
 * data :2019/6/20
 * description:
 */
public class AddGroupAdapter extends MyBaseAdapter<UserList.MembersBean> {
    private Map<Integer, UserList.MembersBean> membersBeanHashMap = new HashMap<>();

    @Inject
    public AddGroupAdapter() {
        super(R.layout.item_user, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, UserList.MembersBean item) {
        helper.setText(R.id.text_tv, item.getStaffName());
        ImageView imageView = helper.getView(R.id.image);
        if (item.isVisible()) imageView.setVisibility(View.VISIBLE);
        else imageView.setVisibility(View.GONE);
        helper.getView(R.id.ly).setOnClickListener(v -> {
            int id = item.getStaffID();
            if (getMembersBeanHashMap().containsKey(id)) {
                getMembersBeanHashMap().remove(id);
                imageView.setVisibility(View.GONE);
            } else {
                getMembersBeanHashMap().put(id, item);
                imageView.setVisibility(View.VISIBLE);
            }
        });
    }

    public Map<Integer, UserList.MembersBean> getMembersBeanHashMap() {
        return membersBeanHashMap;
    }

    public void setMembersBeanHashMap(Map<Integer, UserList.MembersBean> membersBeanHashMap) {
        this.membersBeanHashMap = membersBeanHashMap;
    }
}
