package zh.com.jyu.business.adapter;

import android.view.Gravity;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Arrays;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.R;

/**
 * author : dayezi
 * data :2019/6/18
 * description:
 */
public class StringAdapter extends MyBaseAdapter<String> {


    @Inject
    public StringAdapter(String[] titles) {
        super(R.layout.item_text, Arrays.asList(titles));
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView tv = helper.getView(R.id.text_tv);
        tv.setText(item);
        tv.setGravity(Gravity.CENTER);
    }
}
