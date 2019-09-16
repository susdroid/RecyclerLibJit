package com.common.baselib.utils.pic;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;


import com.common.baselib.BaseApplication;

import java.util.List;

import androidx.annotation.DimenRes;

/**
 * 像素转换工具类
 * Created by Sudroid on 2018/8/10.
 */
public class DensityUtil {

    public static <T> boolean notEmpty(List<T> list) {
        return !isEmpty(list);
    }

    public static <T> boolean isEmpty(List<T> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }

    private static Resources sRes = Resources.getSystem();
    private static int sDensityDpi = sRes.getDisplayMetrics().densityDpi;
    private static float sScaledDensity = sRes.getDisplayMetrics().scaledDensity;


    public static int dp2px(float value) {
        final float scale = sDensityDpi;
        return (int) (value * (scale / 160) + 0.5f);
    }

    public static int px2dp(float value) {
        final float scale = sDensityDpi;
        return (int) ((value * 160) / scale + 0.5f);
    }

    public static int sp2px(float value) {
        float spValue = value * sScaledDensity;
        return (int) (spValue + 0.5f);
    }

    public static int px2sp(float value) {
        final float scale = sScaledDensity;
        return (int) (value / scale + 0.5f);
    }

    public static int dimenPixelSize(@DimenRes int id) {
        return BaseApplication.getContext().getResources().getDimensionPixelSize(id);
    }

    // 将px值转换为dip或dp值
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    // 将dip或dp值转换为px值
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    // 将px值转换为sp值
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    // 将sp值转换为px值
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static float getWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }


}