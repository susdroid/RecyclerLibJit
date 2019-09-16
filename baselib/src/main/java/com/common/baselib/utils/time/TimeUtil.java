package com.common.baselib.utils.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间转换工具类
 * Created by chen on 2015/10/26.
 */
public class TimeUtil {

    //带提示性语句的友好时间，如刚刚，昨天等
    public static final int FRIEND_TIME_TYPE_TIP = 0;

    //只显示简单明了的友好时间
    public static final int FRIEND_TIME_TYPE_TIME = 1;
    //倒计时
    public static final int FRIEND_TIME_COUNT_DOWN = 2;
    //只显示简单明了的友好时间
    public static final int FRIEND_TIME_TYPE_SIMPLE = 3;
    //列表倒计时
    public static final int TIME_TYPE_LIST = 5;

    public static final int TIME_TYPE_ORDER = 4;

    public static final long ONE_DAY_MILLIS = 86400000;//一天的毫秒数

    public static final long ONE_DAY_SECOND = 86400;//一天的秒数

    public static final long ONE_HOUR_MILLIS = 3600000;//一小时的毫秒数

    public static final long ONE_MINUTE_MILLIS = 60000;//一分钟的毫秒数

    public static final long ONE_SECOND_MILLIS = 1000;//一秒的毫秒数

    //日期格式化
    public static final String DATE_BH = "yyyy/MM/dd";

    public static final String DATE_CH = "yyyy年MM月dd日";


    public static final String DATE_EN = "yyyy-MM-dd";

    public static final String DATE_FN = "yyyy年MM月dd日 HH:mm";

    //简单日期格式化
    public static final String SIMPLE_DATE_CH = "MM月dd日";

    public static final String SIMPLE_DATE_EN = "MM-dd";

    public static final String SIMPLE_DATE_FN = "MM.dd";

    //年份格式化
    public static final String YEAR_CH_EN = "yyyy";

    //时间格式化
    public static final String TIME_CH = "hh小时mm分ss秒";
    //倒计时 时间格式化 时分秒
    public static final String TIME_EN = "HH:mm:ss";
    //倒计时 时间格式化 天
    public static final String TIME_DN = "d天";
    //倒计时 时间格式化 天
    public static final String TIME_MN = "dd天";
    //倒计时 时间格式化 天
    public static final String TIME_HN = "dd天 HH:mm";

    //简单时间格式化
    public static final String SIMPLE_TIME_CH = "hh小时mm分";

    public static final String SIMPLE_TIME_EN = "HH:mm";

    public static final String SIMPLE_TIME_SN = "mm:ss";

    //完整时间格式化
    public static final String FULL_TIME_CH = "yyyy年MM月dd日 HH小时mm分ss秒";

    public static final String FULL_TIME_EN = "yyyy-MM-dd HH:mm:ss";


