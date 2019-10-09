package zh.com.jyu.mvp.model.fragment;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseModel;
import core.app.zh.com.core.bean.MyObservable;
import core.app.zh.com.core.listener.GetMyBaseModelListener;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.fragment.PlanBean;
import zh.com.jyu.bean.other.Data;

/**
 * author : dayezi
 * data :2019/6/27
 * description:
 */
public class NPlanModel implements GetMyBaseModelListener {
    private MyService myService;
    private int pageIndex = 1;
    private MyBaseModel baseModel;

    @Inject
    public NPlanModel(MyService myService, MyBaseModel baseModel) {
        this.myService = myService;
        this.baseModel = baseModel;
    }

    public void getPlanPager(boolean isLoadMore, Map<String, Object> params, MyObservable.OnSuccessListener<Data<List<PlanBean>>> listener) {
        if (isLoadMore) pageIndex += 1;
        else pageIndex = 1;
        params.put("pageIndex", pageIndex);
        MyObservable observable = new MyObservable.Builder<>(listener, baseModel.getMyActivity()).showDialog(false).build();
        myService.getPlanPager(params).subscribe(observable);
    }

    public int getPageIndex() {
        return pageIndex;
    }

    @Override
    public MyBaseModel getMyBaseModel() {
        return baseModel;
    }
}
