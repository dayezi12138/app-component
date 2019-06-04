package com.zh.component.business.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zh.component.R;

@Route(path = MainActivity.AROUTER_PATH)
public class MainActivity extends AppCompatActivity {

    public static final String AROUTER_PATH = "/main/mainLogin/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
