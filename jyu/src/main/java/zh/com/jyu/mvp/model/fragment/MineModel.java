package zh.com.jyu.mvp.model.fragment;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseModel;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.fragment.UpdateInfo;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.business.fragment.mine.MineFragment;

/**
 * author : dayezi
 * data :2019/8/7
 * description:
 */
public class MineModel implements BaseModel<MineFragment> {
    private MineFragment fragment;
    private MyService myService;

    @Inject
    public MineModel(MineFragment fragment, MyService myService) {
        this.fragment = fragment;
        this.myService = myService;
    }

    public void checkUpdate(MyObservable.OnSuccessListener<Data<UpdateInfo>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, fragment.getMyActivity()).showDialog(false).build();
        myService.updateInfo().subscribe(observable);
    }

    @Override
    public MineFragment getBean() {
        return fragment;
    }
}
