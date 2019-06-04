package core.app.zh.com.core.listener;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * author : dayezi
 * data :2019/6/4
 * description:
 */
public interface DaggerOptionListener {

    AndroidInjector<? extends DaggerApplication> optionComponent();
}
