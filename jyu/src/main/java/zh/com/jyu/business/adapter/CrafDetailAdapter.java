package zh.com.jyu.business.adapter;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.CraftDetailBean;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
public class CrafDetailAdapter extends MyBaseAdapter<CraftDetailBean.CmdBean> {

    @Inject
    public CrafDetailAdapter() {
        super(R.layout.item_text, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, CraftDetailBean.CmdBean item) {
        helper.setText(R.id.text_tv, item.getContent());
    }
}
