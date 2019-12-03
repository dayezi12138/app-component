package com.zh.xfz.business.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hgdendi.expandablerecycleradapter.BaseExpandableRecyclerViewAdapter;
import com.zh.xfz.R;
import com.zh.xfz.bean.adapter.ChildBean;
import com.zh.xfz.bean.adapter.GroupBean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * author : dayezi
 * data :2019/10/30
 * description:
 */
public class HelpItemAdapter extends BaseExpandableRecyclerViewAdapter<GroupBean, ChildBean, HelpItemAdapter.GroupVH, HelpItemAdapter.ChildVH> {

    private List<GroupBean> data = new ArrayList<>();

    public void setData(List<GroupBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Inject
    public HelpItemAdapter() {
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public GroupBean getGroupItem(int groupIndex) {
        return data.get(groupIndex);
    }

    @Override
    public GroupVH onCreateGroupViewHolder(ViewGroup parent, int groupViewType) {
        return new GroupVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_help_item_group, null));
    }

    @Override
    public void onBindGroupViewHolder(GroupVH holder, GroupBean groupBean, boolean isExpand) {
        holder.tv.setText(groupBean.getGroupName());
    }

    @Override
    public ChildVH onCreateChildViewHolder(ViewGroup parent, int childViewType) {
        return new ChildVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_help_item_child, null));
    }

    @Override
    public void onBindChildViewHolder(ChildVH holder, GroupBean groupBean, ChildBean childBean) {
        holder.tv.setText(childBean.getName());
    }

    static class GroupVH extends BaseExpandableRecyclerViewAdapter.BaseGroupViewHolder {
        private TextView tv;

        public GroupVH(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.group_text_tv);
        }

        @Override
        protected void onExpandStatusChanged(RecyclerView.Adapter relatedAdapter, boolean isExpanding) {

        }
    }

    static class ChildVH extends RecyclerView.ViewHolder {
        private TextView tv;

        public ChildVH(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.group_text_tv);
        }
    }
}
