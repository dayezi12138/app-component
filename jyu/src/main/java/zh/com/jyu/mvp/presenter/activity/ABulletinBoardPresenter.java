package zh.com.jyu.mvp.presenter.activity;

import java.util.List;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseModel;
import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.bean.fragment.BulletinBoard;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.mvp.contract.activity.ABulletinBoardContract;
import zh.com.jyu.mvp.contract.fragment.BulletinBoardContract;
import zh.com.jyu.mvp.model.activity.ABulletinBoardModel;

/**
 * author : dayezi
 * data :2019/9/5
 * description:
 */
public class ABulletinBoardPresenter extends BasePresenter<ABulletinBoardContract.ABulletinBoardUI> implements ABulletinBoardContract.Presenter {
    private ABulletinBoardModel model;

    @Inject
    public ABulletinBoardPresenter(ABulletinBoardModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void request(String key, String craftId) {
        model.request(key, craftId, new MyObservable.OnSuccessListener<Data<List<BulletinBoard>>>() {
            @Override
            public void onFail(String msg) {
                view.get().showMsg(msg);
            }

            @Override
            public void onSuccess(Data<List<BulletinBoard>> listData) {
                if (listData.isState()) {
                    view.get().success(listData.getRes());
                } else view.get().showMsg(listData.getMsg());
            }
        });
    }
}