    /**
     * 字符时间转时间戳
     *
     * @param time
     * @return 错误返回-1
     */
    public static long strToTimeStamp(String time, String format) {
        if (time == null) return -1;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date;
        try {
            date = formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
        return date.getTime() / 1000;
    }

    /**
     * 时间戳转格式化时间
     *
     * @param time   时间戳 秒为单位
     * @param format 输出格式
     * @return
     */
    public static String timeStampToDate(long time, String format) {
        if (time <= 0) return "wrong time stamp";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date(time * 1000);
        return dateFormat.format(date);
    }

    public static boolean isYesterday(long timeStamp) {
        return isToday(timeStamp + ONE_DAY_MILLIS);
    }

    //根据返回的日期判断是否是今日
    public static boolean isToday(long timeStamp) {
        long now = System.currentTimeMillis();
        SimpleDateFormat formatDate = new SimpleDateFormat(DATE_EN);
        String curDate = formatDate.format(now);
        String timeDate = formatDate.format(timeStamp);
        return curDate.equals(timeDate);
    }

    //根据返回的日期判断是否是今年
    public static boolean isThisYear(long timeStamp) {
        long now = System.currentTimeMillis();
        SimpleDateFormat formatDate = new SimpleDateFormat(YEAR_CH_EN);
        String curDate = formatDate.format(now);
        String timeDate = formatDate.format(timeStamp);
        return curDate.equals(timeDate);
    }

    public static String[][] getLastSixMonth() {
        String[][] sixMonth = new String[2][6];
        Calendar c = Calendar.getInstance();

        int mYear = c.get(Calendar.YEAR); //获取当前年份
        int mMonth = c.get(Calendar.MONTH) + 1;//获取当前月份

        for (int i = 0; i < 6; i++) {
            int m = mMonth - i;
            if (m < 1) {
                m = 12 + m;
                mYear -= 1;
            }
            sixMonth[0][i] = m + "月";
            String mm = m < 10 ? m + "" : "0" + m;
            String time = mYear + "-" + mm + "-01";
            sixMonth[1][i] = (strToTimeStamp(time, DATE_EN) / 1000) + "";

        }

        return sixMonth;
    }

    //倒计时  秒数转时分秒
    public static String dateFormatFromMilliSecond(long seconds) {
//        seconds = ONE_DAY_SECOND+10;
        if (seconds == -1) return "00:00:00";
        SimpleDateFormat mFormat = null;
        //初始化format格式
        if (seconds > ONE_DAY_SECOND * 5) {
            mFormat = new SimpleDateFormat(TIME_MN);
        } else if (seconds > ONE_DAY_SECOND) {
            mFormat = new SimpleDateFormat(TIME_DN);
        } else {
            mFormat = new SimpleDateFormat(TIME_EN);
        }
        //设置时区，跳过此步骤会默认设置为"GMT+08:00" 得到的结果会多出来8个小时
        mFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));

        String time = mFormat.format(seconds * 1000);
        return time;
    }

    /**
     * 获取友好时间
     *
     * @param time
     * @return
     */
    public static String getTypeTime(String time, int type) {
        long milliTime = strToTimeStamp(time, FULL_TIME_EN);
        if (milliTime == -1) return "time is null or format error";
        return getTypeTime(milliTime, type);
    }

    //输入标准时间戳
    public static String getTypeTime(long milliTime, int type) {
        switch (type) {
            //带提示的时间显示
            case FRIEND_TIME_TYPE_TIP:
                long now = System.currentTimeMillis();
                long diff = Math.abs(now - milliTime * 1000);
                if (diff <= ONE_MINUTE_MILLIS * 5) {
                    return "刚刚";
                } else if (diff < ONE_HOUR_MILLIS) {
                    int minute = (int) (diff / ONE_MINUTE_MILLIS);
                    return minute + "分钟前";
                } else if (diff <= ONE_HOUR_MILLIS * 5) {
                    int hour = (int) (diff / ONE_HOUR_MILLIS);
                    return hour + "小时前";
                } else if (isToday(milliTime * 1000)) {
                    return new SimpleDateFormat(SIMPLE_TIME_EN).format(milliTime * 1000);
                } else if (isYesterday(milliTime * 1000)) {
                    return "昨天 " + new SimpleDateFormat(SIMPLE_TIME_EN).format(milliTime * 1000);
                } else if (isThisYear(milliTime * 1000)) {
                    return new SimpleDateFormat(DATE_EN).format(milliTime * 1000);
                } else return new SimpleDateFormat(DATE_EN).format(milliTime * 1000);
                //简单的时间显示
            case FRIEND_TIME_TYPE_TIME:
                return new SimpleDateFormat(DATE_EN).format(milliTime * 1000);
            case FRIEND_TIME_TYPE_SIMPLE:
                return new SimpleDateFormat(SIMPLE_DATE_FN).format(milliTime * 1000);
            case FRIEND_TIME_COUNT_DOWN:
                return new SimpleDateFormat(SIMPLE_TIME_SN).format(milliTime * 1000);
            case TIME_TYPE_LIST:
                return new SimpleDateFormat(TIME_HN).format(milliTime * 1000);
            case TIME_TYPE_ORDER:
            default:
                return new SimpleDateFormat(FULL_TIME_EN).format(milliTime * 1000);
        }
    }

}
