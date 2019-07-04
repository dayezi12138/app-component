package zh.com.jyu.mvp.model.fragment;

import java.util.List;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseModel;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.fragment.BulletinBoard;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.business.fragment.board.BulletinBoardFragment;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
public class BulletinBoardModel implements BaseModel<BulletinBoardFragment> {
    private BulletinBoardFragment fragment;
    private MyService myService;

    @Inject
    public BulletinBoardModel(BulletinBoardFragment fragment, MyService myService) {
        this.fragment = fragment;
        this.myService = myService;
    }

    @Override
    public BulletinBoardFragment getBean() {
        return fragment;
    }

    public void request(String key, MyObservable.OnSuccessListener<Data<List<BulletinBoard>>> listener) {
        MyObservable observable = new MyObservable.Builder<>(listener, fragment.getMyActivity()).showDialog(false).build();
        myService.getDailyBoard(key).subscribe(observable);
    }
}
