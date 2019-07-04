package zh.com.jyu.business.adapter;

import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.UserList;

/**
 * author : dayezi
 * data :2019/6/19
 * description:
 */
public class UserListAdapter extends MyBaseAdapter<UserList> {

    @Inject
    public UserListAdapter() {
        super(R.layout.item_user_list, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, UserList item) {
        helper.setText(R.id.title_tv, item.getCrewName());
        LinearLayout ly = helper.getView(R.id.ly);
        if (item.getMembers() != null && !item.getCrewName().isEmpty()) {
            for (int i = 0, j = item.getMembers().size(); i < j; i++) {
                UserList.MembersBean bean = item.getMembers().get(i);
                TextView textView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.text_view, null, false);
                textView.setText(bean.getStaffName());
                ly.addView(textView, i);
            }
        } else {

        }
    }
}
