package com.zh.xfz.listener;

import android.text.TextWatcher;

/**
 * author : dayezi
 * data :2019/12/13
 * description:重写TextWatcher
 */
public interface MyTextWatcher extends TextWatcher {
    @Override
    default void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    default void onTextChanged(CharSequence s, int start, int before, int count) {

    }

}
