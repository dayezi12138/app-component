package zh.com.jyu.business.fragment.leader;

import java.util.Map;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.base.MyBaseAdapter;
import zh.com.jyu.bean.activity.CraftBean;
import zh.com.jyu.business.fragment.StatusFragment;

/**
 * author : dayezi
 * data :2019/6/12
 * description:
 */
public class StartingFragment extends StatusFragment<CraftBean> {
    @Override
    public MyBaseAdapter adapter() {
        return null;
    }

    @Override
    public Map<String, Object> params() {
        return null;
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

//    PlanBAdapter planBAdapter;
//    @BindView(R.id.recycler)
//    RecyclerView recyclerView;
//
//    @BindView(R.id.search_edit_frame)
//    protected ClearEditTextView clearEditTextView;
//
//    @BindView(R.id.swipeRefreshLayout)
//    SwipeRefreshLayout swipeRefreshLayout;
//
//    @Override
//    public BasePresenter getPresenter() {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    public int layoutId() {
//        return R.layouts.fragment_start_state;
//    }
//
//    @Override
//    public void init() {
//        planBAdapter = new PlanBAdapter(this);
//        List<String> list = new ArrayList<>();
//        list.add("222");
//        list.add("222");
//        list.add("222");
//        list.add("222");
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));  //线性布局
//        recyclerView.setAdapter(planBAdapter);
//        planBAdapter.setData(list);
//    }
}
