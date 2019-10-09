package zh.com.jyu.business.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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
                        .inflate(R.layout.item_bulletin, parent, false), parent.getContext());
    }

    @Override
    public void onBindChildViewHolder(ChildVH holder, BulletinBoard groupBean, BulletinBoard.BulletinBoardChild bullentinChildBean) {
        holder.textViewList.get(0).setText(bullentinChildBean.getPlanNO());
        holder.textViewList.get(1).setText(bullentinChildBean.getGoodsName());
        holder.textViewList.get(2).setText(String.valueOf(bullentinChildBean.getRealNumber()));
        holder.textViewList.get(3).setText(String.valueOf(bullentinChildBean.getReportedCount()));
        holder.textViewList.get(4).setText(bullentinChildBean.getReportTime());
        holder.textViewList.get(5).setText(bullentinChildBean.getTimeOut());
        GradientDrawable myGrad = (GradientDrawable) holder.ly.getBackground();
        if (bullentinChildBean.getColor().equals("green")) {
            myGrad.setColor(Color.parseColor("#6E8B3D"));
            holder.setWhiteTextColor();
        } else if (bullentinChildBean.getColor().equals("gray")) {
            myGrad.setColor(Color.parseColor("#FFFFFF"));
            holder.setBlackTextColor();
        } else {
            myGrad.setColor(Color.parseColor("#EEB422"));
            holder.setWhiteTextColor();
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
        //        TextView orderNumTv, goodsNameTv, realNumTv, reportNumTv, firstTimeTv, timeOnTv;
        LinearLayout ly;
        //        private List<FormLineLayout> lineLayouts = new ArrayList<>();
        private List<TextView> textViewList = new ArrayList<>();
        private List<TextView> titleList = new ArrayList<>();
        private Context context;

        ChildVH(View itemView, Context context) {
            super(itemView);
            this.context = context;
            ly = itemView.findViewById(R.id.card_v);
//            lineLayouts.add(itemView.findViewById(R.id.order_num_ly));
//            lineLayouts.add(itemView.findViewById(R.id.goods_name_ly));
//            lineLayouts.add(itemView.findViewById(R.id.real_num_ly));
//            lineLayouts.add(itemView.findViewById(R.id.report_num_ly));
//            lineLayouts.add(itemView.findViewById(R.id.first_time_ly));
//            lineLayouts.add(itemView.findViewById(R.id.time_on_ly));
            textViewList.add(itemView.findViewById(R.id.order_num_tv));
            textViewList.add(itemView.findViewById(R.id.goods_name_tv));
            textViewList.add(itemView.findViewById(R.id.real_num_tv));
            textViewList.add(itemView.findViewById(R.id.report_num_tv));
            textViewList.add(itemView.findViewById(R.id.first_time_tv));
            textViewList.add(itemView.findViewById(R.id.time_on_tv));
            titleList.add(itemView.findViewById(R.id.real_num_title_tv));
            titleList.add(itemView.findViewById(R.id.report_num_title_tv));
            titleList.add(itemView.findViewById(R.id.first_time_title_tv));
            titleList.add(itemView.findViewById(R.id.time_on_title_tv));
        }

        public void setBlackTextColor() {
//            for (FormLineLayout ly : lineLayouts) {
//                ly.getLeftTextView().setTextColor(context.getResources().getColor(R.color.text1));
//            }
            for (TextView textView : titleList) {
                textView.setTextColor(context.getResources().getColor(R.color.text1));
            }
            for (TextView textView : textViewList) {
                textView.setTextColor(context.getResources().getColor(R.color.text1));
            }
        }

        public void setWhiteTextColor() {
//            for (FormLineLayout ly : lineLayouts) {
//                ly.getLeftTextView().setTextColor(context.getResources().getColor(R.color.white));
//            }
            for (TextView textView : titleList) {
                textView.setTextColor(context.getResources().getColor(R.color.white));
            }
            for (TextView textView : textViewList) {
                textView.setTextColor(context.getResources().getColor(R.color.white));
            }
        }
    }

}
