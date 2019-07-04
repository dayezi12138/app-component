package zh.com.jyu.bean.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeItemGroup;

import java.util.List;

import zh.com.jyu.R;
import zh.com.jyu.bean.fragment.BulletinBoard;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
public class BulletinParent extends TreeItemGroup<BulletinBoard> {
    @Nullable
    @Override
    protected List<TreeItem> initChildList(BulletinBoard data) {
        return ItemHelperFactory.createItems(data.getValue(), this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_parent;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder) {
        viewHolder.setText(R.id.text_tv, data.getKey());
    }

    @Override
    public void onClick(ViewHolder viewHolder) {
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(viewHolder.getView(R.id.imgage), "rotation", 360f);
//        objectAnimator.setDuration(2000);
//        objectAnimator.start();
    }
}
