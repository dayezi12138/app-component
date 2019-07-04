package zh.com.jyu.mvp.model.fragment;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseModel;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.activity.GoodListBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.business.fragment.produce.ProduceListFragment;

/**
 * author : dayezi
 * data :2019/6/27
 * description:
 */
public class ProduceListModel implements BaseModel<ProduceListFragment> {
    private ProduceListFragment fragment;
    private MyService myService;
    private int pageIndex = 1;

    @Inject
    public ProduceListModel(ProduceListFragment fragment, MyService myService) {
        this.fragment = fragment;
        this.myService = myService;
    }

    @Override
    public ProduceListFragment getBean() {
        return fragment;
    }

    public void getProduceGoodsReceiptPageList(Map<String, Object> map, MyObservable.OnSuccessListener<Data<List<GoodListBean>>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, fragment.getMyActivity()).showDialog(false).build();
        myService.getProduceGoodsReceiptPageList(map).subscribe(observable);
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
