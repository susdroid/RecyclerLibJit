package com.common.baselib.utils.time;

/**
 * 多线程 时间工具类
 * Created by Sudroid on 2018/8/22.
 */

public class ThreadLocalDateUtil {

    public static final long ONE_DAY_SECOND = 86400;//一天的秒数

    public static String formatTime(Long s) {
        Integer ss = 1;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = s / dd;
        Long hour = (s - day * dd) / hh;
        Long minute = (s - day * dd - hour * hh) / mi;
        Long second = (s - day * dd - hour * hh - minute * mi) / ss;

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "天");
            if (hour > 0) {
                sb.append(hour + "小时");
            }
        } else {
            if (hour > 0) {
                sb.append(hour + "小时");
                if (minute > 0) {
                    sb.append(minute + "分");
                }
            } else {
                if (minute > 0) {
                    sb.append(minute + "分");
                }
                if (second > 0) {
                    sb.append(second + "秒");
                }
            }
        }
        return sb.toString();
    }
}
