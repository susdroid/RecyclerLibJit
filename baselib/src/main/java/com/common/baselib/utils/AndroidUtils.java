package com.common.baselib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.common.baselib.R;
import com.common.baselib.utils.snackbar.SnackbarUtils;

import androidx.annotation.Nullable;


/**
 * 提示-跳转的基本工具类
 * Created by Sudroid on 2017/3/13.
 */

public class AndroidUtils {

    private static String oldMsg;
    protected static Toast toast = null;
    private static long oneTime = 0;
    private static long twoTime = 0;

    public static void ToastMiddle(Context paramContext, String paramString) {
        if (toast == null) {
            toast = Toast.makeText(paramContext.getApplicationContext(), paramString, Toast.LENGTH_SHORT);
            toast.setGravity(17, 0, 0);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (paramString.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.setGravity(17, 0, 0);
                    toast.show();
                }
            } else {
                oldMsg = paramString;
                toast.setText(paramString);
                toast.setGravity(17, 0, 0);
                toast.show();
            }
        }
        oneTime = twoTime;
    }

    //顶部  snackbar
    public static void Toast(Activity paramContext, String paramString) {
        SnackbarUtils.topShort(paramContext.getWindow().getDecorView(), paramString)
                .topBackColor(Color.parseColor("#E611223D"))
                .topMessageCenter()
                .topMessageSize(16)
                .topMessageColor(Color.parseColor("#FFFFFF"))
                .topShow();
    }

    public static void ToastWhite(Activity paramContext, String paramString) {
        SnackbarUtils.topShort(paramContext.getWindow().getDecorView(), paramString)
                .topBackColor(Color.parseColor("#E6FFFFFF"))
                .topMessageCenter()
                .topMessageSize(16)
                .topMessageColor(Color.parseColor("#333333"))
                .topShow();
    }

    public static void ToastWarning(Activity paramContext, String paramString, @Nullable Drawable leftDrawable) {
        SnackbarUtils.topShort(paramContext.getWindow().getDecorView(), paramString)
                .topBackColor(Color.parseColor("#E611223D"))
                .topLeftAndRightDrawable(leftDrawable, null)
                .topMessageSize(16)
                .topMessageColor(Color.parseColor("#FFFFFF"))
                .topShow();
    }

    /**
     * 开启activity
     *
     * @param context
     * @param activity
     * @param anime    是否需要切换动画动画（从右边进入，左边退出）
     */
    public static void gotoActivity(Context context, Class<?> activity, boolean anime) {
        Intent intent = new Intent(context, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
        if (anime) {
            ((Activity) context).overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        }
    }

    /**
     * 开启activity
     *
     * @param context
     * @param activity
     * @param anime    是否需要切换动画动画（从右边进入，左边退出）
     */
    public static void gotoMainActivity(Context context, Class<?> activity, boolean anime) {
        Intent intent = new Intent(context, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
        if (anime) {
            ((Activity) context).overridePendingTransition(R.anim.fade_in, R.anim.scale_middle_out);
            ;
        }
    }

    /**
     * login 专用
     *
     * @param context
     * @param activity
     * @param anime    是否需要切换动画动画（从底部进入，底部退出）
     */
    public static void gotoActivityBom(Context context, Class<?> activity, boolean anime) {
        Intent intent = new Intent(context, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
        if (anime) {
            ((Activity) context).overridePendingTransition(R.anim.anim_bottom_in, R.anim.anim_bottom_out);
        }
    }

    /**
     * 开启activity
     *
     * @param context
     * @param activity
     * @param anime    是否需要切换动画动画（从底部进入，底部退出）
     */
    public static void gotoActivityBom(Context context, Class<?> activity,
                                       boolean anime, Bundle bundle) {
        Intent intent = new Intent(context, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra("bundle", bundle);
        context.startActivity(intent);
        if (anime) {
            ((Activity) context).overridePendingTransition(R.anim.anim_bottom_in, R.anim.anim_bottom_out);
        }
    }

    /**
     * 开启activity
     *
     * @param context
     * @param activity
     * @param anime    是否需要切换动画动画（从右边进入，左边退出）
     * @param bundle
     */
    public static void gotoActivity(Context context, Class<?> activity,
                                    boolean anime, Bundle bundle) {
        Intent intent = new Intent(context, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra("bundle", bundle);
        context.startActivity(intent);
        if (anime) {
            ((Activity) context).overridePendingTransition(
                    R.anim.in_from_right, R.anim.out_to_left);
        }
    }

//    //跳转网页
//    public static void goToWebView(Context context, String titles) {
//        Intent intent = new Intent(context, WebViewActivity.class);
//        intent.putExtra("isUrl", false);
//        intent.putExtra(WebViewActivity.EXTRA_TOP_TITLE, titles);
//        intent.putExtra(WebViewActivity.EXTRA_TITLE, titles);
//        context.startActivity(intent);
//    }
//
//    //跳转网页
//    public static void goToWebViewToUrl(Context context, String titles, String url) {
//        Intent intent = new Intent(context, WebViewActivity.class);
//        intent.putExtra("isUrl", true);
//        intent.putExtra("urls", url);
//        intent.putExtra(WebViewActivity.EXTRA_TOP_TITLE, titles);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);
//    }
//
//    //跳转网页
//    public static void goToWebViewToUrl(Context context, String titles, String url, Bundle bundle) {
//        Intent intent = new Intent(context, WebViewActivity.class);
//        intent.putExtra("isUrl", true);
//        intent.putExtra("urls", url);
//        intent.putExtra("bundle", bundle);
//        intent.putExtra(WebViewActivity.EXTRA_TOP_TITLE, titles);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);
//    }


    //内置浏览器打开url
    public static void goToAndroidView(Context context, String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(Intent.createChooser(intent, "请选择浏览器"));
    }
}
