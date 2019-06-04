package core.app.zh.com.core.dagger.component;

import core.app.zh.com.core.application.BaseApplication;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class
})
public interface MyAppComponent extends AndroidInjector<BaseApplication>{

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseApplication> {
    }
}
