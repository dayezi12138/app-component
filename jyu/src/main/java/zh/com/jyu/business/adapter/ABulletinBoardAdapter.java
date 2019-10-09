package zh.com.jyu.business.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.R;
import zh.com.jyu.bean.fragment.BulletinBoard;

/**
 * author : dayezi
 * data :2019/9/5
 * description:
 */
public class ABulletinBoardAdapter extends MyBaseAdapter<BulletinBoard.BulletinBoardChild> {
    @Inject
    public ABulletinBoardAdapter() {
        super(R.layout.item_a_bulletin_board, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, BulletinBoard.BulletinBoardChild item) {
        helper.setText(R.id.plan_no_tv, item.getPlanNO());
        helper.setText(R.id.good_name_tv, item.getGoodsName());
        helper.setText(R.id.real_num_tv, String.valueOf(item.getRealNumber()));
        helper.setText(R.id.report_num_tv, String.valueOf(item.getReportedCount()));
        helper.setText(R.id.report_time_tv, item.getReportTime());
        helper.setText(R.id.create_time_tv, item.getTimeOut());
        GradientDrawable myGrad = (GradientDrawable) helper.getView(R.id.ly).getBackground();
        if (item.getColor().equals("green")) {
            myGrad.setColor(Color.parseColor("#1CBB9A"));
        } else if (item.getColor().equals("gray")) {
            myGrad.setColor(Color.parseColor("#FFFFFF"));
        } else {
            myGrad.setColor(Color.parseColor("#FDF4CA"));
        }
    }
}
