package zh.com.jyu.mvp.presenter.activity;

import com.blankj.utilcode.util.SPUtils;

import javax.inject.Inject;

import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.R;
import zh.com.jyu.bean.activity.LoginBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.constans.SpKeyConstant;
import zh.com.jyu.mvp.contract.activity.LoginContract;
import zh.com.jyu.mvp.model.activity.LoginModel;
import zh.com.jyu.utils.MyTextUtils;

/**
 * author : dayezi
 * data :2019/6/10
 * description:
 */
public class LoginPresenter extends BasePresenter<LoginContract.LoginUI> implements LoginContract.Presenter {
    private LoginModel model;

    @Inject
    public LoginPresenter(LoginModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void login(String userName, String password) {
        boolean valid = MyTextUtils.showValidMsg(R.string.act_login_error_tip_str, userName, password);
        if (!valid)
            model.login(userName, password, new MyObservable.OnSuccessListener<Data<LoginBean>>() {
                @Override
                public void onSuccess(Data<LoginBean> loginBeanData) {
                    if (loginBeanData.isState()) {
                        SPUtils.getInstance().put(SpKeyConstant.LOGIN_UID_KEY, loginBeanData.getRes().getUserId());
                        SPUtils.getInstance().put(SpKeyConstant.LOGIN_LINK_MAN_KEY, loginBeanData.getRes().getLinkman());
                        SPUtils.getInstance().put(SpKeyConstant.LOGIN_GROUP_ID_KEY, loginBeanData.getRes().getGroupID());
                        model.getLoginStateListener().start();
                        model.getBean().finish();
                    } else {
                        onFail(loginBeanData.getMsg());
                    }
                }

                @Override
                public void onFail(String msg) {
                    view.get().showMsg(msg);
                }
            });
    }
}
