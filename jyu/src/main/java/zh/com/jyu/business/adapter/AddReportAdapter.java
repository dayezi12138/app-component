package zh.com.jyu.business.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.UserListBean;

/**
 * author : dayezi
 * data :2019/6/19
 * description:
 */
public class AddReportAdapter extends MyBaseAdapter<UserListBean> {

    @Inject
    public AddReportAdapter() {
        super(R.layout.item_add_report, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, UserListBean item) {
        helper.setText(R.id.name_tv,item.getName());
        EditText countTv = helper.getView(R.id.count_tv);
        if (countTv.getTag() != null && countTv.getTag() instanceof TextWatcher) {
            countTv.removeTextChangedListener((TextWatcher) countTv.getTag());
        }
        TextWatcher countW = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                item.setCount_(s.toString());

            }
        };
        countTv.addTextChangedListener(countW);
        countTv.setTag(countW);

        EditText memoTv = helper.getView(R.id.memo_tv);
        if (memoTv.getTag() != null && memoTv.getTag() instanceof TextWatcher) {
            memoTv.removeTextChangedListener((TextWatcher) memoTv.getTag());
        }
        TextWatcher memoW = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                item.setMemo_(s.toString());
            }
        };
        memoTv.addTextChangedListener(memoW);
        memoTv.setTag(memoW);
    }
}
