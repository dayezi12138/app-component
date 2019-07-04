package zh.com.jyu.mvp.contract.fragment;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.BaseModel;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.fragment.PlanBean;
import zh.com.jyu.bean.other.Data;

/**
 * author : dayezi
 * data :2019/6/12
 * description:
 */
public class PlanWaitModel implements BaseModel<BaseFragment> {
    private BaseFragment fragment;
    private MyService myService;
    private int pageIndex = 1;

    @Inject
    public PlanWaitModel(BaseFragment fragment, MyService myService) {
        this.fragment = fragment;
        this.myService = myService;
    }

    @Override
    public BaseFragment getBean() {
        return fragment;
    }

    public void getPlanPager(boolean isLoadMore, Map<String, Object> params, MyObservable.OnSuccessListener<Data<List<PlanBean>>> listener) {
        if (isLoadMore) pageIndex += 1;
        else pageIndex = 1;
        params.put("pageIndex", pageIndex);
        MyObservable observable = new MyObservable.Builder<>(listener, fragment.getMyActivity()).showDialog(false).build();
        myService.getPlanPager(params).subscribe(observable);
    }

    public int getPageIndex() {
        return pageIndex;
    }
}
