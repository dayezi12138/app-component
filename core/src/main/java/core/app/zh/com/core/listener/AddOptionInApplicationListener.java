package core.app.zh.com.core.listener;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import core.app.zh.com.core.application.BaseApplication;

/**
 * author : dayezi
 * data :2019/6/3
 * description:
 */
public interface AddOptionInApplicationListener {

     void init(BaseApplication application) ;

    List<ApplicationInitListener> addListener();

    DaggerOptionListener daggerOptionListener();

    default List<AddOptionInPageListener> optionActivityList() {
        List<AddOptionInPageListener> listeners = new ArrayList<>();
        listeners.add((object, view) -> {
            ButterKnife.bind(object, view);
            ARouter.getInstance().inject(object);
        });
        return listeners;
    }
}
