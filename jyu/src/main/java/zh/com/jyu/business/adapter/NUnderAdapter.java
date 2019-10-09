package zh.com.jyu.business.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseViewHolder;

import javax.inject.Inject;

import zh.com.jyu.R;
import zh.com.jyu.bean.activity.CraftBean;
import zh.com.jyu.business.activity.CraftDetailActivity;
import zh.com.jyu.views.pine.entity.ExpandGroupItemEntity;
import zh.com.jyu.views.pine.entity.RecyclerExpandBaseAdapter;

/**
 * author : dayezi
 * data :2019/9/4
 * description:
 */
public class NUnderAdapter extends RecyclerExpandBaseAdapter<String, CraftBean> {
    @Inject
    public NUnderAdapter() {
        super();
    }

    /**
     * 悬浮标题栏被点击的时候，展开收起切换功能
     */
    public void switchExpand(int adapterPosition) {
        int groupIndex = mIndexMap.get(adapterPosition).getGroupIndex();
        ExpandGroupItemEntity entity = mDataList.get(groupIndex);
        entity.setExpand(!entity.isExpand());
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM_TIME) {
            TitleItemHolder holder = new TitleItemHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_n_under_head, parent, false));
            holder.title.setOnClickListener(v -> {
                ExpandGroupItemEntity entity = (ExpandGroupItemEntity) holder.itemView.getTag();
                for (ExpandGroupItemEntity entity1 : mDataList) {
                    if (entity1.getParent().equals(entity.getParent())) {
                        entity1.setExpand(!entity1.isExpand());
                    } else {
                        entity1.setExpand(false);
                    }
                }
                notifyDataSetChanged();
            });
            return holder;
        } else {
            return new SubItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_n_under_content, parent, false));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreatePinnedViewHolder(ViewGroup parent, int viewType) {
        TitleItemHolder holder = (TitleItemHolder) super.onCreatePinnedViewHolder(parent, viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_ITEM_TIME) {
            int groupIndex = mIndexMap.get(position).getGroupIndex();
            TitleItemHolder itemHolder = (TitleItemHolder) holder;
            itemHolder.itemView.setTag(mDataList.get(groupIndex));
            itemHolder.title.setText("生产单号: "+String.valueOf(mDataList.get(groupIndex).getParent()));
        } else {
            SubItemHolder subHolder = (SubItemHolder) holder;
            int groupIndex = mIndexMap.get(position).getGroupIndex();
            int childIndex = mIndexMap.get(position).getChildIndex();
            CraftBean subItem = mDataList.get(groupIndex).getChildList().get(childIndex);
            subHolder.itemView.setTag(subItem);
            subHolder.goodsNameTv.setText(subItem.getGoodsName());
            subHolder.lengthTv.setText(String.valueOf(subItem.getLength()) + "米");
            subHolder.nameTv.setText(subItem.getCraftsName());
            subHolder.statusTv.setText(subItem.getCraftsStatusStr());
            subHolder.packCountTv.setText(String.valueOf(subItem.getNumber1()));
            subHolder.realNumTv.setText(String.valueOf(subItem.getReportedCount()));
            subHolder.createTimeTv.setText(subItem.getRegTime());
            subHolder.ly.setOnClickListener(v -> ARouter.getInstance().build(CraftDetailActivity.AROUTER_PATH)
                    .withInt(CraftDetailActivity.CRAFT_ID_KEY, subItem.getCraftsReceiptID())
                    .navigation());

        }
    }

    @Override
    public void onBindPinnedViewHolder(BaseViewHolder holder, int position) {
        super.onBindPinnedViewHolder(holder, position);
        TitleItemHolder itemHolder = (TitleItemHolder) holder;
    }

    static class TitleItemHolder extends BaseViewHolder {

        TextView title;

        TitleItemHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }

    static class SubItemHolder extends BaseViewHolder {

        TextView goodsNameTv;
        TextView lengthTv;
        TextView nameTv;
        TextView statusTv;
        TextView realNumTv;
        TextView createTimeTv;
        TextView packCountTv;
        LinearLayout ly;

        SubItemHolder(View itemView) {
            super(itemView);
            goodsNameTv = itemView.findViewById(R.id.goods_name_tv);
            lengthTv = itemView.findViewById(R.id.length_tv);
            nameTv = itemView.findViewById(R.id.name_tv);
            statusTv = itemView.findViewById(R.id.status_tv);
            realNumTv = itemView.findViewById(R.id.real_num_tv);
            createTimeTv = itemView.findViewById(R.id.create_time_tv);
            packCountTv = itemView.findViewById(R.id.pack_count_tv);
            ly = itemView.findViewById(R.id.ly);

        }
    }

    @Override
    protected void convert(BaseViewHolder helper, ExpandGroupItemEntity<String, CraftBean> item) {

    }
}
