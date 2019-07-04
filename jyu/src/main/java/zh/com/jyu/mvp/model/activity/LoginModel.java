package zh.com.jyu.mvp.model.activity;

import javax.inject.Inject;

import core.app.zh.com.core.base.BaseModel;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.R;
import zh.com.jyu.api.MyService;
import zh.com.jyu.bean.activity.LoginBean;
import zh.com.jyu.bean.other.Data;
import zh.com.jyu.business.activity.LoginActivity;
import zh.com.jyu.listener.LoginStateListener;

/**
 * author : dayezi
 * data :2019/6/10
 * description:
 */
public class LoginModel implements BaseModel<LoginActivity> {
    private LoginActivity activity;
    private MyService myService;
    private LoginStateListener loginStateListener;

    @Inject
    public LoginModel(LoginActivity activity, MyService myService, LoginStateListener loginStateListener) {
        this.activity = activity;
        this.myService = myService;
        this.loginStateListener = loginStateListener;
    }

    @Override
    public LoginActivity getBean() {
        return activity;
    }

    public void login(String userName, String password, MyObservable.OnSuccessListener<Data<LoginBean>> listener) {
        MyObservable myObservable = new MyObservable.Builder<>(listener, activity)
                .title(activity.getResources().getString(R.string.act_login_loading_str))
                .build();
        myService.login(userName, password).subscribe(myObservable);
    }

    public LoginStateListener getLoginStateListener() {
        return loginStateListener;
    }
}
