package core.app.zh.com.core.listener.impl;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;

/**
 * author : dayezi
 * data :2019/6/3
 * description:
 */
public class ActivityLifecycleCallbackListener implements Application.ActivityLifecycleCallbacks {

    public static List<Activity> sActivityList = new LinkedList<>();


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        sActivityList.add(activity);

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        sActivityList.remove(activity);
    }

    public List<Activity> getsActivityList() {
        return sActivityList;
    }

}
