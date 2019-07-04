package zh.com.jyu.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;

import zh.com.jyu.R;

/**
 * author : dayezi
 * data :2019/6/13
 * description:
 */
public class ItemDecorationHelper {


    public static DividerItemDecoration getDividerItemDecoration(Context context) {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.item_line));
        return dividerItemDecoration;
    }
}
