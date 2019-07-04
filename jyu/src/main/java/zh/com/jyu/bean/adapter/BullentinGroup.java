package zh.com.jyu.bean.adapter;

import com.hgdendi.expandablerecycleradapter.BaseExpandableRecyclerViewAdapter;

import java.util.List;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
public class BullentinGroup implements BaseExpandableRecyclerViewAdapter.BaseGroupBean<BullentinChildBean> {

    private List<BullentinChildBean> mList;
    private String mName;

    public BullentinGroup(List<BullentinChildBean> mList, String mName) {
        this.mList = mList;
        this.mName = mName;
    }

    @Override
    public int getChildCount() {
        return mList.size();
    }


    @Override
    public boolean isExpandable() {
        return getChildCount() > 0;
    }
    public String getName() {
        return mName;
    }
    @Override
    public BullentinChildBean getChildAt(int index) {
        return mList.size() <= index ? null : mList.get(index);
    }
}
