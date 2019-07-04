package zh.com.jyu.business.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zh.api.ToolBarInject;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.R;
import zh.com.jyu.mvp.contract.activity.BaseListContract;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
public abstract class BaseListActivity<T> extends BaseActivity implements BaseListContract.BaseListUI<T> {
    private MyBaseAdapter<T> adapter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private BaseListContract.BaseListPreseneter preseneter;

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_good_list;
    }

    @Override
    public void init() {
        if (myBaseAdapter() == null || !(getPresenter() instanceof BaseListContract.BaseListPreseneter))
            return;
        ToolBarInject.inject(this, toolbar);
        adapter = myBaseAdapter();
        preseneter = (BaseListContract.BaseListPreseneter) getPresenter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));  //线性布局
        recyclerView.setAdapter(adapter);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        if (clickble())
            adapter.setOnItemClickListener((adapter, view, position) -> BaseListActivity.this.onItemClick(position));
        adapter.setOnItemLongClickListener((adapter, view, position) -> {
            BaseListActivity.this.onItemLongClick(position);
            return true;
        });
        if (!isOnResume()) preseneter.requestData(params());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isOnResume())
            preseneter.requestData(params());
    }

    public boolean clickble() {
        return true;
    }

    @Override
    public boolean addToolbar() {
        return true;
    }

    @Override
    public void success(List<T> data) {
        adapter.setNewData(data);
    }

    public abstract MyBaseAdapter myBaseAdapter();

    public abstract void onItemClick(int position);

    public abstract Map<String, Object> params();

    public boolean isOnResume() {
        return false;
    }

    public void onItemLongClick(int position) {

    }
}
