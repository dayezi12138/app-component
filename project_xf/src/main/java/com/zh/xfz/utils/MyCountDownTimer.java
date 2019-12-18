package com.zh.xfz.utils;

import android.os.CountDownTimer;

import static com.zh.xfz.constans.Constants.COUNT_DOWN_INTERVAL;
import static com.zh.xfz.constans.Constants.MILLIS_IN_FUTURE;

/**
 * author : dayezi
 * data :2019/12/13
 * description:
 */
public class MyCountDownTimer extends CountDownTimer {
    private CountDownListener countDownListener;

    public void setCountDownListener(CountDownListener countDownListener) {
        this.countDownListener = countDownListener;
    }

    public MyCountDownTimer() {
        super(MILLIS_IN_FUTURE, COUNT_DOWN_INTERVAL);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if (countDownListener != null) {
            int second = (int) ((millisUntilFinished / COUNT_DOWN_INTERVAL) % 60);
            countDownListener.onTick(second);
        }
    }

    @Override
    public void onFinish() {
        if (countDownListener != null) {
            countDownListener.onFinish();
        }
    }

    /**
     *
     */
    public interface CountDownListener {
        void onTick(int second);//回传秒

        void onFinish();
    }
}
