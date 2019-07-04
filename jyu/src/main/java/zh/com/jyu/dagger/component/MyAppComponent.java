package zh.com.jyu.dagger.component;


import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import zh.com.jyu.application.MyApplication;
import zh.com.jyu.dagger.module.AllActivityModule;
import zh.com.jyu.dagger.module.AllFragmentModule;
import zh.com.jyu.dagger.module.AppModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AppModule.class,
        AllActivityModule.class,
        AllFragmentModule.class
})
public interface MyAppComponent extends AndroidInjector<MyApplication> {
    void inject(MyApplication instance);
}