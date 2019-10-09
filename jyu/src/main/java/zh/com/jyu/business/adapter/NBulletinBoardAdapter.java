package zh.com.jyu.business.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hgdendi.expandablerecycleradapter.BaseExpandableRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import zh.com.jyu.R;
import zh.com.jyu.bean.fragment.NBulletinBoard;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
public class NBulletinBoardAdapter extends BaseExpandableRecyclerViewAdapter<NBulletinBoard, NBulletinBoard.ValueBean, NBulletinBoardAdapter.GroupVH, NBulletinBoardAdapter.ChildVH> {

    private List<NBulletinBoard> mList = new ArrayList<>();

    @Inject
    public NBulletinBoardAdapter() {
    }

    public void setmList(List<NBulletinBoard> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public List<NBulletinBoard> getmList() {
        return mList;
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public NBulletinBoard getGroupItem(int groupIndex) {
        return mList.get(groupIndex);
    }

    @Override
    public GroupVH onCreateGroupViewHolder(ViewGroup parent, int groupViewType) {
        return new GroupVH(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_parent, parent, false));
    }

    @Override
    public void onBindGroupViewHolder(GroupVH holder, NBulletinBoard groupBean, boolean isExpand) {
        holder.tv.setText(groupBean.getCraftsName());
        holder.countTv.setText(String.valueOf(groupBean.getChildCount()));
        holder.foldIv.setVisibility(View.VISIBLE);
        holder.foldIv.setBackgroundResource(isExpand ? R.drawable.ic_arrow_expanding : R.drawable.ic_arrow_folding);
    }

    @Override
    public ChildVH onCreateChildViewHolder(ViewGroup parent, int childViewType) {
        return new ChildVH(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_n_bulletin, parent, false), parent.getContext());
    }


    @Override
    public void onBindChildViewHolder(ChildVH holder, NBulletinBoard groupBean, NBulletinBoard.ValueBean valueBean) {
        holder.planNoTv.setText("生产单编号:" + valueBean.getPlanNO());
        if (valueBean.isVisible()) holder.planNoTv.setVisibility(View.VISIBLE);
        else holder.planNoTv.setVisibility(View.GONE);
        holder.goodsNameTv.setText(valueBean.getGoodsName());
        holder.unCompleteNumTv.setText(String.valueOf(valueBean.getRealNumber()));
        holder.reportNumTv.setText(String.valueOf(valueBean.getReportedCount()));
    }


    static class ChildVH extends RecyclerView.ViewHolder {
        TextView goodsNameTv, unCompleteNumTv, reportNumTv, planNoTv;
        LinearLayout ly;

        public ChildVH(@NonNull View itemView, Context context) {
            super(itemView);
            planNoTv = itemView.findViewById(R.id.plan_no_tv);
            goodsNameTv = itemView.findViewById(R.id.goods_name_tv);
            unCompleteNumTv = itemView.findViewById(R.id.un_complete_num_tv);
            reportNumTv = itemView.findViewById(R.id.report_num_tv);
            ly = itemView.findViewById(R.id.ly);
        }
    }

    static class GroupVH extends BaseExpandableRecyclerViewAdapter.BaseGroupViewHolder {
        ImageView foldIv;
        TextView tv;
        TextView countTv;

        public GroupVH(View itemView) {
            super(itemView);
            foldIv = itemView.findViewById(R.id.imgage);
            tv = itemView.findViewById(R.id.text_tv);
            countTv = itemView.findViewById(R.id.count_tv);
        }

        @Override
        protected void onExpandStatusChanged(RecyclerView.Adapter relatedAdapter, boolean isExpanding) {

        }
    }
}
