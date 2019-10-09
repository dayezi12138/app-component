package zh.com.jyu.mvp.model.fragment;

import java.util.List;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseFragment;
import core.app.zh.com.core.base.MyBaseModel;
import core.app.zh.com.core.bean.MyObservable;
import core.app.zh.com.core.listener.GetMyBaseModelListener;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.fragment.BulletinBoard;
import zh.com.jyu.bean.fragment.NBulletinBoard;
import zh.com.jyu.bean.other.Data;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
public class BulletinBoardModel implements GetMyBaseModelListener {
    private BaseFragment fragment;
    private MyService myService;
    private MyBaseModel baseModel;

    @Inject
    public BulletinBoardModel(BaseFragment fragment, MyService myService, MyBaseModel myBaseModel) {
        this.fragment = fragment;
        this.myService = myService;
        this.baseModel = myBaseModel;
    }

    public void request(String key, MyObservable.OnSuccessListener<Data<List<BulletinBoard>>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, fragment.getMyActivity()).showDialog(false).build();
        myService.getDailyBoard(key).subscribe(observable);
    }

    public void getDailyBoardForBoss(String key, MyObservable.OnSuccessListener<Data<List<NBulletinBoard>>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, fragment.getMyActivity()).showDialog(false).build();
        myService.getDailyBoardForBoss(key).subscribe(observable);
    }

    @Override
    public MyBaseModel getMyBaseModel() {
        return baseModel;
    }
}
