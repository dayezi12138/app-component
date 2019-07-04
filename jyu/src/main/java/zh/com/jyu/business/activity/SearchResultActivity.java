package zh.com.jyu.business.activity;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.zh.api.DefaultOptionListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MessageEvent;
import zh.com.jyu.R;
import zh.com.jyu.db.DaoSession;
import zh.com.jyu.db.SearchRecord;
import zh.com.jyu.db.SearchRecordDao;
import zh.com.jyu.views.ClearEditTextView;

/**
 * author : dayezi
 * data :2019/6/21
 * description:
 */
@Route(path = SearchResultActivity.AROUTER_PATH)
//@ToolbarNavigation(visibleNavigation = true)
//@ToolbarTitle(title = "搜索")
public class SearchResultActivity extends BaseActivity {

    public static final String AROUTER_PATH = "/plan/SearchResultActivity/";
    public static final String SEARCH_VALUE = "SEARCH";
    public static final String SEARCH_KEY_CODE = "SEARCH_KEY_CODE";
    public static final int SEARCH_EVENT_CODE = 100000;
    public static final String HINT_KEY = "HINT_KEY";

    @BindView(R.id.et_search)
    ClearEditTextView clearEditTextView;

    @BindView(R.id.id_flowlayout)
    TagFlowLayout flowLayout;
    private SearchTagAdapter searchTagAdapter;
    private List<SearchRecord> datas = new ArrayList<>();

    @Inject
    DaoSession daoSession;

    @Inject
    DefaultOptionListener defaultOptionListener;

    @Autowired(name = HINT_KEY)
    String hintText;

    @Autowired(name = SEARCH_KEY_CODE)
    public int eventCode;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_search_result;
    }

    @Override
    public void init() {
        setStatusBarColor(findViewById(R.id.search_ly), getResources().getColor(defaultOptionListener.defaultToolbarOption().getToolbarBackgroundColorId()));
        datas.clear();
        List<SearchRecord> list = daoSession.queryBuilder(SearchRecord.class).list();
        datas.addAll(list);
        searchTagAdapter = new SearchTagAdapter(datas);
        flowLayout.setAdapter(searchTagAdapter);
        flowLayout.setOnTagClickListener((view, position, parent) -> {
            int code = SEARCH_EVENT_CODE;
            if (eventCode != 0) code = eventCode;
            MessageEvent event = new MessageEvent(code, datas.get(position).getSearchValue());
            EventBus.getDefault().post(event);
            finish();
            return true;
        });
        if (!TextUtils.isEmpty(hintText)) clearEditTextView.setHint(hintText);
    }

    @OnClick(R.id.cancel_tv)
    public void cancel() {
        boolean exist = daoSession.queryBuilder(SearchRecord.class).where(SearchRecordDao.Properties.SearchValue.eq(clearEditTextView.getText().toString())).list().isEmpty();
        if (exist && !TextUtils.isEmpty(clearEditTextView.getText().toString())) {
            SearchRecord record = new SearchRecord();
            record.setSearchValue(clearEditTextView.getText().toString());
            daoSession.getSearchRecordDao().save(record);
        }
        int code = SEARCH_EVENT_CODE;
        if (eventCode != 0) code = eventCode;
        MessageEvent event = new MessageEvent(code, clearEditTextView.getText().toString());
        EventBus.getDefault().post(event);
        finish();
    }

    private class SearchTagAdapter extends TagAdapter<SearchRecord> {

        public SearchTagAdapter(List<SearchRecord> datas) {
            super(datas);
        }

        @Override
        public View getView(FlowLayout parent, int position, SearchRecord s) {
            TextView tv = (TextView) LayoutInflater.from(SearchResultActivity.this).inflate(R.layout.flowlayout_tv_, parent, false);
            tv.setText(s.getSearchValue());
            return tv;
        }
    }

    @OnClick(R.id.delete_tv)
    public void delete() {
        daoSession.deleteAll(SearchRecord.class);
        datas.clear();
        searchTagAdapter.notifyDataChanged();
        showMsg("清除成功");
    }

    @OnClick(R.id.back_iv)
    public void back() {
        finish();
    }
}
