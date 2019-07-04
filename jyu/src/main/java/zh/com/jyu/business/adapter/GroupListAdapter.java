package zh.com.jyu.business.adapter;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.GroupBean;

/**
 * author : dayezi
 * data :2019/6/20
 * description:
 */
public class GroupListAdapter extends MyBaseAdapter<GroupBean> {

    @Inject
    public GroupListAdapter() {
        super(R.layout.item_group, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, GroupBean item) {
        helper.setText(R.id.name_tv, item.getCrewName());
        helper.setText(R.id.is_open_tv, item.isEnabled() ? "是" : "否");
        helper.setText(R.id.member_tv, item.getMembers());
        helper.setText(R.id.produce_tv, item.getCreationTime());
        helper.setText(R.id.memo_tv, item.getRemark());
    }
}
