package com.common.baselib.widget;

import android.view.View;

/**
 * 双击回到顶部
 * Created by Sudroid on 2018/3/9.
 */

public class DoubleClick {

    public static void registerDoubleClickListener(View view, final OnDoubleClickListener listener) {
        if (listener == null) return;
        view.setOnClickListener(new View.OnClickListener() {
            //双击间隔时间350毫秒
            private static final int DOUBLE_CLICK_TIME = 350;
            private boolean flag = true;

            //等待双击
            public void onClick(final View v) {
                if (flag) {
                    flag = false;//与执行双击事件
                    new Thread() {
                        public void run() {
                            try {
                                Thread.sleep(DOUBLE_CLICK_TIME);
                                //此时线程沉睡 而flag被修改为false  在DOUBLE_CLICK_TIME内点击则 进入else
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }    //等待双击时间，否则执行单击事件
                        }

                    }.start();
                } else {
                    flag = true;
                    listener.OnDoubleClick(v);    //执行双击
                }
            }
        });
    }
}
