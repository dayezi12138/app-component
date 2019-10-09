package zh.com.jyu.mvp.model.activity;

import java.util.List;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseModel;
import core.app.zh.com.core.bean.MyObservable;
import core.app.zh.com.core.listener.GetMyBaseModelListener;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.fragment.BulletinBoard;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.business.activity.BulletinBoardActivity;

/**
 * author : dayezi
 * data :2019/9/5
 * description:
 */
public class ABulletinBoardModel implements GetMyBaseModelListener {
    private MyBaseModel myBaseModel;
    private BulletinBoardActivity activity;
    private MyService myService;

    @Inject
    public ABulletinBoardModel(MyBaseModel myBaseModel, BulletinBoardActivity activity, MyService myService) {
        this.myBaseModel = myBaseModel;
        this.activity = activity;
        this.myService = myService;
    }

    @Override
    public MyBaseModel getMyBaseModel() {
        return myBaseModel;
    }

    public void request(String key, String craftId, MyObservable.OnSuccessListener<Data<List<BulletinBoard>>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, activity.getMyActivity()).showDialog(false).build();
        myService.getDailyBoardA(key, craftId).subscribe(observable);
    }
}
