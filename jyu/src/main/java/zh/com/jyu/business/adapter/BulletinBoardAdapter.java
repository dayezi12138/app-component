package zh.com.jyu.business.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hgdendi.expandablerecycleradapter.BaseExpandableRecyclerViewAdapter;

import java.util.List;

import zh.com.jyu.R;
import zh.com.jyu.bean.fragment.BulletinBoard;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
public class BulletinBoardAdapter extends BaseExpandableRecyclerViewAdapter<BulletinBoard, BulletinBoard.BulletinBoardChild, BulletinBoardAdapter.GroupVH, BulletinBoardAdapter.ChildVH> {

    private List<BulletinBoard> mList;

    public BulletinBoardAdapter(List<BulletinBoard> mList) {
        this.mList = mList;
    }

    public void setmList(List<BulletinBoard> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public BulletinBoard getGroupItem(int groupIndex) {
        return mList.get(groupIndex);
    }

    @Override
    public GroupVH onCreateGroupViewHolder(ViewGroup parent, int groupViewType) {
        return new GroupVH(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_parent, parent, false));
    }

    @Override
    public void onBindGroupViewHolder(GroupVH holder, BulletinBoard groupBean, boolean isExpand) {
        holder.tv.setText(groupBean.getKey());
        holder.countTv.setText(String.valueOf(groupBean.getChildCount()));
        holder.foldIv.setVisibility(View.VISIBLE);
        holder.foldIv.setBackgroundResource(isExpand ? R.drawable.ic_arrow_expanding : R.drawable.ic_arrow_folding);
    }

    @Override
    public ChildVH onCreateChildViewHolder(ViewGroup parent, int childViewType) {
        return new ChildVH(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_bulletin, parent, false));
    }

    @Override
    public void onBindChildViewHolder(ChildVH holder, BulletinBoard groupBean, BulletinBoard.BulletinBoardChild bullentinChildBean) {
        holder.orderNumTv.setText(bullentinChildBean.getTradeNOs());
        holder.goodsNameTv.setText(bullentinChildBean.getGoodsName());
        holder.realNumTv.setText(String.valueOf(bullentinChildBean.getRealNumber()));
        holder.reportNumTv.setText(String.valueOf(bullentinChildBean.getReportedCount()));
        holder.firstTimeTv.setText(bullentinChildBean.getReportTime());
        holder.timeOnTv.setText(bullentinChildBean.getTimeOut());
        if (bullentinChildBean.getColor().equals("green")) {
            holder.ly.setBackgroundColor(Color.parseColor("#6E8B3D"));
        } else if (bullentinChildBean.getColor().equals("gray")) {
            holder.ly.setBackgroundColor(Color.parseColor("#555555"));
        } else {
            holder.ly.setBackgroundColor(Color.parseColor("#EEB422"));
        }
    }

    static class GroupVH extends BaseExpandableRecyclerViewAdapter.BaseGroupViewHolder {
        ImageView foldIv;
        TextView tv;
        TextView countTv;

        GroupVH(View itemView) {
            super(itemView);
            foldIv = itemView.findViewById(R.id.imgage);
            tv = itemView.findViewById(R.id.text_tv);
            countTv = itemView.findViewById(R.id.count_tv);
        }

        @Override
        protected void onExpandStatusChanged(RecyclerView.Adapter relatedAdapter, boolean isExpanding) {
            foldIv.setBackgroundResource(isExpanding ? R.drawable.ic_arrow_expanding : R.drawable.ic_arrow_folding);
        }
    }

    static class ChildVH extends RecyclerView.ViewHolder {
        TextView orderNumTv, goodsNameTv, realNumTv, reportNumTv, firstTimeTv, timeOnTv;
        LinearLayout ly;

        ChildVH(View itemView) {
            super(itemView);
            orderNumTv = itemView.findViewById(R.id.order_num_tv);
            goodsNameTv = itemView.findViewById(R.id.goods_name_tv);
            realNumTv = itemView.findViewById(R.id.real_num_tv);
            reportNumTv = itemView.findViewById(R.id.report_num_tv);
            firstTimeTv = itemView.findViewById(R.id.first_time_tv);
            timeOnTv = itemView.findViewById(R.id.time_on_tv);
            ly = itemView.findViewById(R.id.card_v);
        }
    }
}
