package zh.com.jyu.mvp.model.fragment;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseModel;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.activity.CraftBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.business.fragment.leader.UnderFragment;

/**
 * author : dayezi
 * data :2019/6/27
 * description:
 */
public class UnderModel implements BaseModel<UnderFragment> {

    private UnderFragment fragment;
    private MyService myService;
    private int pageIndex = 1;

    @Inject
    public UnderModel(UnderFragment fragment, MyService myService) {
        this.fragment = fragment;
        this.myService = myService;
    }

    @Override
    public UnderFragment getBean() {
        return fragment;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void getProduceCraftsReceiptListPager(Map<String, Object> map, MyObservable.OnSuccessListener<Data<List<CraftBean>>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, fragment.getMyActivity()).showDialog(false).build();
        myService.getProduceCraftsReceiptListPager(map).subscribe(observable);
    }
}
