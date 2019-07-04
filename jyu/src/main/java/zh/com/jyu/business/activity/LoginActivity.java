package zh.com.jyu.business.activity;

import android.support.annotation.NonNull;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindViews;
import butterknife.OnClick;
import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.base.BasePresenter;
import core.app.zh.com.core.listener.AppExitListener;
import zh.com.jyu.R;
import zh.com.jyu.mvp.contract.activity.LoginContract;
import zh.com.jyu.mvp.presenter.activity.LoginPresenter;

/**
 * author : dayezi
 * data :2019/6/10
 * description:
 */
@Route(path = LoginActivity.AROUTE_PATH)
public class LoginActivity extends BaseActivity implements LoginContract.LoginUI {

    public static final String AROUTE_PATH = "/common/LoginActivity/";

    @Inject
    AppExitListener listener;

    @Inject
    LoginPresenter presenter;

    @BindViews({R.id.username, R.id.password})
    List<EditText> params;

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @NonNull
    @Override
    public int layoutId() {
        return R.layout.act_login;
    }

    @Override
    public void init() {
        setSystemUiVisibility(false);
        setAppExitListener(listener);
    }

    @OnClick(R.id.login)
    public void submit() {
        presenter.login(params.get(0).getText().toString(), params.get(1).getText().toString());
    }
}
