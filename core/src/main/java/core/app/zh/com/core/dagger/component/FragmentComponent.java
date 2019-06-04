package core.app.zh.com.core.dagger.component;

import core.app.zh.com.core.base.BaseFragment;
import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Subcomponent(modules = AndroidInjectionModule.class)
public interface FragmentComponent extends AndroidInjector<BaseFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseFragment> {

    }
}
