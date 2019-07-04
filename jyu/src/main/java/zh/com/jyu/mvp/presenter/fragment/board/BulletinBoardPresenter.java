package zh.com.jyu.mvp.presenter.fragment.board;

import java.util.List;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.bean.fragment.BulletinBoard;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.mvp.contract.fragment.BulletinBoardContract;
import zh.com.jyu.mvp.model.fragment.BulletinBoardModel;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
public class BulletinBoardPresenter extends BasePresenter<BulletinBoardContract.BulletinBoardUI> implements BulletinBoardContract.Presenter {
    private BulletinBoardModel model;

    @Inject
    public BulletinBoardPresenter(BulletinBoardModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void request(String key) {
        model.request(key, new MyObservable.OnSuccessListener<Data<List<BulletinBoard>>>() {
            @Override
            public void onSuccess(Data<List<BulletinBoard>> listData) {
                if (listData.isState()) view.get().success(listData.getRes());
                else view.get().showMsg(listData.getMsg());
            }

            @Override
            public void onFail(String msg) {
                view.get().showMsg(msg);
            }
        });
    }
}
