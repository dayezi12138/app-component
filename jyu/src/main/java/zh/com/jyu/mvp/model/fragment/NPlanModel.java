package zh.com.jyu.mvp.model.fragment;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseModel;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.fragment.PlanBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.business.fragment.plan.NPlanFragment;

/**
 * author : dayezi
 * data :2019/6/27
 * description:
 */
public class NPlanModel implements BaseModel<NPlanFragment> {
    private MyService myService;
    private NPlanFragment fragment;
    private int pageIndex = 1;

    @Inject
    public NPlanModel(MyService myService, NPlanFragment fragment) {
        this.myService = myService;
        this.fragment = fragment;
    }

    @Override
    public NPlanFragment getBean() {
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
