package core.app.zh.com.core.base;

import android.support.v4.app.Fragment;

import dagger.android.AndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * @auther create by Administrator
 * DATE:2019/3/26 0026 19
 */
public abstract  class InJectFragmentActivity extends BaseActivity implements HasSupportFragmentInjector{

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return super.supportFragmentInjector();
    }
}
