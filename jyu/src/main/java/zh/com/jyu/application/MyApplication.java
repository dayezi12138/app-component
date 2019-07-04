package zh.com.jyu.application;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.zh.api.DefaultOptionListener;
import com.zh.api.ToolBarInject;

import javax.inject.Inject;

import core.app.zh.com.core.application.BaseApplication;
import core.app.zh.com.core.listener.DaggerOptionListener;
import zh.com.jyu.dagger.component.DaggerMyAppComponent;
import zh.com.jyu.dagger.component.MyAppComponent;
import zh.com.jyu.dagger.module.AppModule;

/**
 * author : dayezi
 * data :2019/6/4
 * description:main module
 */
public class MyApplication extends BaseApplication {
    @Inject
    DefaultOptionListener defaultOptionListener;

    @Override
    public void init(BaseApplication application) {
        ToolBarInject.init(MyApplication.this, defaultOptionListener);
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5d14861c");
    }

    @Override
    public DaggerOptionListener daggerOptionListener() {
        MyAppComponent component = DaggerMyAppComponent.builder().appModule(new AppModule(this)).build();
        component.inject(this);
        return () -> component;
    }

}
