package com.common.baselib.utils;

import com.common.baselib.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * 调试模式日志工具
 */
public class Debug {

    private static boolean mIsPrintLog = BuildConfig.DEBUG;

    public static void LogD(String msg) {

        if (mIsPrintLog) {
            Logger.d(msg);
        }
    }

    public static void LogI(String msg) {

        if (mIsPrintLog) {
            Logger.i(msg);
        }
    }

    public static void LogE(String msg) {

        if (mIsPrintLog) {
            Logger.e(msg);
        }
    }

    public static void LogV(String msg) {

        if (mIsPrintLog) {
            Logger.v(msg);
        }
    }

    public static void LogW(String msg) {

        if (mIsPrintLog) {
            Logger.w(msg);
        }
    }

    public static boolean getDebugState() {
        return mIsPrintLog;
    }

}
