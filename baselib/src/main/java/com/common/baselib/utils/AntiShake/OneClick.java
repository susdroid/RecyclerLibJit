package com.common.baselib.utils.AntiShake;

import java.util.Calendar;

/**
 * Created by Sudroid on 2018/1/5.
 */

public class OneClick {

    private String methodName;
    private static final int CLICK_DELAY_TIME = 500;
    private long lastClickTime = 0;

    public OneClick(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public boolean check() {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            return false;
        } else {
            return true;
        }
    }
}
