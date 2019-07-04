package zh.com.jyu.mvp.presenter.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import zh.com.jyu.R;
import zh.com.jyu.mvp.contract.activity.MainContract;
import zh.com.jyu.mvp.model.activity.MainModel;

/**
 * author : dayezi
 * data :2019/6/11
 * description:
 */
public class MainPresenter extends BasePresenter<MainContract.MainUI> implements MainContract.Presenter {
    private MainModel model;

    @Inject
    public MainPresenter(MainModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void addFragment() {
        FragmentTransaction transaction = model.getFragmentManager().beginTransaction();
        for (Fragment fragment : model.getFragmentList()) {
            transaction.add(R.id.fragment_, fragment);
            transaction.hide(fragment);
        }
        transaction.show(model.getFragmentList().get(0));
        transaction.commit();
    }

    @Override
    public void setTabPosition(int index) {
        FragmentTransaction transaction = model.getFragmentManager().beginTransaction();
        for (int i = 0; i < model.getFragmentList().size(); i++) {
            if (i != index) transaction.hide(model.getFragmentList().get(i));
        }
        transaction.show(model.getFragmentList().get(index));
        transaction.commit();
    }
}
