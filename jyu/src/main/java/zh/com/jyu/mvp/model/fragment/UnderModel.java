package zh.com.jyu.mvp.model.fragment;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.BaseView;
import core.app.zh.com.core.base.MyBaseModel;
import core.app.zh.com.core.bean.MyObservable;
import core.app.zh.com.core.listener.GetMyBaseModelListener;
import zh.com.jyu.api.MyService;
import zh.com.jyu.application.MyApplication;
import zh.com.jyu.bean.activity.CraftBean;
import zh.com.jyu.bean.other.Data;

/**
 * author : dayezi
 * data :2019/6/27
 * description:
 */
public class UnderModel implements GetMyBaseModelListener {

    private BaseFragment fragment;
    private MyService myService;
    private int pageIndex = 1;
    private MyApplication myApplication;

    @Inject
    public UnderModel(BaseFragment fragment, MyApplication myApplication, MyService myService) {
        this.myService = myService;
        this.myApplication = myApplication;
        this.fragment = fragment;
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

    @Override
    public MyBaseModel getMyBaseModel() {
        return new MyBaseModel(myApplication) {
            @Override
            public BaseView getBaseView() {
                return (fragment != null && fragment instanceof BaseView) ? fragment : null;
            }

            @Override
            public BaseActivity getMyActivity() {
                return fragment.getMyActivity();
            }
        };
    }
}
