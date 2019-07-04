package zh.com.jyu.bean.adapter;

import android.support.annotation.NonNull;

import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;

import zh.com.jyu.R;
import zh.com.jyu.bean.fragment.BulletinBoard;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
public class BulletinChild extends TreeItem<BulletinBoard.BulletinBoardChild> {

    @Override
    public int getLayoutId() {
        return R.layout.item_text;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder) {
        viewHolder.setText(R.id.text_tv, data.getColor());
    }
}
