package com.zh.api.factory;

import android.app.Activity;
import android.support.v7.widget.Toolbar;

/**
 * author : dayezi
 * data :2019/6/6
 * description:
 */
public interface IPunish<T extends IPunish> {
    void punish(Activity activity, Toolbar toolbar, T bean);
}
