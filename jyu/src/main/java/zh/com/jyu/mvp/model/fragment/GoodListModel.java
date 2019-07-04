package zh.com.jyu.mvp.model.fragment;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseModel;
import zh.com.jyu.api.MyService;
import zh.com.jyu.business.fragment.BaseGoodListFragment;

/**
 * author : dayezi
 * data :2019/6/13
 * description:
 */
public class GoodListModel implements BaseModel<BaseGoodListFragment> {
    private BaseGoodListFragment fragment;
    private MyService myService;

    @Inject
    public GoodListModel(BaseGoodListFragment fragment, MyService myService) {
        this.fragment = fragment;
        this.myService = myService;
    }

    @Override
    public BaseGoodListFragment getBean() {
        return fragment;
    }
}
